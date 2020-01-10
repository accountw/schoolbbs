import { request } from "./request.js";

export function login(data) {
  return request({
    url: "/user/login",
    method: "post",
    params: data
  });
}
export function loginout() {
  return request({
    url: "/"
  });
}
