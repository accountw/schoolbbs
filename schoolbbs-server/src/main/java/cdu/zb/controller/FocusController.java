package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.FocusDto;
import cdu.zb.entity.FocusEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.service.FocusService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */
@RestController
@RequestMapping("/api/focus")
@CrossOrigin
public class FocusController  extends BaseApiController {


    @Autowired
    private FocusService focusService;


    @PostMapping(value = "/addFocus",name="添加关注")
    public JsonResult<Boolean>  addFocus(@RequestBody FocusDto focusDto){
        if(focusService.count(new QueryWrapper<FocusEntity>().eq("user_id",focusDto.getUserId())
        .eq("focus_user_id",focusDto.getFocusUserId()))!=0){
            return jr(GlobalConstants.ERROR,"已关注");
        }
        focusDto.setFocusTime(LocalDateTime.now());
        return jr(GlobalConstants.SUCCESS,"关注成功",focusService.save(focusDto));
    }

    @GetMapping(value = "/deleteFocus",name="取消关注")
    public JsonResult<Boolean>  deleteFocus(String userId,String focusUserId){
        return jr(GlobalConstants.SUCCESS,"取消成功",focusService.remove(new QueryWrapper<FocusEntity>().eq("user_id",userId)
                .eq("focus_user_id",focusUserId)));
    }
    @GetMapping(value = "/isFocus",name="是否关注")
    public JsonResult<Boolean>  isFocus(String userId,String focusUserId){
        if(focusService.count(new QueryWrapper<FocusEntity>().eq("user_id",userId)
                .eq("focus_user_id",focusUserId))!=0){
            return jr(GlobalConstants.SUCCESS,"已关注");
        }
        return jr(GlobalConstants.ERROR,"未关注");
    }

    @GetMapping(value = "/getcount",name="关注数")
    public JsonResult<Integer>  getcount(String userId){
            return jr(GlobalConstants.SUCCESS,"已关注",focusService.count(new QueryWrapper<FocusEntity>()
                    .eq("focus_user_id",userId)));
    }


}
