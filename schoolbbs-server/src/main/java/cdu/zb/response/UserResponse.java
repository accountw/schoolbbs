package cdu.zb.response;

import cdu.zb.entity.UserEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author accountw
 * @date 2020-02-14 14:07
 * @description: user的返回封装类
 */
@JsonIgnoreProperties("password")
public class UserResponse extends UserEntity {
}
