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
@TableName("topic")
public class TopicEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户_id
     */
    private String useId;

    /**
     * 板块_id
     */
    private String plateId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String context;

    /**
     * 图片
     */
    private String picture;

    /**
     * 点赞数
     */
    private Integer likenum;

    /**
     * 回复数
     */
    private Integer replynum;

    /**
     * 发帖时间
     */
    private LocalDateTime firstTime;

    /**
     * 最后回复时间
     */
    private LocalDateTime lastTime;

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
    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }
    public String getPlateId() {
        return plateId;
    }

    public void setPlateId(String plateId) {
        this.plateId = plateId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
    public Integer getLikenum() {
        return likenum;
    }

    public void setLikenum(Integer likenum) {
        this.likenum = likenum;
    }
    public Integer getReplynum() {
        return replynum;
    }

    public void setReplynum(Integer replynum) {
        this.replynum = replynum;
    }
    public LocalDateTime getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(LocalDateTime firstTime) {
        this.firstTime = firstTime;
    }
    public LocalDateTime getLastTime() {
        return lastTime;
    }

    public void setLastTime(LocalDateTime lastTime) {
        this.lastTime = lastTime;
    }
    public Integer getLogicDeleteFlag() {
        return logicDeleteFlag;
    }

    public void setLogicDeleteFlag(Integer logicDeleteFlag) {
        this.logicDeleteFlag = logicDeleteFlag;
    }

    @Override
    public String toString() {
        return "TopicEntity{" +
            "id=" + id +
            ", useId=" + useId +
            ", plateId=" + plateId +
            ", title=" + title +
            ", context=" + context +
            ", picture=" + picture +
            ", likenum=" + likenum +
            ", replynum=" + replynum +
            ", firstTime=" + firstTime +
            ", lastTime=" + lastTime +
            ", logicDeleteFlag=" + logicDeleteFlag +
        "}";
    }
}
