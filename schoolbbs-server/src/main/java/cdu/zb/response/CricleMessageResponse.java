package cdu.zb.response;

import cdu.zb.entity.CricleMessageEntity;

/**
 * @author accountw
 * @date 2020-05-04 17:26
 * @description: 消息
 */
public class CricleMessageResponse extends CricleMessageEntity {

    private String username;
    private String rusername;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRusername() {
        return rusername;
    }

    public void setRusername(String rusername) {
        this.rusername = rusername;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    private String head;
}
