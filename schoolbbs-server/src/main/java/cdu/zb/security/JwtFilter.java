package cdu.zb.security;

import cdu.zb.mapper.BanMapper;
import cdu.zb.util.RedisUtil;
import cdu.zb.util.TokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

/**
 * @author accountw
 * @date 2020-02-01 18:54
 * @description: token验证类
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(JwtFilter.class);

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private BanMapper banMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String tokenHeader = request.getHeader(TokenUtil.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader == null || !tokenHeader.startsWith(TokenUtil.TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        // 如果请求头中有token，则进行解析，并且设置认证信息
        String token = tokenHeader.replace(TokenUtil.TOKEN_PREFIX, "");
        String username=TokenUtil.getUsername(token);
        if(redisUtil.get(username).equals(token)){
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(tokenHeader));
                chain.doFilter(request, response);
        }else{
            response.setStatus(401);
            return;
        }
    }

    // 这里从token中获取用户信息并新建一个token
    private UsernamePasswordAuthenticationToken getAuthentication(String tokenHeader) {
        String token = tokenHeader.replace(TokenUtil.TOKEN_PREFIX, "");
        String username = TokenUtil.getUsername(token);
        String role = TokenUtil.getAuthorization(token);
        if (username != null){
            return new UsernamePasswordAuthenticationToken(username, null,  Collections.singleton(new SimpleGrantedAuthority(role)));
        }
        return null;
    }

}
