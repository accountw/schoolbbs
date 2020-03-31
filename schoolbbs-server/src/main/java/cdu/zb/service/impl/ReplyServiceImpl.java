package cdu.zb.service.impl;

import cdu.zb.dto.ReplyDto;
import cdu.zb.entity.ReplyEntity;
import cdu.zb.mapper.ReplyMapper;
import cdu.zb.response.ReplyResponse;
import cdu.zb.service.ReplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    @Override
    public List<ReplyResponse> getReplybyTopicid(String topicid) {
        return replyMapper.getReplybyTopicid(topicid);
    }

    @Override
    public Integer saveReply(ReplyDto replyDto) {
        replyDto.setReplyTime(LocalDateTime.now());
        Integer storey=replyMapper.getMaxStorey(replyDto.getTopicId());
        if(storey==null){
            storey=0;
        }else {
            storey=storey+1;
        }
       replyDto.setStorey(storey);
       return replyMapper.insert(replyDto);
    }
}
