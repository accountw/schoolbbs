import { request } from "./request.js";

export function login(data) {
  return request({
    url: "/user/login",
    method: "post",
    data: data,
    headers: {
      "content-type": "application/x-www-form-urlencoded"
    }
  });
}
export function out() {
  return request({
    url: "/system/loginout",
    method: "post"
  });
}
