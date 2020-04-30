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
export function getUserByid(data) {
  return request({
    url: "/user/getUserByid",
    method: "get",
    params: {
      id: data
    }
  });
}

export function updateUser(data) {
  return request({
    url: "/user/updateUser",
    method: "post",
    data: data
  });
}

export function sendMse() {
  return request({
    url: "/checkcode/sendMse",
    method: "get"
  });
}

export function validatecode(data) {
  return request({
    url: "/checkcode/validatecode",
    method: "get",
    params: {
      code: data
    }
  });
}
export function changepd(data) {
  return request({
    url: "/user/changepd",
    method: "get",
    params: {
      newpassword: data.newpassword,
      oldpassword: data.oldpassword
    }
  });
}

export function getFocusList(userId) {
  return request({
    url: "/user/getFocusList",
    method: "get",
    params: {
      userId: userId
    }
  });
}

export function getFansList(userId) {
  return request({
    url: "/user/getFansList",
    method: "get",
    params: {
      userId: userId
    }
  });
}
