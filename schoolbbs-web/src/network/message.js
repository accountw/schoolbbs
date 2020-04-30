import { request } from "./request";

export function getmessage(userid, index) {
  return request({
    url: "/message/getMessage",
    method: "get",
    params: {
      userid: userid,
      index: index
    }
  });
}

export function getcount(userid) {
  return request({
    url: "/message/getcount",
    method: "get",
    params: {
      userid: userid
    }
  });
}
