package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.PlateAdminDto;
import cdu.zb.entity.PlateAdminEntity;
import cdu.zb.entity.UserEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.PlateAdminResponse;
import cdu.zb.service.PlateAdminService;
import cdu.zb.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-02-01
 */
@RestController
@RequestMapping("/api/plateAdmin")
public class PlateAdminController extends BaseApiController {

    @Autowired
    private PlateAdminService plateAdminService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getPlateAdmin",name = "得到版主")
    public JsonResult<List<PlateAdminResponse>> getPlateAdmin(String plateid){
        return jr(GlobalConstants.SUCCESS,"获得成功",plateAdminService.getPlateAdmin(plateid));
    }

    @GetMapping(value = "/isAdmin",name="查询是否是版主")
    public  JsonResult<Integer> isAdmin(String plateid){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntity userEntity=userService.getOne(new QueryWrapper<UserEntity>().eq("username",username));
        PlateAdminEntity plateAdminEntity=plateAdminService.getOne(new QueryWrapper<PlateAdminEntity>().eq("plate_id",plateid));
        if(plateAdminEntity==null){
            return jr(GlobalConstants.ERROR,"不是版主");
        }
        if(plateAdminEntity.getUserId().equals(userEntity.getId())){
            return jr(GlobalConstants.SUCCESS,"是版主");
        }
        return jr(GlobalConstants.ERROR,"不是版主");
    }

    @GetMapping(value = "/deleteAdmin",name = "解除版主")
    public JsonResult<Boolean> deleteAdmin(String id){
        return jr(GlobalConstants.SUCCESS,"解除成功",plateAdminService.removeById(id));
    }

    @GetMapping(value = "/listAdmin",name = "查询所有版主")
    public JsonResult<List<PlateAdminResponse>> listAdmin(Integer index){
        return jr(GlobalConstants.SUCCESS,"查询成功",plateAdminService.listAdmin(index));
    }
    @PostMapping(value = "/addAdmin",name = "任命版主")
    public JsonResult<Boolean> addAdmin(PlateAdminDto plateAdminDto){
        plateAdminDto.setCreateTime(LocalDateTime.now());
        return jr(GlobalConstants.SUCCESS,"解除成功",plateAdminService.save(plateAdminDto));
    }
    @GetMapping(value = "/getcount",name="查询版主数")
    public JsonResult<Integer> getcount(){
        return jr(GlobalConstants.SUCCESS,"获取成功",plateAdminService.getcount());
    }

    @GetMapping(value = "setadmin",name="设置版主")
    public JsonResult<Boolean> setadmin(String plateId,String username){
        UserEntity userEntity=userService.getOne(new QueryWrapper<UserEntity>().eq("username",username));
        if(userEntity==null){
            return jr(GlobalConstants.ERROR,"该用户不存在");
        }
        if(plateAdminService.count(new QueryWrapper<PlateAdminEntity>().eq("user_id",userEntity.getId()))!=0){
            return jr(GlobalConstants.ERROR,"该用户已经是版主了");
        }
        PlateAdminEntity plateAdminEntity=new PlateAdminEntity();
        plateAdminEntity.setUserId(userEntity.getId());
        plateAdminEntity.setCreateTime(LocalDateTime.now());
        plateAdminEntity.setPlateId(plateId);

        return jr(GlobalConstants.SUCCESS,plateAdminService.save(plateAdminEntity));
    }
}
