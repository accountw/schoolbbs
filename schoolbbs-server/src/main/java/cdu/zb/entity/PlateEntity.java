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
@TableName("plate")
public class PlateEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 板块名
     */
    private String name;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

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
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
    public Integer getLogicDeleteFlag2() {
        return logicDeleteFlag2;
    }

    public void setLogicDeleteFlag2(Integer logicDeleteFlag2) {
        this.logicDeleteFlag2 = logicDeleteFlag2;
    }

    @Override
    public String toString() {
        return "PlateEntity{" +
            "id=" + id +
            ", name=" + name +
            ", createTime=" + createTime +
            ", logicDeleteFlag2=" + logicDeleteFlag2 +
        "}";
    }
}
