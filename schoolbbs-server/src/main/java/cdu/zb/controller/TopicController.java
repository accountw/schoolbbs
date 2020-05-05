package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.TopicDto;
import cdu.zb.entity.*;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.TopicResponse;
import cdu.zb.security.MyUserDetails;
import cdu.zb.service.*;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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
 * @since 2020-02-10
 */
@RestController
@RequestMapping("/api/topic")
public class TopicController extends BaseApiController {

    @Autowired
    private TopicService topicService;

    @Autowired
    private BanService banService;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private ReplyService replyService;

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
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails= (MyUserDetails) authentication.getPrincipal();
        BanEntity banEntity=banService.getOne(new QueryWrapper<BanEntity>().eq("uid",myUserDetails.getId()).le("free_time", LocalDateTime.now()));
        if(banEntity!=null){
            return  jr(GlobalConstants.NO_UNAUTHORIZED,banEntity.getFreeTime().toString());
        }
        return  jr(GlobalConstants.SUCCESS,"发表成功",topicService.saveTopic(topicDto));
    }

    @GetMapping(value = "/getTopicCount",name="获取总条数")
    public JsonResult<Integer> getTopicCount(String plateid){
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.count(new QueryWrapper<TopicEntity>().eq("plate_id",plateid)));
    }

    @GetMapping(value = "/getTopicFlow",name="流加载")
    public JsonResult<List<TopicResponse>> getTopicFlow(Integer index) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getTopicFlow(index));
    }
    @GetMapping(value = "/getFirstCount",name="获取总条数")
    public JsonResult<Integer> getFirstCount(){
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.count());
    }

    @GetMapping(value = "/getTopicByUserid")
    public JsonResult<List<TopicResponse>> getTopicByUserid(Integer index,String userId) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getTopicByUserid(index,userId));
    }

    @GetMapping(value = "/deleteTopic")
    public JsonResult<Integer> deleteTopic(String topicid){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntity userEntity=userService.getOne(new QueryWrapper<UserEntity>().eq("username",username));
        if(topicService.remove(new QueryWrapper<TopicEntity>().eq("id",topicid).eq("user_id",userEntity.getId()))){
            userEntity.setCount(userEntity.getCount()-1);
            userEntity.setExp(userEntity.getExp()-3);
            userService.updateById(userEntity);
            messageService.remove(new QueryWrapper<MessageEntity>().eq("topic_id",topicid));
            replyService.remove(new QueryWrapper<ReplyEntity>().eq("topic_id",topicid));
            return jr(GlobalConstants.SUCCESS,"删除成功");
        }
        return jr(GlobalConstants.ERROR,"删除失败");
    }

    @GetMapping(value = "/deleteTopicbyadmin")
    public JsonResult<Integer> deleteTopicbyadmin(String topicid){
        TopicEntity topicEntity=topicService.getById(topicid);
        if(topicService.removeById(topicid)){
           userService.deleteExp(topicEntity.getUserId(),3);
           userService.deletecount(topicEntity.getUserId());
            messageService.remove(new QueryWrapper<MessageEntity>().eq("topic_id",topicid));
            replyService.removeReplies(topicid);
            return jr(GlobalConstants.SUCCESS,"删除成功");
        }
        return jr(GlobalConstants.ERROR,"删除失败");
    }
  @GetMapping(value = "getTop",name = "获取今日前10")
    public  JsonResult<List<TopicResponse>>  getTop() throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getTop());
  }

  @GetMapping(value = "/setTop",name="置顶")
  public  JsonResult<Integer>  setTop(String id,String plateid)  {
      Integer count=  topicService.count(new QueryWrapper<TopicEntity>().eq("plate_id",plateid).eq("top",1));
      if(count>4){
          return jr(GlobalConstants.ERROR,"置顶帖已达上限");
      }
      topicService.update(new UpdateWrapper<TopicEntity>().eq("id",id).set("top",1));
      return jr(GlobalConstants.SUCCESS,"置顶");
  }

    @GetMapping(value = "/removeTop",name="取消置顶")
    public  JsonResult<Integer>  removeTopTop(String id) {
        topicService.update(new UpdateWrapper<TopicEntity>().eq("id",id).set("top",0));
        return jr(GlobalConstants.SUCCESS,"取消置顶");
    }

    @GetMapping(value = "getTopList",name="获取置顶帖")
    public  JsonResult<List<TopicResponse>>  getTopList(String plateid) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getTopTopicByPlateid(plateid));
    }
    @GetMapping(value = "/setFine",name="加精")
    public  JsonResult<Integer>  setFine(String id)  {
        topicService.update(new UpdateWrapper<TopicEntity>().eq("id",id).set("fine",1));
        return jr(GlobalConstants.SUCCESS,"置顶");
    }
    @GetMapping(value = "/removeFine",name="取消加精")
    public  JsonResult<Integer>  removeFine(String id) {
        topicService.update(new UpdateWrapper<TopicEntity>().eq("id",id).set("fine",0));
        return jr(GlobalConstants.SUCCESS,"取消置顶");
    }
    @GetMapping(value = "/getFineList",name="获取置顶帖")
    public  JsonResult<List<TopicResponse>>  getTopList(String plateid,Integer index) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getFineList(plateid,index));
    }

    @GetMapping(value = "/getFineCount",name = "获取加精帖数")
    public JsonResult<Integer> getFineCount(String plateid){
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.count(new QueryWrapper<TopicEntity>().eq("plate_id",plateid).eq("fine",1)));
    }

    @GetMapping(value = "/getSearch",name = "获取搜索结果")
    public JsonResult<List<TopicResponse>> getSearch(String context,Integer index) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.getSearch(context,index));
    }
    @GetMapping(value = "/getSearchCount",name = "获取搜索结果")
    public JsonResult<Integer> getSearchCount(String context) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        context=encoder.encodeToString(context.getBytes("UTF-8"));
        return jr(GlobalConstants.SUCCESS,"获取成功",topicService.count(new QueryWrapper<TopicEntity>().like("context",context).or().like("title",context)));
    }
}
