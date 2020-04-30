package cdu.zb.response;

import cdu.zb.entity.CollectEntity;

/**
 * @author accountw
 * @date 2020-04-14 21:33
 * @description: 收藏
 */
public class CollectResponse extends CollectEntity {
    private  String platename;
    private String topicname;

    public String getPlatename() {
        return platename;
    }

    public void setPlatename(String platename) {
        this.platename = platename;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }
}
