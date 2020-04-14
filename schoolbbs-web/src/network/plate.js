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

export function getlist() {
  return request({
    url: "/plate/getlist",
    method: "get"
  });
}
export function deleteplate(id) {
  return request({
    url: "/plate/deleteplate",
    method: "get",
    params: {
      id: id
    }
  });
}

export function updateplate(data) {
  return request({
    url: "/plate/updateplate",
    method: "post",
    data: data
  });
}

export function addplate(data) {
  return request({
    url: "/plate/addplate",
    method: "post",
    data: data
  });
}
