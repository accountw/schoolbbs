package cdu.zb.service.impl;

import cdu.zb.dto.ReplyDto;
import cdu.zb.entity.ReplyEntity;
import cdu.zb.mapper.ReplyMapper;
import cdu.zb.mapper.TopicMapper;
import cdu.zb.response.ReplyResponse;
import cdu.zb.service.ReplyService;
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
    @Override
    public List<ReplyResponse> getReplybyTopicid(String topicid) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        List<ReplyResponse> list=replyMapper.getReplybyTopicid(topicid);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
        }
        return list;
    }

    @Override
    public Integer saveReply(ReplyDto replyDto) {
        replyDto.setReplyTime(LocalDateTime.now());
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
       return replyMapper.insert(replyDto);
    }
}
