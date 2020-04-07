import { request } from "./request.js";

export function getFirstTopic() {
  return request({
    url: "/topic/getFirstTopices",
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
