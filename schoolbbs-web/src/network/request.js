import axios from "axios";
import Store from "../store/index";
import Message from "element-ui/packages/message/src/main";
import router from "../router";

export function request(config) {
  const instance = axios.create({
    baseURL: " http://localhost:8081/api",
    timeout: 5000,
    headers: {
      "content-type": "application/json"
    }
  });

  /*将所有的请求都push到数组中,其实数组是[function(token){}, function(token){},...]*/
  function subscribeTokenRefresh(cb) {
    Store.state.refreshSubscribers.push(cb);
  }
  /*数组中的请求得到新的token之后自执行，用新的token去请求数据*/
  function onRefreshed(token) {
    Store.state.refreshSubscribers.map(cb => cb(token));
  }
  function retoken() {
    return request({
      url: "/system/token/reload",
      method: "get"
    });
  }
  function tokenrefresh(config, resolve) {
    retoken()
      .then(res => {
        if (res.data.code === "RELOAD") {
          localStorage.setItem("isRefreshing", "1");
          Store.state.isRefreshing = "1";
          Store.state.Authorization = res.data.message;
          localStorage.setItem("Authorization", res.data.message);
          localStorage.setItem("expire", new Date().getTime() + 1800000);
          Store.state.expire = localStorage.getItem("expire");

          config.headers.Authorization = localStorage.getItem("Authorization");
          resolve(config);
          onRefreshed(res.data.message);
          Store.state.refreshSubscribers = [];
        }
      })
      .catch(err => {
        console.log(err);
        Message({
          showClose: true,
          message: "用户登录信息过时",
          type: "error"
        });
        localStorage.clear();
        Store.username = null;
        Store.Authorization = "";
        Store.expire = 0;
        Store.id = "";
        config.cancelToken = "用户登录信息过时";
        location.reload();
      });
  }

  instance.interceptors.request.use(
    config => {
      if (Store.state.Authorization && config.url != "/system/token/reload") {
        if (Store.state.expire > new Date().getTime()) {
          config.headers.Authorization = localStorage.getItem("Authorization");
        } else if (
          parseInt(Store.state.expire) + 1500000 >
          new Date().getTime()
        ) {
          if (Store.state.isRefreshing === "1") {
            localStorage.setItem("isRefreshing", "0");
            Store.state.isRefreshing = "0";
            let refresh = new Promise(resolve => {
              tokenrefresh(config, resolve);
            });
            return refresh;
          } else {
            let retry = new Promise(resolve => {
              /*(token) => {...}这个函数就是cb*/
              subscribeTokenRefresh(token => {
                config.headers.Authorization = token;
                /*将请求挂起*/
                resolve(config);
              });
            });
            return retry;
          }
        } else {
          Message({
            showClose: true,
            message: "用户登录信息过时",
            type: "error"
          });
          localStorage.clear();
          Store.username = null;
          Store.Authorization = "";
          Store.expire = 0;
          config.cancelToken = "用户登录信息过时";
          location.reload();
        }
      }
      if (Store.state.Authorization && config.url === "/system/token/reload") {
        config.headers.Authorization = localStorage.getItem("Authorization");
      }
      return config;
    },
    error => {
      return Promise.reject(error);
    }
  );

  //响应拦截
  instance.interceptors.response.use(
    res => {
      if (res.data.code === "NO_AUTH") {
        Message({
          showClose: true,
          message: "账号于" + res.data.message + "解封",
          type: "error"
        });
      } else if (res.data.code === "COLLECTED") {
        Message({
          showClose: true,
          message: "已收藏",
          type: "info"
        });
        location.reload();
      } else {
        return res;
      }
    },
    err => {
      // eslint-disable-next-line no-console
      if (err.response.status === 401) {
        Message({
          showClose: true,
          message: "用户权限不足",
          type: "error"
        });
      }
      if (err.response.status === 500) {
        Message({
          showClose: true,
          message: "系统出错，请重试",
          type: "error"
        });
        router.push("/");
      }
      if (err.response.status === 403) {
        Message({
          showClose: true,
          message: "用户登录信息过时",
          type: "error"
        });

        location.reload();
      }
    }
  );

  //发送真正的网络请求
  return instance(config);
}
