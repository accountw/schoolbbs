import { request } from "./request";

export function deleteCollectPlate(userId, plateId) {
  return request({
    url: "/collect/deleteCollectPlate",
    method: "get",
    params: {
      userId: userId,
      plateId: plateId
    }
  });
}
export function collectPlate(data) {
  return request({
    url: "/collect/collectPlate",
    method: "post",
    data: data
  });
}

export function isCollectTopic(userId, topicId) {
  return request({
    url: "/collect/isCollectTopic",
    method: "get",
    params: {
      userId: userId,
      topicId: topicId
    }
  });
}
export function isCollectPlate(userId, plateId) {
  return request({
    url: "/collect/isCollectPlate",
    method: "get",
    params: {
      userId: userId,
      plateId: plateId
    }
  });
}
export function deleteCollectTopic(userId, topicId) {
  return request({
    url: "/collect/deleteCollectTopic",
    method: "get",
    params: {
      userId: userId,
      topicId: topicId
    }
  });
}
export function collectTopic(data) {
  return request({
    url: "/collect/collectTopic",
    method: "post",
    data: data
  });
}
export function getCollectTopic(userId, index) {
  return request({
    url: "/collect/getCollectTopic",
    method: "get",
    params: {
      userId: userId,
      index: index
    }
  });
}

export function getCollectPlate(userId) {
  return request({
    url: "/collect/getCollectPlate",
    method: "get",
    params: {
      userId: userId
    }
  });
}

export function getCollectTopicCount(userId) {
  return request({
    url: "/collect/getCollectTopicCount",
    method: "get",
    params: {
      userId: userId
    }
  });
}
