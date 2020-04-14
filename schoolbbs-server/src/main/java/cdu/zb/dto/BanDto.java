package cdu.zb.dto;

import cdu.zb.entity.BanEntity;

/**
 * @author accountw
 * @date 2020-01-09 22:22
 * @description: ban类的封装
 */
public class BanDto extends BanEntity {
    private Integer date;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }
}
