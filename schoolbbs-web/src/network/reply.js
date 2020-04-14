import { request } from "./request.js";

export function getReplybyTopicid(topicid, index) {
  return request({
    url: "/reply/getReplybyTopicid",
    method: "get",
    params: {
      topicid: topicid,
      index: index
    }
  });
}

export function saveReply(data) {
  return request({
    url: "/reply/saveReply",
    method: "post",
    data: data
  });
}
export function deleteReply(data) {
  return request({
    url: "/reply/deleteReply",
    method: "get",
    params: {
      id: data
    }
  });
}
