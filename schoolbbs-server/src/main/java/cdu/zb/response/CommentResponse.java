package cdu.zb.response;

import cdu.zb.entity.CommentEntity;

/**
 * @author accountw
 * @date 2020-02-14 12:27
 * @description: comment的返回封装类
 */
public class CommentResponse extends CommentEntity {
    private String username;
    private  String replyUsername;

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    private  String head;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReplyUsername() {
        return replyUsername;
    }

    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }
}
