package cdu.zb.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */


@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    /*
     * @description: 登录
     * @author accountw
     * @date 2020/1/10 11:25
     * @param [userDto]
     * @return cdu.zb.dto.UserDto
     **/
    @PostMapping(value = "/login", name = "登录")
    public String login(String id){
        LOG.debug(id);
        return "success";
    }
}
