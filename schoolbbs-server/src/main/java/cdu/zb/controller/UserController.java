package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.UserDto;
import cdu.zb.entity.CheckCodeEntity;
import cdu.zb.entity.UserEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.service.CheckCodeService;
import cdu.zb.service.UserService;
import cdu.zb.service.impl.MailServiceImpl;
import cdu.zb.util.CodeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */


@RestController
@RequestMapping("/api/user")
@CrossOrigin
public class UserController extends BaseApiController {

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CheckCodeService checkCodeService;

    @Autowired
    private MailServiceImpl mailService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
     * @description: 普通用户注册
     * @author accountw
     * @date 2020/1/16 14:37
     * @param [userDto],必须传入username,password,mail,exp,register_time
     * @return cdu.zb.jsonreslut.JsonResult<java.lang.String>
     **/
    @PostMapping(value = "/register", name = "注册")
    public JsonResult<String> register(@RequestBody UserDto userDto) {
        if (userService.count(new QueryWrapper<UserEntity>().eq("username", userDto.getUsername())) != 0) {
            LOG.info("用户名:" + userDto.getUsername() + "已被占用");
            return jr(GlobalConstants.ERROR, "该用户名已被占用");
        }
        if (userService.register(userDto)) {
            LOG.info("用户:" + userDto.getUsername() + "注册成功");
            return jr(GlobalConstants.SUCCESS, "注册成功");
        }
        LOG.info("用户注册失败");
        return jr(GlobalConstants.ERROR, "注册失败，请重试");
    }


    /**
     * @param mail
     * @return cdu.zb.jsonresult.JsonResult<java.lang.String>
     * @description: 邮件验证码发送
     * @author accountw
     * @date 2020/1/22 18:32
     **/
    @PostMapping(value = "/sendMessage", name = "发送短信")
    public JsonResult<String> sendMessage(@RequestBody String mail) {
        String code = CodeUtil.getCode();
        LOG.debug(code);
        String subject = "注册验证码";
        String content = "亲爱的用户你好，你的注册验证码为:" + code;
        CheckCodeEntity checkCodeEntity=new CheckCodeEntity();
        checkCodeEntity.setCode(bCryptPasswordEncoder.encode(code.toUpperCase()));
        checkCodeEntity.setUserMail(mail);
        checkCodeEntity.setCreateTime(LocalDateTime.now());

//        new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        mailService.sendSimpleMailMessge(mail, subject, content);
//                    }
//                }).start();

        if(checkCodeService.count(new QueryWrapper<CheckCodeEntity>().eq("user_mail",mail))==0){
            checkCodeService.save(checkCodeEntity);
        }else{
            checkCodeService.update(checkCodeEntity,new QueryWrapper<CheckCodeEntity>().eq("user_mail",mail));
        }
        return jr(GlobalConstants.SUCCESS,"验证码发送成功");
    }
    
    /**
     * @description: 查询邮箱是否重复
     * @author accountw
     * @date 2020/1/23 16:17
     * @param mail
     * @return cdu.zb.jsonresult.JsonResult<java.lang.String>
     **/
    @GetMapping(value = "/selectMail",name="查询邮箱是否重复")
    public JsonResult<String> selectMail(String mail){
        if(userService.count(new QueryWrapper<UserEntity>().eq("mail",mail))!=0){
            return jr(GlobalConstants.ERROR, "该邮箱已被注册");
        }
        return jr(GlobalConstants.SUCCESS, "该邮箱可用");
    }

    /**
     * @description: 查询用户名是否重复
     * @author accountw
     * @date 2020/1/27 15:59
     * @param [username]
     * @return cdu.zb.jsonresult.JsonResult<java.lang.String>
     **/
    @GetMapping(value = "/selectUsername",name="查询用户名是否重复")
    public JsonResult<String> selectUsername(String username){
        if(userService.count(new QueryWrapper<UserEntity>().eq("username",username))!=0){
            return jr(GlobalConstants.ERROR, "该用户名已被注册");
        }
        return jr(GlobalConstants.SUCCESS, "该用户名可用");
    }


    @GetMapping(value = "/selectCode",name="查询验证码是否正确")
    public JsonResult<String> selectCode(String userMail,String code){
        String checkCode=checkCodeService.getOne(new QueryWrapper<CheckCodeEntity>().eq("user_mail",userMail)).getCode();
        if(bCryptPasswordEncoder.matches(code.toUpperCase(),checkCode)){
            return jr(GlobalConstants.SUCCESS,"验证码正确");
        }
        return  jr(GlobalConstants.ERROR,"验证码错误");
    }

}
