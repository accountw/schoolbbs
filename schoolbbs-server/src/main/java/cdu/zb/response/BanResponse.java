package cdu.zb.response;

import cdu.zb.entity.BanEntity;

/**
 * @author accountw
 * @date 2020-02-14 12:26
 * @description: ban的返回封装类
 */
public class BanResponse extends BanEntity {

    private  String username;

    private  String dealUsername;

    public String getDealUsername() {
        return dealUsername;
    }

    public void setDealUsername(String dealUsername) {
        this.dealUsername = dealUsername;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
