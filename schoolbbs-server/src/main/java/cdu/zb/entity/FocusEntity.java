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
@TableName("focus")
public class FocusEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 被关注用户id
     */
    private String focusUserId;

    /**
     * 关注时间
     */
    private LocalDateTime focusTime;

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
    public String getFocusUserId() {
        return focusUserId;
    }

    public void setFocusUserId(String focusUserId) {
        this.focusUserId = focusUserId;
    }
    public LocalDateTime getFocusTime() {
        return focusTime;
    }

    public void setFocusTime(LocalDateTime focusTime) {
        this.focusTime = focusTime;
    }

    @Override
    public String toString() {
        return "FocusEntity{" +
            "id=" + id +
            ", userId=" + userId +
            ", focusUserId=" + focusUserId +
            ", focusTime=" + focusTime +
        "}";
    }
}
