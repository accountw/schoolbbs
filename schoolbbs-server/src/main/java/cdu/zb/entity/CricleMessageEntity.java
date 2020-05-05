package cdu.zb.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author accountw
 * @since 2020-05-03
 */
@TableName("cricle_message")
public class CricleMessageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String replyUserId;

    private String context;

    private String cricleId;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime createTime;

    @JsonIgnore
    @TableLogic
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
    public String getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(String replyUserId) {
        this.replyUserId = replyUserId;
    }
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
    public String getCricleId() {
        return cricleId;
    }

    public void setCricleId(String cricleId) {
        this.cricleId = cricleId;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Integer getLogicDeleteFlag() {
        return logicDeleteFlag;
    }

    public void setLogicDeleteFlag(Integer logicDeleteFlag) {
        this.logicDeleteFlag = logicDeleteFlag;
    }

    @Override
    public String toString() {
        return "CricleMessageEntity{" +
            "id=" + id +
            ", userId=" + userId +
            ", replyUserId=" + replyUserId +
            ", context=" + context +
            ", cricleId=" + cricleId +
            ", createTime=" + createTime +
            ", logicDeleteFlag=" + logicDeleteFlag +
        "}";
    }
}
