package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.SchoolRegisterDto;
import cdu.zb.entity.SchoolRegisterEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.service.SchoolRegisterService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
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


    @Autowired
    private SchoolRegisterService schoolRegisterService;


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
        }
        if(status==2){
            schoolRegisterEntity.setStatus(2);
            schoolRegisterService.updateById(schoolRegisterEntity);
        }
        return jr(GlobalConstants.SUCCESS,"处理成功");
    }
}
