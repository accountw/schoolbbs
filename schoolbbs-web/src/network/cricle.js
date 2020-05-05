import { request } from "./request.js";

export function getcricleByuserId(userid, index) {
  return request({
    url: "/cricle/getCriclrbyUserid",
    method: "get",
    params: {
      userid: userid,
      index: index
    }
  });
}

export function getCountbyUserid(userid) {
  return request({
    url: "/cricle/getCountbyUserid",
    method: "get",
    params: {
      userid: userid
    }
  });
}

export function deleteCricle(id) {
  return request({
    url: "/cricle/deleteCricle",
    method: "get",
    params: {
      id: id
    }
  });
}

export function add(data) {
  return request({
    url: "/cricle/addCricle",
    method: "post",
    data: data
  });
}

export function getCricle(userid, index) {
  return request({
    url: "/cricle/getCricle",
    method: "get",
    params: {
      userid: userid,
      index: index
    }
  });
}
export function getMessage(cricleId, index) {
  return request({
    url: "/criclemessage/getMessage",
    method: "get",
    params: {
      cricleId: cricleId,
      index: index
    }
  });
}
export function addMessage(data) {
  return request({
    url: "/criclemessage/addMessage",
    method: "post",
    data: data
  });
}

export function getFocusCricleCount(userid) {
  return request({
    url: "/cricle/getFocusCricleCount",
    method: "get",
    params: {
      userid: userid
    }
  });
}

export function getCount(cricleId) {
  return request({
    url: "/criclemessage/getCount",
    method: "get",
    params: {
      cricleId: cricleId
    }
  });
}
