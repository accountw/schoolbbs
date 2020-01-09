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
 * @since 2020-01-09
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
    private LocalDateTime replyTime;

    /**
     * 贴主查看状态
     */
    private Integer status;

    /**
     * 被回复人查看
     */
    private Integer userStatus;

    /**
     * 逻辑删除
     */
    private Integer logicDeleteFlag;

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
        "}";
    }
}
