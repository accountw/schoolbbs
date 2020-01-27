import { request } from "./request.js";

//注册
export function register(data) {
  return request({
    url: "/user/register",
    method: "post",
    data: data
  });
}

//发送验证码到邮箱
export function sendMessage(data) {
  return request({
    url: "/user/sendMessage",
    method: "post",
    data: data
  });
}

//判断邮箱是否重复
export function selectMail(data) {
  return request({
    url: "/user/selectMail",
    method: "get",
    params: {
      mail: data
    }
  });
}

//判断用户名是否重复
export function selectName(data) {
  return request({
    url: "/user/selectUsername",
    method: "get",
    params: {
      username: data
    }
  });
}

//判断验证码是否正确
export function selectCode(data) {
  return request({
    url: "/user/selectCode",
    method: "get",
    params: {
      userMail: data.userMail,
      code: data.code
    }
  });
}
