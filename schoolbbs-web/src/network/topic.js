import { request } from "./request.js";

export function getFirstTopic() {
  return request({
    url: "/topic/getFirstTopices",
    method: "get"
  });
}
