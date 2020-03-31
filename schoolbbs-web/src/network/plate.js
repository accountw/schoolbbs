import { request } from "./request.js";

export function getplate(data) {
  return request({
    url: "/plate/getplate",
    method: "get",
    params: {
      blockid: data
    }
  });
}

export function getPlateByid(data) {
  return request({
    url: "/plate/getPlateByid",
    method: "get",
    params: {
      id: data
    }
  });
}
