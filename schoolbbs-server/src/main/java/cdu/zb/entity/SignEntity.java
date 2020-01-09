package cdu.zb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */
@TableName("sign")
public class SignEntity implements Serializable {

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
     * 签到时间
     */
    private LocalDate signTime;

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
    public LocalDate getSignTime() {
        return signTime;
    }

    public void setSignTime(LocalDate signTime) {
        this.signTime = signTime;
    }

    @Override
    public String toString() {
        return "SignEntity{" +
            "id=" + id +
            ", userId=" + userId +
            ", signTime=" + signTime +
        "}";
    }
}
