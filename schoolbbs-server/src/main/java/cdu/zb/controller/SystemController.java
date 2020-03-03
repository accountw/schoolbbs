package cdu.zb.controller;

import cdu.zb.constants.GlobalConstants;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.util.RedisUtil;
import cdu.zb.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * @author accountw
 * @date 2020-02-18 16:45
 * @description: 系统控制器
 */
@RestController
@RequestMapping("/api/system")
@CrossOrigin
public class SystemController extends BaseApiController {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 重新获取token
     * @return
     */
    @GetMapping(value = "/token/reload",name = "重新获取token")
    public JsonResult<String>  reload(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();

        String role = "";
        // 因为在JwtUser中存了权限信息，可以直接获取，由于只有一个角色就这么干了
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities){
            role = authority.getAuthority();
        }
        // 根据用户名，角色创建token
        String token = TokenUtil.createToken(authentication.getName(), role, false);
        redisUtil.set(authentication.getName(),token);
        return jr(GlobalConstants.RELOAD,TokenUtil.TOKEN_PREFIX+token);
    }

    /**
     * @description: 注销
     * @author accountw
     * @date 2020/3/1 17:59
     * @param []
     * @return cdu.zb.jsonresult.JsonResult<java.lang.String>
     **/
    @PostMapping(value = "/loginout" ,name = "注销")
    public JsonResult<String> loginout(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        redisUtil.del(authentication.getName());
        return jr(GlobalConstants.SUCCESS,"注销成功");
    }

}
