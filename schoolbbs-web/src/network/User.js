import { request } from "./request.js";

export function getUser(data) {
  return request({
    url: "/user/getUser",
    method: "get",
    params: {
      username: data
    }
  });
}
