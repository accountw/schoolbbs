package cdu.zb.response;

import cdu.zb.entity.CricleEntity;

/**
 * @author accountw
 * @date 2020-05-03 16:29
 * @description: 圈子
 */
public class CricleResponse extends CricleEntity {

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    private String head;
}
