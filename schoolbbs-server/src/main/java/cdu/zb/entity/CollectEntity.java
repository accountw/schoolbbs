package cdu.zb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author accountw
 * @since 2020-04-14
 */
@TableName("collect")
public class CollectEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String topicId;

    private String plateId;

    private LocalDateTime collectTime;

    private Integer logicDeleteFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }
    public LocalDateTime getCollectTime() {
        return collectTime;
    }

    public void setCollectTime(LocalDateTime collectTime) {
        this.collectTime = collectTime;
    }
    public Integer getLogicDeleteFlag() {
        return logicDeleteFlag;
    }

    public void setLogicDeleteFlag(Integer logicDeleteFlag) {
        this.logicDeleteFlag = logicDeleteFlag;
    }

    @Override
    public String toString() {
        return "CollectEntity{" +
            "id=" + id +
            ", userId=" + userId +
            ", topicId=" + topicId +
            ", plateId=" + plateId +
            ", collectTime=" + collectTime +
            ", logicDeleteFlag=" + logicDeleteFlag +
        "}";
    }
}
