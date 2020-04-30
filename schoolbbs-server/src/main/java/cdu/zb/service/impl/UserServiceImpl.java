package cdu.zb.service.impl;

import cdu.zb.dto.UserDto;
import cdu.zb.entity.UserEntity;
import cdu.zb.mapper.UserMapper;
import cdu.zb.response.UserResponse;
import cdu.zb.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;


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
        userDto.setHead("/head/head.png");
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        if(userMapper.insert(userDto)!=0){
            return true;
        }
        return false;
    }

    @Override
    public Integer updateUser(UserDto userDto) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        userDto.setSign(encoder.encodeToString(userDto.getSign().getBytes("UTF-8")));
        return  userMapper.updateById(userDto);
    }

    @Override
    public Integer addExp(String id, Integer exp) {
        UserEntity userEntity=userMapper.selectById(id);
        userEntity.setExp(userEntity.getExp()+exp);
        return userMapper.updateById(userEntity);
    }

    @Override
    public Integer deleteExp(String id, Integer exp) {
        UserEntity userEntity=userMapper.selectById(id);
        userEntity.setExp(userEntity.getExp()-exp);
        return userMapper.updateById(userEntity);
    }

    @Override
    public Integer addcount(String id) {
        UserEntity userEntity=userMapper.selectById(id);
        userEntity.setCount(userEntity.getCount()+1);
        return userMapper.updateById(userEntity);
    }

    @Override
    public Integer deletecount(String id) {
        UserEntity userEntity=userMapper.selectById(id);
        userEntity.setCount(userEntity.getCount()-1);
        return userMapper.updateById(userEntity);
    }

    @Override
    public List<UserResponse> getFocusList(String userId) {
        List<UserResponse> list=userMapper.getFocusList(userId);
       return  list;
    }

    @Override
    public List<UserResponse> getFansList(String userId) {
        List<UserResponse> list=userMapper.getFansList(userId);
        return  list;
    }
}
