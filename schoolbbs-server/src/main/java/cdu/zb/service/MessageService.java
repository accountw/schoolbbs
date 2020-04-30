package cdu.zb.service;

import cdu.zb.entity.MessageEntity;
import cdu.zb.response.MessageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-04-27
 */
public interface MessageService extends IService<MessageEntity> {



     Integer reply(String userid,String topicid,String uid,String context,String masterId,String replyid) throws UnsupportedEncodingException;

     Integer comment(String userid,String replyid,String context,String peopleId,String masterId,String commentid) throws UnsupportedEncodingException;

     Integer commentTouser(String userid,String replyid,String uid,String context,String peopleId,String masterId,String commentid) throws UnsupportedEncodingException;

     Integer replyLike(String userid,String replyid,String uid);
     Integer topicLike(String userid,String topicid,String uid);

     List<MessageResponse> getMessage(String userid, Integer index) throws UnsupportedEncodingException;

    Integer getcount(String userid);
}
