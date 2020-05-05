package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.FavoriteDto;
import cdu.zb.entity.*;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.security.MyUserDetails;
import cdu.zb.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
@RequestMapping("/api/favorite")
@CrossOrigin
public class FavoriteController extends BaseApiController {


    @Autowired
    private  FavoriteService favoriteService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private ReplyService replyService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CricleService cricleService;

    @PostMapping(value = "/addFavorite",name = "点赞")
    public JsonResult<Boolean> addFavorite(@RequestBody FavoriteDto favoriteDto){
        if(favoriteDto.getTopicId()!=null){
            if(favoriteService.count(new QueryWrapper<FavoriteEntity>().eq("user_id",favoriteDto.getUserId())
            .eq("topic_id",favoriteDto.getTopicId()))!=0){
                return jr(GlobalConstants.ERROR,"已经给这个帖子点过赞了");
            }
            TopicEntity topicEntity=topicService.getOne(new QueryWrapper<TopicEntity>().eq("id",favoriteDto.getTopicId()));
            topicEntity.setLikenum(topicEntity.getLikenum()+1);
            topicService.updateById(topicEntity);
        }
        if(favoriteDto.getReplyId()!=null){
            if(favoriteService.count(new QueryWrapper<FavoriteEntity>().eq("user_id",favoriteDto.getUserId())
                    .eq("reply_id",favoriteDto.getReplyId()))!=0){
                return jr(GlobalConstants.ERROR,"已经给这个帖子点过赞了");
            }
            ReplyEntity replyEntity=replyService.getOne((new QueryWrapper<ReplyEntity>()
            .eq("id",favoriteDto.getReplyId())));
            replyEntity.setLikenum(replyEntity.getLikenum()+1);
            replyService.updateById(replyEntity);
        }
        favoriteDto.setLikeTime(LocalDateTime.now());
        if(favoriteService.save(favoriteDto)){
            if(favoriteDto.getReplyId()!=null){
                messageService.replyLike(favoriteDto.getUserId(),favoriteDto.getReplyId(),replyService.getById(favoriteDto.getReplyId()).getUserId());
            }
            if(favoriteDto.getTopicId()!=null){
                messageService.topicLike(favoriteDto.getUserId(),favoriteDto.getTopicId(),topicService.getById(favoriteDto.getTopicId()).getUserId());
            }
            return jr(GlobalConstants.SUCCESS,"点赞成功");
        }
        return jr(GlobalConstants.ERROR,"点赞失败");
    }

    @GetMapping(value = "/deleteReplyFavorite",name = "取消点赞")
    public JsonResult<Boolean> deleteReplyFavorite(String userId,String replyId){
        ReplyEntity replyEntity=replyService.getOne((new QueryWrapper<ReplyEntity>()
                .eq("id",replyId)));
        replyEntity.setLikenum(replyEntity.getLikenum()-1);
        if(favoriteService.count(new QueryWrapper<FavoriteEntity>()
                .eq("reply_id",replyId).eq("user_id",userId))!=0){
            if(favoriteService.remove(new QueryWrapper<FavoriteEntity>()
                    .eq("reply_id",replyId).eq("user_id",userId))){
                messageService.remove(new QueryWrapper<MessageEntity>()
                        .eq("reply_id",replyId).eq("user_id",userId));
                replyService.updateById(replyEntity);
                messageService.remove(new QueryWrapper<MessageEntity>().eq("user_id",userId).eq("reply_id",replyId)
                        .eq("event",2));
                return jr(GlobalConstants.SUCCESS,"取消成功");
            }
        }
        return jr(GlobalConstants.ERROR,"取消失败");
    }
    @GetMapping(value = "/deleteTopicFavorite",name = "取消点赞")
    public JsonResult<Boolean> deleteTopicFavorite(String userId,String topicId){
        TopicEntity topicEntity=topicService.getOne(new QueryWrapper<TopicEntity>().eq("id",topicId));
        topicEntity.setLikenum(topicEntity.getLikenum()-1);

     if(favoriteService.count(new QueryWrapper<FavoriteEntity>()
             .eq("topic_id",topicId).eq("user_id",userId))!=0){
         if(favoriteService.remove(new QueryWrapper<FavoriteEntity>()
                 .eq("topic_id",topicId).eq("user_id",userId))){
             messageService.remove(new QueryWrapper<MessageEntity>()
                     .eq("topic_id",topicId).eq("user_id",userId));
             topicService.updateById(topicEntity);
             messageService.remove(new QueryWrapper<MessageEntity>().eq("user_id",userId).eq("topic_id",topicId)
                     .eq("event",2));
             return jr(GlobalConstants.SUCCESS,"取消成功");
         }

     }
        return jr(GlobalConstants.ERROR,"取消失败");
    }

