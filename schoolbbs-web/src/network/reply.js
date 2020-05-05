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

export function getcount(topicid) {
  return request({
    url: "/reply/getcount",
    method: "get",
    params: {
      topicid: topicid
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

export function getreplysearch(context, index) {
  return request({
    url: "reply/getSearch",
    method: "get",
    params: {
      context:context,
      index:index
    }
  });
}

export function getreplysearchCount(context) {
  return request({
    url: "reply/getSearchCount",
    method: "get",
    params: {
      context: context,
    }
  });
}
