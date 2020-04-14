package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.BanDto;
import cdu.zb.entity.BanEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.BanResponse;
import cdu.zb.service.BanService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/ban")
@CrossOrigin
public class BanController  extends BaseApiController {

    @Autowired
    private  BanService banService;


    @GetMapping(value="/getBanList",name="得到封禁列表")
    public JsonResult<List<BanResponse>> getBanList(Integer index){
        return jr(GlobalConstants.SUCCESS,"获取成功",banService.getBanList(index));
    }

    @GetMapping(value = "/getcount",name="得到封禁的数量")
    public JsonResult<Integer> getcount(){
        return jr(GlobalConstants.SUCCESS,"获取成功",banService.count());
    }


    @GetMapping(value = "/deleteBan",name="解封")
    public JsonResult<Boolean> deleteBan(String id){
        return jr(GlobalConstants.SUCCESS,"解封成功",banService.remove(new QueryWrapper<BanEntity>().eq("id",id)));
    }

    @PostMapping(value = "/addBan",name="提交封禁申请")
    public JsonResult<Integer>  addBan(@RequestBody BanDto banDto){
        if(banService.addBan(banDto)==0){
            return jr(GlobalConstants.ERROR,"提交失败");
        }
        return jr(GlobalConstants.SUCCESS,"提交成功");
    }

}
