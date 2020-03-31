import { request } from "./request.js";

export function getFirstTopic() {
  return request({
    url: "/topic/getFirstTopices",
    method: "get"
  });
}

export function getTopicByPlateid(data) {
  return request({
    url: "/topic/getTopicByPlateid",
    method: "get",
    params: {
      plateid: data
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
