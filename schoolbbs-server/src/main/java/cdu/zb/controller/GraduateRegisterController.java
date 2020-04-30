package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.GraduateRegisterDto;
import cdu.zb.entity.GraduateRegisterEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.service.GraduateRegisterService;
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
 * @since 2020-01-27
 */
@RestController
@RequestMapping("/api/graduateRegister")
public class GraduateRegisterController extends BaseApiController {


    @Autowired
    private GraduateRegisterService graduateRegisterService;

    @PostMapping(value = "save",name = "保存注册表单")
    public JsonResult<Boolean> save(@RequestBody GraduateRegisterDto graduateRegisterDto){
        graduateRegisterDto.setAskTime(LocalDateTime.now());
        graduateRegisterDto.setStatus(0);
        return  jr(GlobalConstants.SUCCESS,"申请成功",graduateRegisterService.save(graduateRegisterDto));
    }

    @GetMapping(value = "getRegister",name="得到申请注册名单")
    public JsonResult<List<GraduateRegisterEntity>> getRegister(Integer index){
        index=index*15-15;
        return  jr(GlobalConstants.SUCCESS,"获取成功",graduateRegisterService.list(new QueryWrapper<GraduateRegisterEntity>().eq("status",0)
        .last("limit "+index+",15")));
    }

    @GetMapping(value = "deal",name="通过或者拒绝")
    public JsonResult<String> deal(String id,Integer status){
        GraduateRegisterEntity graduateRegisterEntity=graduateRegisterService.getById(id);
        if(status==1){
            graduateRegisterEntity.setStatus(1);
            graduateRegisterService.updateById(graduateRegisterEntity);
        }
        if(status==2){
            graduateRegisterEntity.setStatus(2);
            graduateRegisterService.updateById(graduateRegisterEntity);
        }
        return jr(GlobalConstants.SUCCESS,"处理成功");
    }
}
