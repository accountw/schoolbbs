import { request } from "./request";

export function addFocus(data) {
  return request({
    url: "/focus/addFocus",
    method: "post",
    data: data
  });
}

export function deleteFocus(userId, focusUserId) {
  return request({
    url: "/focus/deleteFocus",
    method: "get",
    params: {
      userId: userId,
      focusUserId: focusUserId
    }
  });
}

export function isFocus(userId, focusUserId) {
  return request({
    url: "/focus/isFocus",
    method: "get",
    params: {
      userId: userId,
      focusUserId: focusUserId
    }
  });
}

export function getcount(userId) {
  return request({
    url: "/focus/getcount",
    method: "get",
    params: {
      userId: userId
    }
  });
}
