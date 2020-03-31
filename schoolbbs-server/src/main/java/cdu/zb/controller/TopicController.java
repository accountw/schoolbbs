package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.entity.TopicEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.TopicResponse;
import cdu.zb.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public JsonResult<List<TopicResponse>> getFirstTopices(){
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
    public JsonResult<List<TopicResponse>> getTopicByPlateid(String plateid){
          return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getTopicByPlateid(plateid));
    }

    @GetMapping(value = "/getTopicbyid",name = "通过id得到帖子内容")
    public JsonResult<TopicEntity> getTopicbyid(String id){
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getTopicbyid(id));
    }
}
