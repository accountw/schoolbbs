package cdu.zb.response;

import cdu.zb.entity.PlateAdminEntity;

/**
 * @author accountw
 * @date 2020-02-14 14:04
 * @description: plateadmin的返回封装类
 */
public class PlateAdminResponse extends PlateAdminEntity {

    private String username;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
