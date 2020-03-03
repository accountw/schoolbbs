package cdu.zb.security;

import cdu.zb.constants.GlobalConstants;
import cdu.zb.util.RedisUtil;
import cdu.zb.util.TokenUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * @author accountw
 * @date 2020-02-01 18:28
 * @description: 登录成功的处理
 */
@Component
public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private FailHander failHander;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是User啦
        MyUserDetails User = (MyUserDetails) authentication.getPrincipal();
        logger.info("User:" + User.getUsername()+"登陆成功");
        String role = "";
        // 因为在JwtUser中存了权限信息，可以直接获取，由于只有一个角色就这么干了
        Collection<? extends GrantedAuthority> authorities = User.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }

        // 根据用户名，角色创建token
        String token = TokenUtil.createToken(User.getUsername(), role, false);
        redisUtil.set(User.getUsername(),token);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject o=new JSONObject();
        o.put("code", GlobalConstants.SUCCESS);
        o.put("message",TokenUtil.TOKEN_PREFIX + token);
        response.getWriter().write(o.toString());
    }
}
