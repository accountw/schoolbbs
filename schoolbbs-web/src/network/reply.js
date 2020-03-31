import { request } from "./request.js";

export function getReplybyTopicid(data) {
  return request({
    url: "/reply/getReplybyTopicid",
    method: "get",
    params: {
      topicid: data
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
