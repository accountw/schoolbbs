package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.entity.CheckCodeEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.service.CheckCodeService;
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
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-01-27
 */
@RestController
@RequestMapping("/api/checkcode")
public class CheckCodeController extends BaseApiController {

    private static final Logger LOG = LoggerFactory.getLogger(CheckCodeController.class);

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CheckCodeService checkCodeService;

    @Autowired
    private MailServiceImpl mailService;


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
        checkCodeEntity.setCode(code.toUpperCase());
        checkCodeEntity.setUserMail(mail);
        checkCodeEntity.setCreateTime(LocalDateTime.now());

        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        mailService.sendSimpleMailMessge(mail, subject, content);
                    }
                }).start();

        if(checkCodeService.count(new QueryWrapper<CheckCodeEntity>().eq("user_mail",mail))==0){
            checkCodeService.save(checkCodeEntity);
        }else{
            checkCodeService.update(checkCodeEntity,new QueryWrapper<CheckCodeEntity>().eq("user_mail",mail));
        }
        return jr(GlobalConstants.SUCCESS,"验证码发送成功");
    }

    /**
     * @description: 查询验证码是否正确
     * @author accountw
     * @date 2020/1/29 16:44
     * @param [userMail, code]
     * @return cdu.zb.jsonresult.JsonResult<java.lang.String>
     **/
    @GetMapping(value = "/selectCode",name="查询验证码是否正确")
    public JsonResult<String> selectCode(String userMail,String code){
        String checkCode=checkCodeService.getOne(new QueryWrapper<CheckCodeEntity>().eq("user_mail",userMail)).getCode();
        if(checkCode==null){
            return  jr(GlobalConstants.ERROR,"验证码错误");
        }
        if(code.toUpperCase().equals(checkCode)){
            return jr(GlobalConstants.SUCCESS,"验证码正确");
        }
        LOG.info(code.toUpperCase());
        return  jr(GlobalConstants.ERROR,"验证码错误");
    }

}
