package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.CommentDto;
import cdu.zb.entity.BanEntity;
import cdu.zb.entity.CommentEntity;
import cdu.zb.entity.ReplyEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.CommentResponse;
import cdu.zb.security.MyUserDetails;
import cdu.zb.service.*;
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
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController extends BaseApiController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private BanService banService;

    @Autowired
    private ReplyService replyService;

    @Autowired
    private  TopicService topicService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/getCommentByreplyid" ,name = "根据评论id得到回复")
    public JsonResult<List<CommentResponse>> getCommentByreplyid(String replyid,Integer index) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获得成功",commentService.getCommentByreplyid(replyid,index));
    }

    @PostMapping(value="/saveComment",name="保存回复")
    public JsonResult<Integer> saveComment(@RequestBody CommentDto commentDto) throws UnsupportedEncodingException {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails= (MyUserDetails) authentication.getPrincipal();
        BanEntity banEntity=banService.getOne(new QueryWrapper<BanEntity>().eq("uid",myUserDetails.getId()));
        if(banEntity!=null){
            return  jr(GlobalConstants.NO_UNAUTHORIZED,banEntity.getFreeTime().toString());
        }
        commentDto.setReplyTime(LocalDateTime.now());
        Base64.Encoder encoder = Base64.getEncoder();
        commentDto.setContext(encoder.encodeToString(commentDto.getContext().getBytes("UTF-8")));
        ReplyEntity replyEntity=replyService.getById(commentDto.getReplyId());
        if(commentService.saveComment(commentDto)!=0){
            if(commentDto.getReplyUserId()!=null){
                messageService.commentTouser(commentDto.getUserId(),commentDto.getReplyId(),commentDto.getReplyUserId(),
                        commentDto.getContext(),replyEntity.getUserId(),
                   topicService.getById(replyEntity.getTopicId()).getUserId() ,commentDto.getId()    );
            }
           else{
                messageService.comment(commentDto.getUserId(),commentDto.getReplyId(),
                        commentDto.getContext(),replyEntity.getUserId(),
                        topicService.getById(replyEntity.getTopicId()).getUserId(),commentDto.getId() );
            }
        }

        return jr(GlobalConstants.SUCCESS,"保存成功");
    }

    @GetMapping(value = "/getCommentCount",name="查询回复数")
    public JsonResult<Integer> getCommentCount(String replyid){
        return jr(GlobalConstants.SUCCESS,"查询成功",commentService.count(new QueryWrapper<CommentEntity>().eq("reply_id",replyid)));
    }


}
