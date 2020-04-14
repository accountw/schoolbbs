package cdu.zb.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import jdk.nashorn.internal.ir.annotations.Ignore;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author accountw
 * @since 2020-03-03
 */
@TableName("block")
public class BlockEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 模块名
     */
    private String name;

    /**
     * 逻辑删除
     */
    @Ignore
    @TableLogic
    private Integer logicDeleteFlag;

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

    public Integer getLogicDeleteFlag() {
        return logicDeleteFlag;
    }

    public void setLogicDeleteFlag(Integer logicDeleteFlag) {
        this.logicDeleteFlag = logicDeleteFlag;
    }

    @Override
    public String toString() {
        return "BlockEntity{" +
                "id=" + id +
                ", name=" + name +
                ", logicDeleteFlag=" + logicDeleteFlag +
                "}";
    }
}
