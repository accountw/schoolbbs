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
