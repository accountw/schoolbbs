package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.CollectDto;
import cdu.zb.entity.CollectEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.CollectResponse;
import cdu.zb.response.TopicResponse;
import cdu.zb.service.CollectService;
import cdu.zb.service.TopicService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-04-14
 */
@RestController
@RequestMapping("/api/collect")
public class CollectController extends BaseApiController {

    @Autowired
    private  CollectService collectService;

    @Autowired
    private TopicService topicService;



    @PostMapping(value = "/collectPlate",name="收藏版块")
    public JsonResult<Boolean> collectPlate(@RequestBody CollectDto collectDto){
        collectDto.setCollectTime(LocalDateTime.now());
        if(collectService.count(new QueryWrapper<CollectEntity>().eq("user_id",collectDto.getUserId())
                .eq("plate_id",collectDto.getPlateId()))!=0){
            return jr(GlobalConstants.COLLECTED,"已收藏");
        }
        if(collectService.count(new QueryWrapper<CollectEntity>().eq("user_id",collectDto.getUserId())
                .eq("topic_id",collectDto.getTopicId()))!=0){
            return jr(GlobalConstants.COLLECTED,"已收藏");
        }
        return jr(GlobalConstants.SUCCESS,collectService.save(collectDto));
    }

    @GetMapping(value = "/deleteCollectPlate",name="取消收藏版块")
    public JsonResult<Boolean> collectPlate(String userId,String plateId){
        return jr(GlobalConstants.SUCCESS,collectService.remove(new QueryWrapper<CollectEntity>().eq("user_id",userId).eq("plate_id",plateId)));
    }

    @GetMapping(value = "/isCollectTopic",name="查询是否收藏")
    public JsonResult<String> isCollectTopic(String userId,String topicId){
        if(collectService.count(new QueryWrapper<CollectEntity>().eq("user_id",userId).eq("topic_id",topicId))!=0){
            return jr(GlobalConstants.SUCCESS,"已收藏");
        }
        return jr(GlobalConstants.ERROR,"未收藏");
    }
    @GetMapping(value = "/isCollectPlate",name="查询是否收藏")
    public JsonResult<String> isCollectPlate(String userId,String plateId){
        if(collectService.count(new QueryWrapper<CollectEntity>().eq("user_id",userId).eq("plate_id",plateId))!=0){
            return jr(GlobalConstants.SUCCESS,"已收藏");
        }
        return jr(GlobalConstants.ERROR,"未收藏");
    }

    @PostMapping(value = "/collectTopic",name="收藏帖子")
    public JsonResult<Boolean> collectTopic(@RequestBody CollectDto collectDto){
        collectDto.setCollectTime(LocalDateTime.now());
        return jr(GlobalConstants.SUCCESS,collectService.save(collectDto));
    }

    @GetMapping(value = "/deleteCollectTopic",name="取消收藏版块")
    public JsonResult<Boolean> deleteCollectTopic(String userId,String topicId){
        return jr(GlobalConstants.SUCCESS,collectService.remove(new QueryWrapper<CollectEntity>().eq("user_id",userId).eq("topic_id",topicId)));
    }

    @GetMapping(value = "/getCollectPlate",name="获取收藏版块")
    public JsonResult<List<CollectResponse>> getCollectPlate(String userId){
        return jr(GlobalConstants.SUCCESS,"获取成功",collectService.getCollectPlate(userId));
    }

    @GetMapping(value = "/getCollectTopic",name="获取收藏帖子")
    public JsonResult<List<TopicResponse>> getCollectTopic(String userId, Integer index) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getCollectTopic(userId,index));
    }

    @GetMapping(value = "/getCollectTopicCount",name="获取收藏帖子数")
    public JsonResult<Integer> getCollectTopicCount(String userId){
        return jr(GlobalConstants.SUCCESS,"获取成功",collectService.count(new QueryWrapper<CollectEntity>().eq("user_id",userId)
        .eq("plate_id",null)));
    }
}
