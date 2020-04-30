package cdu.zb.security;

import cdu.zb.constants.GlobalConstants;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author accountw
 * @date 2020-02-01 18:56
 * @description: 异常处理类
 */
@Component
public class MyAuthenticationException implements AuthenticationEntryPoint {

    private static final Logger LOG = LoggerFactory.getLogger(MyAuthenticationException.class);
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        LOG.info("AuthenticationEntryPoint检测到异常:"+authException);
        if(authException.equals("org.springframework.security.authentication.InsufficientAuthenticationException: Full authentication is required to access this resource")){
            response.setStatus(403);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject o=new JSONObject();
            o.put("code", GlobalConstants.EXPIRED);
            o.put("message","登录过期");
            response.getWriter().write(o.toString());
            response.getWriter().flush();
        }
        else {
            response.setStatus(500);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            JSONObject o = new JSONObject();
            o.put("code", GlobalConstants.ERROR);
            o.put("message", "出现异常");
            response.getWriter().write(o.toString());
            response.getWriter().flush();
        }
    }
}
