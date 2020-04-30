package cdu.zb.response;

import cdu.zb.entity.MessageEntity;

/**
 * @author accountw
 * @date 2020-04-27 21:32
 * @description: 消息
 */
public class MessageResponse extends MessageEntity {
    public String getMastername() {
        return mastername;
    }

    public void setMastername(String mastername) {
        this.mastername = mastername;
    }

    private String rTopicId;
    private String rContext;
    private String tTitle;
    private String username;
    private  String mastername;
    private  String head;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getrTopicId() {
        return rTopicId;
    }

    public void setrTopicId(String rTopicId) {
        this.rTopicId = rTopicId;
    }

    public String getrContext() {
        return rContext;
    }

    public void setrContext(String rContext) {
        this.rContext = rContext;
    }

    public String gettTitle() {
        return tTitle;
    }

    public void settTitle(String tTitle) {
        this.tTitle = tTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPeoplename() {
        return peoplename;
    }

    public void setPeoplename(String peoplename) {
        this.peoplename = peoplename;
    }

    private String uname;
    private String peoplename;
}
