package cdu.zb.service.impl;

import cdu.zb.dto.CommentDto;
import cdu.zb.entity.CommentEntity;
import cdu.zb.entity.TopicEntity;
import cdu.zb.mapper.CommentMapper;
import cdu.zb.mapper.ReplyMapper;
import cdu.zb.mapper.TopicMapper;
import cdu.zb.response.CommentResponse;
import cdu.zb.service.CommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author accountw
 * @since 2020-03-31
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, CommentEntity> implements CommentService {

    @Autowired
    private  CommentMapper commentMapper;
    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private  ReplyMapper replyMapper;

    @Override
    public List<CommentResponse> getCommentByreplyid(String replyid,Integer index) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        index=index*10-10;
        List<CommentResponse> list=commentMapper.getCommentByreplyid(replyid,index);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
        }
        return list;
    }

    @Override
    public Integer saveComment(CommentDto commentDto) {
        TopicEntity topicEntity=topicMapper.getTopicbyid(replyMapper.selectById(commentDto.getReplyId()).getTopicId());
        topicEntity.setLastTime(commentDto.getReplyTime());
        topicEntity.setCount(topicEntity.getCount()+1);
        topicMapper.updateById(topicEntity);
        return commentMapper.insert(commentDto);
    }


}
