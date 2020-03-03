package cdu.zb.security;

import cdu.zb.entity.AdminEntity;
import cdu.zb.entity.PlateAdminEntity;
import cdu.zb.entity.UserEntity;
import cdu.zb.mapper.AdminMapper;
import cdu.zb.mapper.PlateAdminMapper;
import cdu.zb.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author accountw
 * @date 2020-01-15 14:27
 * @description: UserDetailsService的实现类
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private PlateAdminMapper plateAdminMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity userEntity=userMapper.selectOne(new QueryWrapper<UserEntity>().eq("username",username));
       AdminEntity adminEntity=adminMapper.selectOne(new QueryWrapper<AdminEntity>().eq("username",username));
       MyUserDetails userDetails;
       if(userEntity==null){
           throw new UsernameNotFoundException(username);
       }
       if(adminEntity!=null){
           userDetails=new MyUserDetails(userEntity,"ROLE_ADMIN");
           return userDetails;
       }
       if(plateAdminMapper.selectCount(new QueryWrapper<PlateAdminEntity>().eq("user_id",userEntity.getId()))!=0){
           userDetails=new MyUserDetails(userEntity,"ROLE_MANAGER");
       }else {
           userDetails=new MyUserDetails(userEntity,"ROLR_USER");
       }
       return userDetails;
     }
}
