package cdu.zb.response;

import cdu.zb.entity.TopicEntity;

/**
 * @author accountw
 * @date 2020-02-14 14:06
 * @description: topic的返回封装类
 */
public class TopicResponse extends TopicEntity {
    private String username;
    private String platename;
    private String blockname;
    private String depict;

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
