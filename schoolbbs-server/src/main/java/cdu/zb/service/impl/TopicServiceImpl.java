package cdu.zb.service.impl;

import cdu.zb.dto.TopicDto;
import cdu.zb.entity.TopicEntity;
import cdu.zb.mapper.TopicMapper;
import cdu.zb.response.TopicResponse;
import cdu.zb.service.TopicService;
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
 * @since 2020-02-10
 */
@Service
public class TopicServiceImpl extends ServiceImpl<TopicMapper, TopicEntity> implements TopicService {

    @Autowired
    private  TopicMapper topicMapper;
    @Override
    public List<TopicResponse> getFirstTopices() throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        List<TopicResponse> list=topicMapper.getFirstTopices();
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
            list.get(i).setTitle(new String(decoder.decode(list.get(i).getTitle()),"UTF-8"));
        }
        return list;
    }

    @Override
    public List<TopicResponse> getTopicByPlateid(String plateid,Integer index) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        index=index*20-20;
        List<TopicResponse> list=topicMapper.getTopicByPlateid(plateid,index);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
            list.get(i).setTitle(new String(decoder.decode(list.get(i).getTitle()),"UTF-8"));
        }
        return list;
    }

    @Override
    public TopicEntity getTopicbyid(String id) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        TopicEntity topicEntity=topicMapper.selectById(id);
        topicEntity.setContext(new String(decoder.decode(topicEntity.getContext()),"UTF-8"));
        topicEntity.setTitle(new String(decoder.decode(topicEntity.getTitle()),"UTF-8"));
        return topicEntity;
    }

    @Override
    public Integer saveTopic(TopicDto topicDto) throws UnsupportedEncodingException {
        topicDto.setFirstTime(LocalDateTime.now());
        topicDto.setLastTime(LocalDateTime.now());
        Base64.Encoder encoder = Base64.getEncoder();
        topicDto.setContext(encoder.encodeToString(topicDto.getContext().getBytes("UTF-8")));
        topicDto.setTitle(encoder.encodeToString(topicDto.getTitle().getBytes("UTF-8")));
        return topicMapper.insert(topicDto);
    }
}
