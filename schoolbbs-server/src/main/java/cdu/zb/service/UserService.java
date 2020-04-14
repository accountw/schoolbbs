package cdu.zb.service;

import cdu.zb.dto.UserDto;
import cdu.zb.entity.UserEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */
public interface UserService extends IService<UserEntity> {

    
    
    /**
     * @description: 普通用户注册
     * @author accountw
     * @date 2020/1/16 14:41
     * @param [userDto]
     * @return void
     **/
    boolean register(UserDto userDto);

    Integer updateUser(UserDto userDto) throws UnsupportedEncodingException;

    Integer addExp(String id,Integer exp);
    Integer deleteExp(String id,Integer exp);

    Integer addcount(String id);
    Integer deletecount(String id);
}
