package cdu.zb.security;

import cdu.zb.constants.GlobalConstants;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author accountw
 * @date 2020-02-01 18:55
 * @description: 权限不足处理类
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    private static final Logger LOG = LoggerFactory.getLogger(MyAccessDeniedHandler.class);
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        //登陆状态下，权限不足执行该方法
        LOG.info("权限不足：" + accessDeniedException.getMessage());
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject o=new JSONObject();
        o.put("code", GlobalConstants.ERROR);
        o.put("message","权限不足");
        response.getWriter().write(o.toString());
        response.getWriter().flush();
    }
}
