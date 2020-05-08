package cdu.zb.service.impl;

import cdu.zb.entity.MessageEntity;
import cdu.zb.mapper.MessageMapper;
import cdu.zb.response.MessageResponse;
import cdu.zb.service.MessageService;
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
 * @since 2020-04-27
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, MessageEntity> implements MessageService {


    @Autowired
    private  MessageMapper messageMapper;

    @Override
    public Integer reply(String userid, String topicid, String uid, String context,String masterId,String replyid) throws UnsupportedEncodingException {
        MessageEntity messageEntity=new MessageEntity() ;
        messageEntity.setUserId(userid);
        messageEntity.setUid(uid);
        messageEntity.setEvent(0);
        messageEntity.setTopicId(topicid);
        messageEntity.setMasterId(masterId);
        messageEntity.setContext(context);
        messageEntity.setReplyId(replyid);
        messageEntity.setCreateTime(LocalDateTime.now());
        return messageMapper.insert(messageEntity);
    }

    @Override
    public Integer comment(String userid, String replyid,String context, String peopleId,String masterId,String commentid) throws UnsupportedEncodingException {
        MessageEntity messageEntity=new MessageEntity() ;
        messageEntity.setUserId(userid);
        messageEntity.setPeopleId(peopleId);
        messageEntity.setEvent(1);
        messageEntity.setReplyId(replyid);
        messageEntity.setMasterId(masterId);
        messageEntity.setContext(context);
        messageEntity.setCommentId(commentid);
        messageEntity.setCreateTime(LocalDateTime.now());
        return messageMapper.insert(messageEntity);
    }

    @Override
    public Integer commentTouser(String userid, String replyid, String uid, String context, String peopleId,String masterId,String commentid) throws UnsupportedEncodingException {
        MessageEntity messageEntity=new MessageEntity() ;
        messageEntity.setUserId(userid);
        messageEntity.setUid(uid);
        messageEntity.setEvent(1);
        messageEntity.setReplyId(replyid);
        messageEntity.setMasterId(masterId);
        messageEntity.setCommentId(commentid);
        messageEntity.setPeopleId(peopleId);
        messageEntity.setContext(context);
        messageEntity.setCreateTime(LocalDateTime.now());
        return messageMapper.insert(messageEntity);
    }

    @Override
    public Integer replyLike(String userid, String replyid, String uid) {
        MessageEntity messageEntity=new MessageEntity() ;
        messageEntity.setUserId(userid);
        messageEntity.setUid(uid);
        messageEntity.setEvent(2);
        messageEntity.setReplyId(replyid);
        messageEntity.setCreateTime(LocalDateTime.now());
        return messageMapper.insert(messageEntity);
    }

    @Override
    public Integer topicLike(String userid, String topicid, String uid) {
        MessageEntity messageEntity=new MessageEntity() ;
        messageEntity.setUserId(userid);
        messageEntity.setUid(uid);
        messageEntity.setEvent(2);
        messageEntity.setTopicId(topicid);
        messageEntity.setCreateTime(LocalDateTime.now());
        return messageMapper.insert(messageEntity);
    }

    @Override
    public List<MessageResponse> getMessage(String userid, Integer index) throws UnsupportedEncodingException {
        index=index*15-15;
        Base64.Decoder decoder = Base64.getDecoder();
        List<MessageResponse> list =messageMapper.getMessage(userid,index);
        for(int i=0;i<list.size();i++){
           if(list.get(i).getContext()!=null){
               list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
           }
            if(list.get(i).getrContext()!=null){
                list.get(i).setrContext(new String(decoder.decode(list.get(i).getrContext()),"UTF-8"));
                if(list.get(i).getrContext().equals("")) {
                    if (list.get(i).getEvent() == 1) {
                        list.get(i).setrContext("[图片]");
                    }
                }
            }
            if(list.get(i).gettTitle()!=null){
                list.get(i).settTitle(new String(decoder.decode(list.get(i).gettTitle()),"UTF-8"));
            }

        }
        return list;
    }

    @Override
    public Integer getcount(String userid) {
        return messageMapper.getcount(userid);
    }
}
