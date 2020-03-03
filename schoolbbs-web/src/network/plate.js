import { request } from "./request.js";

export function getplate() {
    return request({
        url: "/plate/getplate",
        method: "get",
    })

}