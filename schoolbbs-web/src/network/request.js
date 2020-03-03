import axios from "axios";
import Store from "../store/index";
import Message from "element-ui/packages/message/src/main";

function retoken() {
  return request({
    url: "/system/token/reload",
    method: "get"
  });
}
export function request(config) {
  const instance = axios.create({
    baseURL: " http://localhost:8081/api",
    timeout: 5000,
    headers: {
      "content-type": "application/json"
    }
  });

  instance.interceptors.request.use(
    config => {
      if (Store.state.Authorization && config.url != "/system/token/reload") {
        if (Store.state.expire > new Date().getTime()) {
          config.headers.Authorization = localStorage.getItem("Authorization");
        } else if (
          parseInt(Store.state.expire) + 1500000 >
          new Date().getTime()
        ) {
          console.log("刷新token");
          retoken()
            .then(res => {
              if (res.data.code === "RELOAD") {
                Store.state.Authorization = res.data.message;
                localStorage.setItem("Authorization", res.data.message);
                Store.state.expire = new Date().getTime() + 1800000;
                localStorage.setItem("expire", Store.state.expire);
                config.headers.Authorization = localStorage.getItem(
                  "Authorization"
                );
              }
            })
            .catch(err => {
              console.log(err);
              console.log("用户登录信息过时");
              Message({
                showClose: true,
                message: "用户登录信息过时",
                type: "error"
              });
              Store.mutations.logout();
              config.cancelToken = "用户登录信息过时";
            });
        } else {
          console.log("用户登录信息过时");
          Message({
            showClose: true,
            message: "用户登录信息过时",
            type: "error"
          });
          Store.mutations.logout();
          config.cancelToken = "用户登录信息过时";
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
      return res;
    },
    err => {
      // eslint-disable-next-line no-console
      if (err.response.status === 403) {
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
      }
      if (err.response.status === 401) {
        Message({
          showClose: true,
          message: "用户登录信息过时",
          type: "error"
        });
        Store.mutations.logout();
      }
    }
  );

  //发送真正的网络请求
  return instance(config);
}
