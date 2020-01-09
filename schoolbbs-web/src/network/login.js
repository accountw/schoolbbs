import { request } from "./request.js";

export function login() {
  return request({
    url: "api/user/s"
  });
}
export function loginout() {
  return request({
    url: "api/"
  });
}
