package cdu.zb.security;

import cdu.zb.constants.GlobalConstants;
import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author accountw
 * @date 2020-02-01 18:49
 * @description: 登录失败处理
 */
@Component
public class FailHander extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        logger.info("用户登陆失败");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject o=new JSONObject();
        o.put("code", GlobalConstants.ERROR);
        o.put("message","登录失败");
        response.getWriter().write(o.toString());
    }
}
