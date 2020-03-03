package cdu.zb.controller;


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

    @GetMapping(value = "/getFirstTopices",name="得到首页帖子")
    public JsonResult<List<TopicResponse>> getFirstTopices(){
        return jr("获取成功",topicService.getFirstTopices());
    }

}
