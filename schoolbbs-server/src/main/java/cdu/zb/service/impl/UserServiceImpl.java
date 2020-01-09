package cdu.zb.service.impl;

import cdu.zb.entity.UserEntity;
import cdu.zb.mapper.UserMapper;
import cdu.zb.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
