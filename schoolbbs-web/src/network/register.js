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
    url: "/checkcode/sendMessage",
    method: "post",
    data: data
  });
}

//判断邮箱是否重复
export function selectMail(data) {
  return request({
    url: "/user/register/selectMail",
    method: "get",
    params: {
      mail: data
    }
  });
}

//判断用户名是否重复
export function selectName(data) {
  return request({
    url: "/user/register/selectUsername",
    method: "get",
    params: {
      username: data
    }
  });
}

//判断验证码是否正确
export function selectCode(data) {
  return request({
    url: "/checkcode/selectCode",
    method: "get",
    params: {
      userMail: data.userMail,
      code: data.code
    }
  });
}

export function studentregister(data) {
  return request({
    url: "/schoolregister/save",
    method: "post",
    data: data
  });
}

export function getGraduateRegister(index) {
  return request({
    url: "/graduateRegister/getRegister",
    method: "get",
    params: {
      index: index
    }
  });
}
export function dealGraduateRegister(id, status) {
  return request({
    url: "/graduateRegister/deal",
    method: "get",
    params: {
      id: id,
      status: status
    }
  });
}
export function getSchoolRegister(index) {
  return request({
    url: "/schoolregister/getRegister",
    method: "get",
    params: {
      index: index
    }
  });
}

export function dealSchoolRegister(id, status) {
  return request({
    url: "/schoolregister/deal",
    method: "get",
    params: {
      id: id,
      status: status
    }
  });
}
