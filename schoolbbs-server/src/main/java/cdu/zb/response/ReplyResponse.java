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
     private Integer likenum;

    @Override
    public Integer getLikenum() {
        return likenum;
    }

    @Override
    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    private String head;
     private  String sign;

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
