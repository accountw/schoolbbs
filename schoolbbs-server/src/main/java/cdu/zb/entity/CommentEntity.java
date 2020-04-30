package cdu.zb.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author accountw
 * @since 2020-03-31
 */
@TableName("comment")
public class CommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 回复_id
     */
    private String replyId;

    /**
     * 回复人id
     */
    private String userId;

    /**
     * 被回复人id
     */
    private String replyUserId;

    /**
     * 回复时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime replyTime;

    /**
     * 贴主查看状态
     */

    @JsonIgnore
    private Integer status;

    /**
     * 被回复人查看
     */

    @JsonIgnore
    private Integer userStatus;

    /**
     * 逻辑删除
     */
    @TableLogic
    @Ignore
    @JsonIgnore
    private Integer logicDeleteFlag;

    private String context;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
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
    public LocalDateTime getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(LocalDateTime replyTime) {
        this.replyTime = replyTime;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }
    public Integer getLogicDeleteFlag() {
        return logicDeleteFlag;
    }

    public void setLogicDeleteFlag(Integer logicDeleteFlag) {
        this.logicDeleteFlag = logicDeleteFlag;
    }
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
            "id=" + id +
            ", replyId=" + replyId +
            ", userId=" + userId +
            ", replyUserId=" + replyUserId +
            ", replyTime=" + replyTime +
            ", status=" + status +
            ", userStatus=" + userStatus +
            ", logicDeleteFlag=" + logicDeleteFlag +
            ", context=" + context +
        "}";
    }
}
