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
@TableName("ban")
public class BanEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户id
     */
    private String uid;

    /**
     * 状态 0表示被封,1表示解封
     */
    private Integer status;

    /**
     * 处理人
     */
    private String dealer;

    /**
     * 处理时间
     */
    private LocalDateTime dealTime;

    /**
     * 解封时间
     */
    private LocalDateTime freeTime;

    /**
     * 封禁原因
     */
    private String reson;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getDealer() {
        return dealer;
    }

    public void setDealer(String dealer) {
        this.dealer = dealer;
    }
    public LocalDateTime getDealTime() {
        return dealTime;
    }

    public void setDealTime(LocalDateTime dealTime) {
        this.dealTime = dealTime;
    }
    public LocalDateTime getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(LocalDateTime freeTime) {
        this.freeTime = freeTime;
    }
    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    @Override
    public String toString() {
        return "BanEntity{" +
            "id=" + id +
            ", uid=" + uid +
            ", status=" + status +
            ", dealer=" + dealer +
            ", dealTime=" + dealTime +
            ", freeTime=" + freeTime +
            ", reson=" + reson +
        "}";
    }
}
