package cdu.zb.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */
@TableName("favorite")
public class FavoriteEntity implements Serializable {

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
     * 帖子_id
     */
    private String topicId;

    /**
     * 用户_id
     */

    private String userId;

    public String getCricleId() {
        return cricleId;
    }

    public void setCricleId(String cricleId) {
        this.cricleId = cricleId;
    }

    private String cricleId;
    /**
     * 点赞时间
     */
    private LocalDateTime likeTime;

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
    public String getTopicId() {
        return topicId;
    }

    public void setTopicId(String topicId) {
        this.topicId = topicId;
    }
    public String getUserId() {
        return userId;
    }

    public void setUserId(String useId) {
        this.userId = useId;
    }
    public LocalDateTime getLikeTime() {
        return likeTime;
    }

    public void setLikeTime(LocalDateTime likeTime) {
        this.likeTime = likeTime;
    }

    @Override
    public String toString() {
        return "FavoriteEntity{" +
            "id=" + id +
            ", replyId=" + replyId +
            ", topicId=" + topicId +
            ", userId=" + userId +
            ", likeTime=" + likeTime +
        "}";
    }
}
