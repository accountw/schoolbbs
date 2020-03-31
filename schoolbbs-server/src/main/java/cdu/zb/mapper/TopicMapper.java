package cdu.zb.mapper;

import cdu.zb.entity.TopicEntity;
import cdu.zb.response.TopicResponse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

    List<TopicResponse> getTopicByPlateid(@Param("plateid") String plateid);
}
