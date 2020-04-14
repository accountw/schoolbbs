package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.PlateDto;
import cdu.zb.entity.PlateEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.PlateResponse;
import cdu.zb.service.PlateService;
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
 * @since 2020-01-17
 */
@RestController
@RequestMapping("/api/plate")
public class PlateController extends BaseApiController {


    @Autowired
    private PlateService plateService;
    /**
     * @description: 获取板块信息
     * @author accountw
     * @date 2020/3/1 18:01
     * @return cdu.zb.jsonresult.JsonResult<cdu.zb.response.PlateResponse>
     **/
    @GetMapping(value = "/getplate",name = "获取板块")
    public JsonResult<List<PlateEntity>> getplate(String blockid){
        return jr(GlobalConstants.SUCCESS,"获取成功",plateService.getplate(blockid));
    }

    @GetMapping(value="/getPlateByid",name="获取板块")
    public JsonResult<PlateResponse> getPlateByid(String id){
        return jr(GlobalConstants.SUCCESS,"获取成功", plateService.getPlateByid(id));
    }

    @GetMapping(value = "/getlist",name = "获取板块")
    public JsonResult<List<PlateResponse>> getlist(){
        return jr(GlobalConstants.SUCCESS,"获取成功",plateService.getlist());
    }

    @GetMapping(value="/deleteplate",name="删除板块")
    public JsonResult<Boolean> deleteplate(String id){
        return jr(GlobalConstants.SUCCESS,"删除成功",plateService.removeById(id));
    }

    @PostMapping(value = "/updateplate",name="更新板块信息")
    public JsonResult<Boolean> updateplate(@RequestBody PlateDto plateDto){
        return jr(GlobalConstants.SUCCESS,"删除成功",plateService.updateById(plateDto));
    }
    @PostMapping(value = "/addplate",name = "添加板块")
    public JsonResult<Boolean> addplate(@RequestBody PlateDto plateDto){
        plateDto.setCreateTime(LocalDateTime.now());
        return jr(GlobalConstants.SUCCESS,"添加成功",plateService.save(plateDto));
    }
}
