package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.SchoolRegisterDto;
import cdu.zb.entity.SchoolRegisterEntity;
import cdu.zb.entity.UserEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.service.SchoolRegisterService;
import cdu.zb.service.UserService;
import cdu.zb.service.impl.MailServiceImpl;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */
@RestController
@RequestMapping("/api/schoolregister")
@CrossOrigin
public class SchoolRegisterController extends BaseApiController {

    private static final Logger LOG = LoggerFactory.getLogger(SchoolRegisterController.class);

    @Autowired
    private SchoolRegisterService schoolRegisterService;
    @Autowired
    private MailServiceImpl mailService;
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;



    @PostMapping(value = "save",name = "保存注册表单")
    public JsonResult<Boolean>  save(@RequestBody SchoolRegisterDto schoolRegisterDto){
        schoolRegisterDto.setAskTime(LocalDateTime.now());
        schoolRegisterDto.setStatus(0);
      return  jr(GlobalConstants.SUCCESS,"申请成功",schoolRegisterService.save(schoolRegisterDto));
    }

    @GetMapping(value = "getRegister",name="得到申请注册名单")
    public JsonResult<List<SchoolRegisterEntity>> getRegister(Integer index){
        index=index*15-15;
        return  jr(GlobalConstants.SUCCESS,"获取成功",schoolRegisterService.list(new QueryWrapper<SchoolRegisterEntity>().eq("status",0)
                .last("limit "+index+",15")));
    }

    @GetMapping(value = "deal",name="通过或者拒绝")
    public JsonResult<String> deal(String id,Integer status){
        SchoolRegisterEntity schoolRegisterEntity=schoolRegisterService.getById(id);
        if(status==1){
            schoolRegisterEntity.setStatus(1);
            schoolRegisterService.updateById(schoolRegisterEntity);
            String subject="校园论坛";
            String content="恭喜你注册通过";
            UserEntity userEntity=new UserEntity();
            userEntity.setExp(1);
            userEntity.setRegisterTime(LocalDateTime.now());
            userEntity.setHead("/head/head.png");
            userEntity.setPassword(bCryptPasswordEncoder.encode(schoolRegisterEntity.getPassword()));
            userEntity.setUsername(schoolRegisterEntity.getUsername());
            userEntity.setBirth(schoolRegisterEntity.getBirth());
            userEntity.setMail(schoolRegisterEntity.getMail());
            userEntity.setCount(0);
            userService.save(userEntity);
            LOG.debug("注册成功");
//            new Thread(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            mailService.sendSimpleMailMessge(schoolRegisterEntity.getMail(), subject, content);
//                        }
//                    }).start();
        }
        if(status==2){
            schoolRegisterEntity.setStatus(2);
            schoolRegisterService.updateById(schoolRegisterEntity);
            String subject="校园论坛";
            String content="你的账号注册失败，请重新注册";
            LOG.debug("注册失败");
//            new Thread(
//                    new Runnable() {
//                        @Override
//                        public void run() {
//                            mailService.sendSimpleMailMessge(schoolRegisterEntity.getMail(), subject, content);
//                        }
//                    }).start();
        }
        return jr(GlobalConstants.SUCCESS,"处理成功");
    }

    @GetMapping(value = "getcount",name = "得到数量")
    public JsonResult<Integer> getcount(){
        return jr(GlobalConstants.SUCCESS,"获取成功",schoolRegisterService.count());
    }
}
