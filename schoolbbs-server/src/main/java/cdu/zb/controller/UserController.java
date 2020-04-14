package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.UserDto;
import cdu.zb.entity.CheckCodeEntity;
import cdu.zb.entity.UserEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.service.CheckCodeService;
import cdu.zb.service.UserService;
import cdu.zb.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Base64;


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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    /*
     * @description: 普通用户注册
     * @author accountw
     * @date 2020/1/16 14:37
     * @param [userDto],必须传入username,password,mail,exp,register_time
     * @return cdu.zb.jsonreslut.JsonResult<java.lang.String>
     **/
    @PostMapping(value = "/register", name = "注册")
    public JsonResult<String> register(@RequestBody  UserDto userDto) {
        if (userService.count(new QueryWrapper<UserEntity>().eq("username", userDto.getUsername())) != 0) {
            LOG.info("用户名:" + userDto.getUsername() + "已被占用");
            return jr(GlobalConstants.ERROR, "该用户名已被占用");
        }
        if (userService.count(new QueryWrapper<UserEntity>().eq("mail", userDto.getMail())) != 0) {
            LOG.info("邮箱:" + userDto.getMail() + "已被占用");
            return jr(GlobalConstants.ERROR, "邮箱已被占用");
        }
        if (userService.register(userDto)) {
            LOG.info("用户:" + userDto.getUsername() + "注册成功");
            return jr(GlobalConstants.SUCCESS, "注册成功");
        }
        LOG.info("用户注册失败");
        return jr(GlobalConstants.ERROR, "注册失败，请重试");
    }



    
    /**
     * @description: 查询邮箱是否重复
     * @author accountw
     * @date 2020/1/23 16:17
     * @param mail
     * @return cdu.zb.jsonresult.JsonResult<java.lang.String>
     **/
    @GetMapping(value = "/register/selectMail",name="查询邮箱是否重复")
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
     * @param username
     * @return cdu.zb.jsonresult.JsonResult<java.lang.String>
     **/
    @GetMapping(value = "/register/selectUsername",name="查询用户名是否重复")
    public JsonResult<String> selectUsername(String username){
        if(userService.count(new QueryWrapper<UserEntity>().eq("username",username))!=0){
            return jr(GlobalConstants.ERROR, "该用户名已被注册");
        }
        return jr(GlobalConstants.SUCCESS, "该用户名可用");
    }


    /**
     * @description: 查询验证码是否正确
     * @param userMail
     * @param code
     * @return
     */
    @GetMapping(value = "/register/selectCode",name="查询验证码是否正确")
    public JsonResult<String> selectCode(String userMail,String code){
        String checkCode=checkCodeService.getOne(new QueryWrapper<CheckCodeEntity>().eq("user_mail",userMail)).getCode();
        if(bCryptPasswordEncoder.matches(code.toUpperCase(),checkCode)){
            return jr(GlobalConstants.SUCCESS,"验证码正确");
        }
        return  jr(GlobalConstants.ERROR,"验证码错误");
    }

    /*
     * @description: 根据用户名得到用户
     * @author accountw
     * @date 2020/2/15 13:59
     * @param [username]
     * @return cdu.zb.jsonresult.JsonResult<cdu.zb.entity.UserEntity>
     **/
    @JsonIgnoreProperties({"password","mail"})
    @GetMapping(value="/getUser",name = "得到用户信息")
    public  JsonResult<UserEntity> getUser(String username) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        UserEntity userEntity=userService.getOne(new QueryWrapper<UserEntity>().eq("username",username));
       if(userEntity.getSign()!=null){
           userEntity.setSign(new String(decoder.decode(userEntity.getSign()),"UTF-8"));
       }
        return jr("获取成功",userEntity);
    }



    @GetMapping(value="/getUserByid",name = "得到用户信息")
    public  JsonResult<UserEntity> getUserByid(String id) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        UserEntity userEntity=userService.getOne(new QueryWrapper<UserEntity>().eq("id",id));
        if(userEntity.getSign()!=null){
            userEntity.setSign(new String(decoder.decode(userEntity.getSign()),"UTF-8"));
        }
        return jr("获取成功",userEntity);
    }

    @GetMapping(value ="/ceshi",name = "测试")
    public  JsonResult<String> ceshi(){
        return  jr(GlobalConstants.SUCCESS,"成功");
    }

    @PostMapping(value = "/saveHead")
    public JsonResult<String> saveHead(MultipartFile file){
// 文件保存路径
        String id= IdUtil.createID();
        String fileName = file.getOriginalFilename();
        String[] name=fileName.split("\\.");
        String a=name[name.length-1];
        String path="D:\\schoolbbs\\schoolbbs-web\\public\\head\\";
        String filePath = path + id+"."+a;
        if (!file.isEmpty()) {
            try {

                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  jr(GlobalConstants.SUCCESS,"保存成功",id+"."+a);
    }

    @PostMapping(value = "/updateUser",name = "更新")
    public  JsonResult<Integer> updateUser(@RequestBody UserDto userDto) throws UnsupportedEncodingException {
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        if(authentication.getPrincipal().equals(userDto.getUsername())) {
            return jr(GlobalConstants.SUCCESS, "更新成功", userService.updateUser(userDto));
        }else {
            return jr(GlobalConstants.ERROR, "更新失败");
        }
    }

    @GetMapping(value = "/changepd",name="更新密码")
    public JsonResult<Integer> changepd(String newpassword,String oldpassword){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntity userEntity=userService.getOne(new QueryWrapper<UserEntity>().eq("username",username));
        if(bCryptPasswordEncoder.matches(oldpassword,userEntity.getPassword())){
            userEntity.setPassword(bCryptPasswordEncoder.encode(newpassword));
            userService.updateById(userEntity);
            checkCodeService.remove(new QueryWrapper<CheckCodeEntity>().eq("user_mail",userEntity.getMail()));
            return jr(GlobalConstants.SUCCESS,"更新成功");
        }
        return jr(GlobalConstants.ERROR,"更新失败");
    }

}
