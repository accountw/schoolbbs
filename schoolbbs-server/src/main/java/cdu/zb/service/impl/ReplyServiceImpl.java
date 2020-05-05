package cdu.zb.service.impl;

import cdu.zb.dto.ReplyDto;
import cdu.zb.entity.MessageEntity;
import cdu.zb.entity.ReplyEntity;
import cdu.zb.entity.TopicEntity;
import cdu.zb.entity.UserEntity;
import cdu.zb.mapper.MessageMapper;
import cdu.zb.mapper.ReplyMapper;
import cdu.zb.mapper.TopicMapper;
import cdu.zb.mapper.UserMapper;
import cdu.zb.response.ReplyResponse;
import cdu.zb.service.ReplyService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author accountw
 * @since 2020-01-17
 */
@Service
public class ReplyServiceImpl extends ServiceImpl<ReplyMapper, ReplyEntity> implements ReplyService {

    @Autowired
    private ReplyMapper replyMapper;
    @Autowired
    private TopicMapper topicMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public List<ReplyResponse> getReplybyTopicid(String topicid,Integer index) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        index=index*15-15;
        List<ReplyResponse> list=replyMapper.getReplybyTopicid(topicid,index);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
            if(list.get(i).getSign()!=null){
                list.get(i).setSign(new String(decoder.decode(list.get(i).getSign()),"UTF-8"));
            }
        }
        return list;
    }

    @Override
    public Integer saveReply(ReplyDto replyDto) {
        replyDto.setReplyTime(LocalDateTime.now());
        TopicEntity topicEntity=topicMapper.getTopicbyid(replyDto.getTopicId());
        topicEntity.setLastTime(replyDto.getReplyTime());
        topicEntity.setCount(topicEntity.getCount()+1);
        UserEntity userEntity=userMapper.selectById(replyDto.getUserId());
        userEntity.setExp(userEntity.getExp()+1);
        Integer storey=replyMapper.getMaxStorey(replyDto.getTopicId());
        if(topicMapper.selectById(replyDto.getTopicId()).getUserId()==replyDto.getUserId()){
            replyDto.setMaster(1);
        }else{
            replyDto.setMaster(0);
        }
        if(storey==null){
            storey=2;
        }else {
            storey=storey+1;
        }
       replyDto.setStorey(storey);
       if(replyMapper.insert(replyDto)>0){
           topicMapper.updateById(topicEntity);
           userMapper.updateById(userEntity);
           return 1;
       }
        return  0;
    }

    @Override
    public void removeReplies(String topicId) {
        List<ReplyEntity> list=replyMapper.selectList(new QueryWrapper<ReplyEntity>().eq("topic_id",topicId));
        for(ReplyEntity replyEntity:list){
            messageMapper.delete(new QueryWrapper<MessageEntity>().eq("reply_id",replyEntity.getId()));
        }
        replyMapper.delete(new QueryWrapper<ReplyEntity>().eq("topic_id",topicId));
    }

    @Override
    public List<ReplyResponse> getSearch(String context, Integer index) throws UnsupportedEncodingException {
        Base64.Encoder encoder = Base64.getEncoder();
        Base64.Decoder decoder = Base64.getDecoder();
        index=index*15-15;
        context=encoder.encodeToString(context.getBytes("UTF-8"));
        List<ReplyResponse> list=replyMapper.getSearchResult(context,index);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
        }
        return list;
    }
}
