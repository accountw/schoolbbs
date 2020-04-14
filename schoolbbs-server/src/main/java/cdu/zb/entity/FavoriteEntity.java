package cdu.zb.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
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
    private String useId;

    /**
     * 点赞时间
     */
    @TableLogic
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
    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
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
            ", useId=" + useId +
            ", likeTime=" + likeTime +
        "}";
    }
}
