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
@TableName("plate_admin")
public class PlateAdminEntity implements Serializable {

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
     * 0为版主，1为副版主
     */
    private Integer position;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

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
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "PlateAdminEntity{" +
            "id=" + id +
            ", useId=" + useId +
            ", plateId=" + plateId +
            ", position=" + position +
            ", createTime=" + createTime +
        "}";
    }
}
