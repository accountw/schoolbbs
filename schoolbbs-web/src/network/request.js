import axios from "axios";

export function request(config) {
  const instance = axios.create({
    baseURL: " http://localhost:8081/api",
    timeout: 5000,
    headers: {
      "Content-Type": "application/json"
    }
  });

  // instance.interceptors.request.use(
  //   config => {
  //     return config;
  //   },
  //   err => {
  //     // eslint-disable-next-line no-console
  //     console.log(err);
  //   }
  // );
  //
  // //响应拦截
  // instance.interceptors.response.use(
  //   res => {
  //     return res.data;
  //   },
  //   err => {
  //     // eslint-disable-next-line no-console
  //     console.log(err);
  //   }
  // );

  //发送真正的网络请求
  return instance(config);
}
