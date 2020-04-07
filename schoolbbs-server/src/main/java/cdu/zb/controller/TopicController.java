package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.TopicDto;
import cdu.zb.entity.TopicEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.TopicResponse;
import cdu.zb.service.TopicService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/api/topic")
public class TopicController extends BaseApiController {

    @Autowired
    private TopicService topicService;

    /*
     * @description: 得到首页帖子
     * @author accountw
     * @date 2020/3/25 16:03
     * @param []
     * @return cdu.zb.jsonresult.JsonResult<java.util.List<cdu.zb.response.TopicResponse>>
     **/
    @GetMapping(value = "/getFirstTopices",name="得到首页帖子")
    public JsonResult<List<TopicResponse>> getFirstTopices() throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getFirstTopices());
    }

    /*
     * @description: 得到该模块的帖子
     * @author accountw
     * @date 2020/3/26 18:08
     * @param [plateid]
     * @return cdu.zb.jsonresult.JsonResult<java.util.List<cdu.zb.response.TopicResponse>>
     **/
    @GetMapping(value = "/getTopicByPlateid",name="得到该模块的帖子")
    public JsonResult<List<TopicResponse>> getTopicByPlateid(@RequestParam("plateid") String plateid, @RequestParam("index") Integer index) throws UnsupportedEncodingException {
          return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getTopicByPlateid(plateid,index));
    }

    @GetMapping(value = "/getTopicbyid",name = "通过id得到帖子内容")
    public JsonResult<TopicResponse> getTopicbyid(String id) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getTopicbyid(id));
    }

    @PostMapping(value = "/saveTopic",name="保存帖子")
    public JsonResult<Integer> saveTopic(@RequestBody TopicDto topicDto) throws UnsupportedEncodingException {
        return  jr(GlobalConstants.SUCCESS,"发表成功",topicService.saveTopic(topicDto));
    }

    @GetMapping(value = "/getTopicCount",name="获取总条数")
    public JsonResult<Integer> getTopicCount(String plateid){
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.count(new QueryWrapper<TopicEntity>().eq("plate_id",plateid)));
    }
}
