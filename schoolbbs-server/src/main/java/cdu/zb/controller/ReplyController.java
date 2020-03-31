package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.ReplyDto;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.ReplyResponse;
import cdu.zb.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/reply")
public class ReplyController extends BaseApiController {

    @Autowired
    private ReplyService replyService;

    @GetMapping(value = "/getReplybyTopicid" ,name = "通过帖子id获取回复")
    public JsonResult<List<ReplyResponse>> getReplybyTopicid(String topicid){
        return jr(GlobalConstants.SUCCESS,"获取成功",replyService.getReplybyTopicid(topicid));
    }

    @PostMapping(value = "/saveReply",name="保存评论")
    public JsonResult<Integer> saveReply(@RequestBody ReplyDto replyDto){
          return  jr(GlobalConstants.SUCCESS,"保存成功",replyService.saveReply(replyDto));
    }
}
