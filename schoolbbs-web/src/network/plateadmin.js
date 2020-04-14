import { request } from "./request";

export function getPlateAdmin(data) {
  return request({
    url: "/plateAdmin/getPlateAdmin",
    methos: "get",
    params: {
      plateid: data
    }
  });
}

export function isAdmin(data) {
  return request({
    url: "/plateAdmin/isAdmin",
    methos: "get",
    params: {
      plateid: data
    }
  });
}

export function listAdmin(index) {
  return request({
    url: "/plateAdmin/listAdmin",
    methos: "get",
    params: {
      index: index
    }
  });
}

export function deleteAdmin(id) {
  return request({
    url: "/plateAdmin/deleteAdmin",
    methos: "get",
    params: {
      id: id
    }
  });
}

export function getcount() {
  return request({
    url: "/plateAdmin/getcount",
    methos: "get"
  });
}
export function addAdmin(data) {
  return request({
    url: "/plateAdmin/addAdmin",
    methos: "post",
    data: data
  });
}
export function setadmin(plateId, username) {
  return request({
    url: "/plateAdmin/setadmin",
    methos: "get",
    params: {
      plateId: plateId,
      username: username
    }
  });
}
