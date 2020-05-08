package cdu.zb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @author accountw
 * @date 2020-01-16 17:25
 * @description: security配置类
 */
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    // 因为UserDetailsService的实现类实在太多啦，这里设置一下我们要注入的实现类
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtFilter jwtFilter;
    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private  SuccessHandler successHandler;
    @Autowired
    private  FailHander failHander;
    @Autowired
    private MyAuthenticationException myAuthenticationException;

    /**
     * @description: 密码加密
     * @author accountw
     * @date 2020/1/16 17:29
     * @param
     * @return org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
     **/
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .formLogin()
                .loginProcessingUrl("/api/user/login")
                .successHandler(successHandler)
                .failureHandler(failHander)
                .and()
                .cors()
                .and()
                .csrf().disable()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/api/user/register/**","/api/checkcode/**",
                        "/api/topic/getTopicFlow","/api/topic/getFirstCount","/api/topic/getTop","/api/schoolregister/**",
                        "/api/graduateRegister/**","/api/user/changepassword").permitAll()
                .antMatchers("/api/topic/deleteTopicbyadmin","api/ban/addBan")
                .hasRole("MANAGER")
                .antMatchers("/api/ban/getBanList","/api/ban/getcount","/api/ban/deleteBan",
                        "/api/plateAdmin/deleteAdmin","/api/plateAdmin/addAdmin","/api/plateAdmin/getcount","/api/plateAdmin/listAdmin",
                        "/api/plate/getlist","/api/plateAdmin/setadmin", "/api/plate/addplate",
                        "/api/plate/deleteplate")
                .hasRole("ADMIN")
                // 除上面外的所有请求全部需要鉴权认证
                .antMatchers( "/api/plate/addplate")
                .hasAnyRole("ADMIN","MANAGER")
                .anyRequest().authenticated();

        // 禁用缓存
        httpSecurity.headers().cacheControl();

        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtFilter, LogoutFilter.class)
                // 添加权限不足 filter
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                //其他异常处理类
                .authenticationEntryPoint(myAuthenticationException);

    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}
