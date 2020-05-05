import { request } from "./request";

export function addFavorite(data) {
  return request({
    url: "/favorite/addFavorite",
    method: "post",
    data: data
  });
}

export function deleteReplyFavorite(userId, replyId) {
  return request({
    url: "/favorite/deleteReplyFavorite",
    method: "get",
    params: {
      userId: userId,
      replyId: replyId
    }
  });
}

export function deleteTopicFavorite(userId, topicId) {
  return request({
    url: "/favorite/deleteTopicFavorite",
    method: "get",
    params: {
      userId: userId,
      topicId: topicId
    }
  });
}

export function isTopicFavorite(userId, topicId) {
  return request({
    url: "/favorite/isTopicFavorite",
    method: "get",
    params: {
      userId: userId,
      topicId: topicId
    }
  });
}
export function isReplyFavorite(userId, replyId) {
  return request({
    url: "/favorite/isReplyFavorite",
    method: "get",
    params: {
      userId: userId,
      replyId: replyId
    }
  });
}
export function getReplyFavariteNum(replyId) {
  return request({
    url: "/favorite/getReplyFavariteNum",
    method: "get",
    params: {
      replyId: replyId
    }
  });
}
export function getTopicFavariteNum(topicId) {
  return request({
    url: "/favorite/getTopicFavariteNum",
    method: "get",
    params: {
      topicId: topicId
    }
  });
}

export function likeCricle(cricleId) {
  return request({
    url: "/favorite/likeCricle",
    method: "get",
    params: {
      cricleId: cricleId
    }
  });
}
export function selectLikeCricle(cricleId) {
  return request({
    url: "/favorite/selectLikeCricle",
    method: "get",
    params: {
      cricleId: cricleId
    }
  });
}
export function removeLikeCricle(cricleId) {
  return request({
    url: "/favorite/removeLikeCricle",
    method: "get",
    params: {
      cricleId: cricleId
    }
  });
}
