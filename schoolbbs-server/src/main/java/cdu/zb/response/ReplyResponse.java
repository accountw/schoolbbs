package cdu.zb.response;

import cdu.zb.entity.ReplyEntity;

/**
 * @author accountw
 * @date 2020-02-14 14:05
 * @description: reply的返回封装类
 */
public class ReplyResponse extends ReplyEntity {
     private String username;
     private Integer exp;
     private String head;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private Integer count;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getExp() {
        return exp;
    }

    public void setExp(Integer exp) {
        this.exp = exp;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }
}
