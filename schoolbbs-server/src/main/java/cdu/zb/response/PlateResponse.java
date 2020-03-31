package cdu.zb.response;

import cdu.zb.entity.PlateEntity;

/**
 * @author accountw
 * @date 2020-02-14 14:04
 * @description: plate的返回封装类
 */
public class PlateResponse extends PlateEntity {
    private String blockname;

    public String getBlockname() {
        return blockname;
    }

    public void setBlockname(String blockname) {
        this.blockname = blockname;
    }
}