    @GetMapping(value = "isTopicFavorite",name="是否给帖子点赞")
    public JsonResult<Integer> isTopicFavorite(String userId,String topicId){
        if(favoriteService.count(new QueryWrapper<FavoriteEntity>().eq("user_id",userId)
                .eq("topic_id",topicId))!=0){
            return jr(GlobalConstants.SUCCESS,"已经给这个帖子点过赞了");
        }
        return jr(GlobalConstants.ERROR,"没有点赞");
    }
    @GetMapping(value = "isReplyFavorite",name="是否给评论点赞")
    public JsonResult<Integer> isReplyFavorite(String userId,String replyId){
        if(favoriteService.count(new QueryWrapper<FavoriteEntity>().eq("user_id",userId)
                .eq("reply_id",replyId))!=0){
            return jr(GlobalConstants.SUCCESS,"已经给这个帖子点过赞了");
        }
        return jr(GlobalConstants.ERROR,"已经给这个帖子点过赞了");
    }

    @GetMapping(value = "getReplyFavariteNum",name = "评论点赞数")
    public JsonResult<Integer> getReplyFavariteNum(String replyId){
        return jr(GlobalConstants.SUCCESS,"获取成功",favoriteService.count(new QueryWrapper<FavoriteEntity>()
                .eq("reply_id",replyId)));
    }
    @GetMapping(value = "getTopicFavariteNum",name = "帖子点赞数")
    public JsonResult<Integer> getTopicFavariteNum(String topicId){
        return jr(GlobalConstants.SUCCESS,"获取成功",favoriteService.count(new QueryWrapper<FavoriteEntity>()
                .eq("topic_id",topicId)));
    }

    @GetMapping(value = "likeCricle",name = "给圈子点赞")
    public JsonResult<String> likeCricle(String cricleId){
       Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails= (MyUserDetails) authentication.getPrincipal();
        CricleEntity cricleEntity=cricleService.getById(cricleId);
       if( favoriteService.count(new QueryWrapper<FavoriteEntity>().eq("user_id",userDetails.getId())
               .eq("cricle_id",cricleId))!=0){
           return jr(GlobalConstants.ERROR,"已经点过赞了");
       }
       FavoriteEntity favoriteEntity=new FavoriteEntity();
       favoriteEntity.setLikeTime(LocalDateTime.now());
       favoriteEntity.setUserId(userDetails.getId());
       favoriteEntity.setCricleId(cricleId);
       favoriteService.save(favoriteEntity);
       cricleEntity.setLikenum(cricleEntity.getLikenum()+1);
       cricleService.updateById(cricleEntity);
        return jr(GlobalConstants.SUCCESS,"点赞成功");
    }

    @GetMapping(value = "removeLikeCricle",name = "取消给圈子点赞")
    public JsonResult<String> removeLikeCricle(String cricleId){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails= (MyUserDetails) authentication.getPrincipal();
        CricleEntity cricleEntity=cricleService.getById(cricleId);
        FavoriteEntity favoriteEntity=favoriteService.getOne(new QueryWrapper<FavoriteEntity>().eq("user_id",userDetails.getId()) .eq("cricle_id",cricleId));
        if(favoriteEntity==null){
            return jr(GlobalConstants.ERROR,"已经点过赞了");
        }
        favoriteService.removeById(favoriteEntity.getId());
        cricleEntity.setLikenum(cricleEntity.getLikenum()-1);
        cricleService.updateById(cricleEntity);
        return jr(GlobalConstants.SUCCESS,"取消成功");
    }

    @GetMapping(value = "selectLikeCricle",name = "查询是否给圈子点赞")
    public JsonResult<String> selectLikeCricle(String cricleId){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails= (MyUserDetails) authentication.getPrincipal();
        FavoriteEntity favoriteEntity=favoriteService.getOne(new QueryWrapper<FavoriteEntity>().eq("user_id",userDetails.getId()) .eq("cricle_id",cricleId));
        if(favoriteEntity==null){
            return jr(GlobalConstants.ERROR,"没有点赞");
        }
        return jr(GlobalConstants.SUCCESS,"已经点赞");
    }
}
