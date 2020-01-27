package cdu.zb.security;

import cdu.zb.entity.UserEntity;
import cdu.zb.mapper.AdminMapper;
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       UserEntity userEntity=userMapper.selectOne(new QueryWrapper<UserEntity>().eq("username",username));
       //AdminEntity adminEntity=adminMapper.selectOne(new QueryWrapper<AdminEntity>().eq("username",username));
       MyUserDetails userDetails;
       if(userEntity==null){
//           if (adminEntity==null){
//               throw new UsernameNotFoundException(username);
//           }else{
//               userDetails=new MyUserDetails(adminEntity);
//               return userDetails;
//           }
           throw new UsernameNotFoundException(username);
       }
       userDetails=new MyUserDetails(userEntity);
       return userDetails;
     }
}
