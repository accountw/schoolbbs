package cdu.zb.service.impl;

import cdu.zb.dto.UserDto;
import cdu.zb.entity.UserEntity;
import cdu.zb.mapper.UserMapper;
import cdu.zb.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private  UserMapper userMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    /**
     * @description: 注册
     * @author accountw
     * @date 2020/1/17 10:00
     * @return boolean
     **/
    @Override
    public boolean register(UserDto userDto) {
        userDto.setExp(1);
        userDto.setRegisterTime(LocalDateTime.now());
        userDto.setHead("../../assets/head/head.png");
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        if(userMapper.insert(userDto)!=0){
            return true;
        }
        return false;
    }
}
