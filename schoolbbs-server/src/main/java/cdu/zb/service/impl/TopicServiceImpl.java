package cdu.zb.service.impl;

import cdu.zb.entity.TopicEntity;
import cdu.zb.mapper.TopicMapper;
import cdu.zb.response.TopicResponse;
import cdu.zb.service.TopicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<TopicResponse> getFirstTopices() {
        return topicMapper.getFirstTopices();
    }

    @Override
    public List<TopicResponse> getTopicByPlateid(String plateid) {
        return topicMapper.getTopicByPlateid(plateid);
    }

    @Override
    public TopicEntity getTopicbyid(String id) {
        return topicMapper.selectById(id);
    }
}
