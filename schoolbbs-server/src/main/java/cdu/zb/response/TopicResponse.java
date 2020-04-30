package cdu.zb.response;

import cdu.zb.entity.TopicEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author accountw
 * @date 2020-02-14 14:06
 * @description: topic的返回封装类
 */
public class TopicResponse extends TopicEntity {
    private String username;
    private String platename;
    private String blockname;
    private Integer likenum;

    @JsonIgnore
    private double number;

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

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

    private String depict;
    private String sign;

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

    private Integer exp;
    private String head;

    public String getDepict() {
        return depict;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }



    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPlatename() {
        return platename;
    }

    public void setPlatename(String platename) {
        this.platename = platename;
    }
}
