package cdu.zb.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author accountw
 * @since 2020-01-17
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

    @JsonIgnore
    private LocalDateTime createTime;

    /**
     * 逻辑删除
     */

    @JsonIgnore
    @TableLogic
    private Integer logicDeleteFlag;


    /**
     * 描述
     */
    private String depict ;

    public String getDepict() {
        return depict ;
    }

    public void setDepict(String depict) {
        this.depict = depict;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    /**
     *模块id
     */
    private String blockId;


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
    public Integer getLogicDeleteFlag() {
        return logicDeleteFlag;
    }

    public void setLogicDeleteFlag(Integer logicDeleteFlag) {
        this.logicDeleteFlag = logicDeleteFlag;
    }

    @Override
    public String toString() {
        return "PlateEntity{" +
            "id=" + id +
            ", name=" + name +
            ", createTime=" + createTime +
            ", logicDeleteFlag=" + logicDeleteFlag +
                ",depict "+depict+
        "}";
    }
}
