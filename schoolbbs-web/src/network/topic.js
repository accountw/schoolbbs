import { request } from "./request.js";

export function getFirstCount() {
  return request({
    url: "/topic/getFirstCount",
    method: "get"
  });
}

export function getTopicByPlateid(plateid, index) {
  return request({
    url: "/topic/getTopicByPlateid",
    method: "get",
    params: {
      plateid: plateid,
      index: index
    }
  });
}

export function getTopicbyid(data) {
  return request({
    url: "/topic/getTopicbyid",
    method: "get",
    params: {
      id: data
    }
  });
}

export function saveTopic(data) {
  return request({
    url: "/topic/saveTopic",
    method: "post",
    data: data
  });
}
export function getTopicCount(data) {
  return request({
    url: "/topic/getTopicCount",
    method: "get",
    params: {
      plateid: data
    }
  });
}
export function getTopicFlow(index) {
  return request({
    url: "/topic/getTopicFlow",
    method: "get",
    params: {
      index: index
    }
  });
}

export function getTopicByUserid(index, userid) {
  return request({
    url: "/topic/getTopicByUserid",
    method: "get",
    params: {
      index: index,
      userId: userid
    }
  });
}

export function deleteTopic(topicid) {
  return request({
    url: "topic/deleteTopic",
    method: "get",
    params: {
      topicid: topicid
    }
  });
}

export function deleteTopicbyadmin(topicid) {
  return request({
    url: "topic/deleteTopicbyadmin",
    method: "get",
    params: {
      topicid: topicid
    }
  });
}

export function getTop() {
  return request({
    url: "topic/getTop",
    method: "get"
  });
}
