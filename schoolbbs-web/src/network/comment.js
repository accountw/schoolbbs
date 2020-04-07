import { request } from "./request.js";

export function getCommentByreplyid(replyid, index) {
  return request({
    url: "/comment/getCommentByreplyid",
    method: "get",
    params: {
      replyid: replyid,
      index: index
    }
  });
}

export function saveComment(data) {
  return request({
    url: "/comment/saveComment",
    method: "post",
    data: data
  });
}

export function getCommentCount(data) {
  return request({
    url: "/comment/getCommentCount",
    method: "get",
    params: {
      replyid: data
    }
  });
}
