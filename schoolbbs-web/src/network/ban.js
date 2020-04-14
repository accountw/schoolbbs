import { request } from "./request";

export function getBanList(index) {
  return request({
    url: "/ban/getBanList",
    method: "get",
    params: {
      index: index
    }
  });
}
export function deleteBan(id) {
  return request({
    url: "/ban/deleteBan",
    method: "get",
    params: {
      id: id
    }
  });
}
export function getcount() {
  return request({
    url: "/ban/getcount",
    method: "get"
  });
}

export function addBan(data) {
  return request({
    url: "/ban/addBan",
    method: "post",
    data: data
  });
}
