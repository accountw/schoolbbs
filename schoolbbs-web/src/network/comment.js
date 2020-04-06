import { request } from "./request.js";

export function getCommentByreplyid(data) {
  return request({
    url: "/comment/getCommentByreplyid",
    method: "get",
    params: {
      replyid: data
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
