package cdu.zb.service;

import cdu.zb.dto.ReplyDto;
import cdu.zb.entity.ReplyEntity;
import cdu.zb.response.ReplyResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-01-17
 */
public interface ReplyService extends IService<ReplyEntity> {

    List<ReplyResponse> getReplybyTopicid(String topicid,Integer index) throws UnsupportedEncodingException;

    Integer saveReply(ReplyDto replyDto);
    public void removeReplies(String topicId);

    List<ReplyResponse> getSearch(String context, Integer index) throws UnsupportedEncodingException;
}
