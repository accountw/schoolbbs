import { request } from "./request.js";

export function getblock() {
  return request({
    url: "/block/getblock",
    method: "get"
  });
}
