package cdu.zb.response;

import cdu.zb.entity.PlateEntity;

/**
 * @author accountw
 * @date 2020-02-14 14:04
 * @description: plate的返回封装类
 */
public class PlateResponse extends PlateEntity {
    private String blockname;
    private Integer tcount;

    public Integer getTcount() {
        return tcount;
    }

    public void setTcount(Integer tcount) {
        this.tcount = tcount;
    }

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }
}
