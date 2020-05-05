package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.CricleDto;
import cdu.zb.entity.CricleEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.CricleResponse;
import cdu.zb.security.MyUserDetails;
import cdu.zb.service.CricleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/api/cricle")
public class CricleController extends BaseApiController {

    @Autowired
    private CricleService cricleService;
    @GetMapping(value = "/getCriclrbyUserid",name = "通过用户id得到圈子")
    public JsonResult<List<CricleResponse>> getCriclrbyUserid(String userid, Integer index) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",cricleService.getCriclrbyUserid(userid,index));
    }
    @GetMapping(value = "/getCountbyUserid",name ="得到用户的圈子数" )
    public JsonResult<Integer> getCountbyUserid(String userid){
        return jr(GlobalConstants.SUCCESS,"获取成功",cricleService.count(new QueryWrapper<CricleEntity>().eq("user_id",userid)));
    }
    @PostMapping(value = "/addCricle",name = "添加圈子")
    public JsonResult<Boolean> addCricle(@RequestBody CricleDto cricleDto) throws UnsupportedEncodingException {
        cricleDto.setCreateTime(LocalDateTime.now());
        cricleDto.setLikenum(0);
        Base64.Encoder encoder = Base64.getEncoder();
        cricleDto.setContext(encoder.encodeToString(cricleDto.getContext().getBytes("UTF-8")));
        return jr(GlobalConstants.SUCCESS,"添加成功",cricleService.save(cricleDto));
    }

    @GetMapping(value = "/deleteCricle",name = "删除圈子")
    public JsonResult<String> deleteCricle(String id){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails= (MyUserDetails) authentication.getPrincipal();
        if(cricleService.remove(new QueryWrapper<CricleEntity>().eq("id",id).eq("user_id",myUserDetails.getId()))){
            return jr(GlobalConstants.SUCCESS,"删除成功");
        }
        return  jr(GlobalConstants.ERROR,"删除失败");
    }
    @GetMapping(value = "/getFocusCricleCount",name = "得到关注的用户的圈子总条数")
    public JsonResult<Integer> getFocusCricleCount(String userid){
        return jr(GlobalConstants.SUCCESS,"获取成功",cricleService.getFocusCricleCount(userid));
    }
    @GetMapping(value = "/getCricle",name = "得到关注人发的圈子")
    public JsonResult<List<CricleResponse>> getCricle(String userid, Integer index) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",cricleService.getCricle(userid,index));
    }
    @GetMapping(value = "/getCount",name ="得到用户的圈子数" )
    public JsonResult<Integer> getCount(String userid){
        return jr(GlobalConstants.SUCCESS,"获取成功",cricleService.count(new QueryWrapper<CricleEntity>().eq("user_id",userid)));
    }
}
