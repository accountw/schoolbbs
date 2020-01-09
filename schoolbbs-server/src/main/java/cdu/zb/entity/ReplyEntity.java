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
@TableName("reply")
public class ReplyEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 帖子_id
     */
    private String topicId;

    /**
     * 用户_id
     */
    private String useId;

    /**
     * 内容
     */
    private String context;

    /**
     * 图片
     */
    private String picture;

    /**
     * 回复时间
     */
    private LocalDateTime replyTime;

    /**
     * 贴主查看状态
     */
    private Integer status;

    /**
     * 楼层
     */
    private Integer storey;

    /**
     * 逻辑删除
     */
    private Integer logicDeleteFlag2;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
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
    public Integer getStorey() {
        return storey;
    }

    public void setStorey(Integer storey) {
        this.storey = storey;
    }
    public Integer getLogicDeleteFlag2() {
        return logicDeleteFlag2;
    }

    public void setLogicDeleteFlag2(Integer logicDeleteFlag2) {
        this.logicDeleteFlag2 = logicDeleteFlag2;
    }

    @Override
    public String toString() {
        return "ReplyEntity{" +
            "id=" + id +
            ", topicId=" + topicId +
            ", useId=" + useId +
            ", context=" + context +
            ", picture=" + picture +
            ", replyTime=" + replyTime +
            ", status=" + status +
            ", storey=" + storey +
            ", logicDeleteFlag2=" + logicDeleteFlag2 +
        "}";
    }
}
