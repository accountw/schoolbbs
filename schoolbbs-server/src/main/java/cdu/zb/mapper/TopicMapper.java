package cdu.zb.mapper;

import cdu.zb.entity.TopicEntity;
import cdu.zb.response.TopicResponse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author accountw
 * @since 2020-02-10
 */
@Repository
public interface TopicMapper extends BaseMapper<TopicEntity> {

    List<TopicResponse> getFirstTopices();

    List<TopicResponse> getTopicByPlateid(@Param("plateid") String plateid,@Param("index")Integer index);

    TopicResponse getTopicbyid(String id);

    List<TopicResponse> getTopicFlow(@Param("index")Integer index);

    List<TopicResponse> getTopicByUserid(@Param("index")Integer index, @Param("userId")String userId);

    List<TopicResponse> getTop(@Param("localDateTime") LocalDateTime localDateTime);

    List<TopicResponse> getCollectTopic(@Param("userId")String userId, @Param("index")Integer index);
}
