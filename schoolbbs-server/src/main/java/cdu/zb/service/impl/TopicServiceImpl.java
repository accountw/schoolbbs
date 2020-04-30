package cdu.zb.service.impl;

import cdu.zb.dto.TopicDto;
import cdu.zb.entity.TopicEntity;
import cdu.zb.entity.UserEntity;
import cdu.zb.mapper.TopicMapper;
import cdu.zb.mapper.UserMapper;
import cdu.zb.response.TopicResponse;
import cdu.zb.service.TopicService;
import cdu.zb.util.RedisUtil;
import cdu.zb.util.SortUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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


    private static final Logger LOG = LoggerFactory.getLogger(TopicServiceImpl.class);
    @Autowired
    private  TopicMapper topicMapper;
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private  RedisUtil redisUtil;
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
        index=index*15-15;
        List<TopicResponse> list=topicMapper.getTopicByPlateid(plateid,index);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
            list.get(i).setTitle(new String(decoder.decode(list.get(i).getTitle()),"UTF-8"));
        }
        return list;
    }

    @Override
    public TopicResponse getTopicbyid(String id) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
       TopicResponse topicResponse=topicMapper.getTopicbyid(id);
        topicResponse.setContext(new String(decoder.decode(topicResponse.getContext()),"UTF-8"));
        topicResponse.setTitle(new String(decoder.decode(topicResponse.getTitle()),"UTF-8"));
        if(topicResponse.getSign()!=null){
            topicResponse.setSign(new String(decoder.decode(topicResponse.getSign()),"UTF-8"));
        }
        return topicResponse;
    }

    @Override
    public Integer saveTopic(TopicDto topicDto) throws UnsupportedEncodingException {
        topicDto.setFirstTime(LocalDateTime.now());
        topicDto.setLastTime(LocalDateTime.now());
        Base64.Encoder encoder = Base64.getEncoder();
        UserEntity userEntity=userMapper.selectById(topicDto.getUserId());
        userEntity.setCount(userEntity.getCount()+1);
        userEntity.setExp(userEntity.getExp()+3);
        userMapper.updateById(userEntity);
        topicDto.setContext(encoder.encodeToString(topicDto.getContext().getBytes("UTF-8")));
        topicDto.setTitle(encoder.encodeToString(topicDto.getTitle().getBytes("UTF-8")));
        return topicMapper.insert(topicDto);
    }

    @Override
    public List<TopicResponse> getTopicFlow(Integer index) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        index=index*15-15;
        List<TopicResponse> list=topicMapper.getTopicFlow(index);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
            list.get(i).setTitle(new String(decoder.decode(list.get(i).getTitle()),"UTF-8"));
        }
        return list;
    }

    @Override
    public List<TopicResponse> getTopicByUserid(Integer index, String userId) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        index=index*15-15;
        List<TopicResponse> list=topicMapper.getTopicByUserid(index,userId);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
            list.get(i).setTitle(new String(decoder.decode(list.get(i).getTitle()),"UTF-8"));
        }
        return list;
    }

    @Override
    public Integer addcount(String id) {
        TopicEntity topicEntity=topicMapper.getTopicbyid(id);
        topicEntity.setCount(topicEntity.getCount()+1);
        return topicMapper.updateById(topicEntity);

    }

    @Override
    public Integer deletecount(String id) {
        TopicEntity topicEntity=topicMapper.getTopicbyid(id);
        topicEntity.setCount(topicEntity.getCount()-1);
        return topicMapper.updateById(topicEntity);
    }

    @Override
    public List<TopicResponse> getTop() throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        Object str=redisUtil.get("top");
        List<TopicResponse> topicResponseList = new ArrayList<>();
        if(str!=null){
            String s=str.toString();
           topicResponseList=JSON.parseArray(s,TopicResponse.class);
        }

        if(topicResponseList.size()==0){
            LOG.info("初始化热度排名");
            LocalDateTime localDateTime=LocalDateTime.now().minusDays(7);
            List<TopicResponse> list=topicMapper. getTop(localDateTime);
            SortUtil.quickSort(list,0,list.size()-1);
            int j=15;
            if(list.size()<15){
                j=list.size();
            }
            for(int i=0;i<j;i++){
                topicResponseList.add(list.get(i));
            }
            String strJson = JSON.toJSONString(topicResponseList);
            redisUtil.set("top",strJson);
            redisUtil.set("refresh",JSON.toJSONString(LocalDateTime.now().plus(10, ChronoUnit.MINUTES)));
        }else{
            String refresh = redisUtil.get("refresh").toString();
            String torefresh= (String) JSON.parse(refresh);
            LocalDateTime local=LocalDateTime.parse(torefresh);
            if(local.compareTo(LocalDateTime.now())<0){
                LOG.info("刷新热度排名");
                redisUtil.set("refresh",JSON.toJSONString(LocalDateTime.now().plus(10, ChronoUnit.MINUTES)));
                LocalDateTime localDateTime=LocalDateTime.now().minusDays(7);
                List<TopicResponse> list=topicMapper. getTop(localDateTime);
                SortUtil.quickSort(list,0,list.size()-1);
                int j=15;
                if(list.size()<15){
                    j=list.size();
                }
                topicResponseList.clear();
                for(int i=0;i<j;i++){
                    topicResponseList.add(i,list.get(i));
                }
                String strJson = JSON.toJSONString(topicResponseList);
                redisUtil.set("top",strJson);
            }
        }


        for (int i = 0; i < topicResponseList.size(); i++) {
            topicResponseList.get(i).setContext(new String(decoder.decode(topicResponseList.get(i).getContext()),"UTF-8"));
            topicResponseList.get(i).setTitle(new String(decoder.decode(topicResponseList.get(i).getTitle()),"UTF-8"));
        }
        return topicResponseList;
    }

    @Override
    public List<TopicResponse> getCollectTopic(String userId, Integer index) throws UnsupportedEncodingException {
        Base64.Decoder decoder = Base64.getDecoder();
        index=index*15-15;
        List<TopicResponse> list=topicMapper.getCollectTopic(userId,index);
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
            list.get(i).setTitle(new String(decoder.decode(list.get(i).getTitle()),"UTF-8"));
        }
        return list;
    }
}
