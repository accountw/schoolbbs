package cdu.zb.security;

import cdu.zb.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

/**
 * @author accountw
 * @date 2020-01-15 14:03
 * @description: MyUser
 */
public class MyUserDetails  implements UserDetails {



    private String username;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;


    public  MyUserDetails(UserEntity userEntity,String role){
        username=userEntity.getUsername();
        password=userEntity.getPassword();
        authorities = Collections.singleton(new SimpleGrantedAuthority(role));
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    
    /*
     * @description: 账号是否为过期
     * @author accountw
     * @date 2020/1/15 14:21
     * @param []
     * @return boolean
     **/
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * @description: 账号是否未锁定
     * @author accountw
     * @date 2020/1/15 14:21
     * @param []
     * @return boolean
     **/
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    
    /*
     * @description: 账号凭证是否未过期
     * @author accountw
     * @date 2020/1/15 14:22
     * @param []
     * @return boolean
     **/
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
