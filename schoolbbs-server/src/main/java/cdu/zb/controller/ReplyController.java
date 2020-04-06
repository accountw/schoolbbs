package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.ReplyDto;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.ReplyResponse;
import cdu.zb.service.ReplyService;
import cdu.zb.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Base64;
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
    public JsonResult<List<ReplyResponse>> getReplybyTopicid(String topicid) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",replyService.getReplybyTopicid(topicid));
    }

    @PostMapping(value = "/saveReply",name="保存评论")
    public JsonResult<Integer> saveReply(@RequestBody ReplyDto replyDto) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        replyDto.setContext(encoder.encodeToString(replyDto.getContext().getBytes("UTF-8")));
          return  jr(GlobalConstants.SUCCESS,"保存成功",replyService.saveReply(replyDto));
    }

    @PostMapping(value = "/savePicture",name="保存评论")
    public JsonResult<String> saveReply(MultipartFile file){
        // 文件保存路径
        String id=IdUtil.createID();
        String fileName = file.getOriginalFilename();
        String[] name=fileName.split("\\.");
        String a=name[name.length-1];
        String path="D:\\schoolbbs\\schoolbbs-web\\public\\image\\";
        String filePath = path + id+"."+a;
        if (!file.isEmpty()) {
            try {

                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return  jr(GlobalConstants.SUCCESS,"保存成功",id+"."+a);
    }
}
