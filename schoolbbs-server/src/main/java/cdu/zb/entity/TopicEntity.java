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
 * @since 2020-02-10
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
    private String userId;

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
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime firstTime;

    /**
     * 最后回复时间
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime lastTime;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    /**
     * 逻辑删除
     */

    @JsonIgnore
    @TableLogic
    private Integer logicDeleteFlag;

    private  Integer count;

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
            ", userId=" + userId +
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
