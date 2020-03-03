package cdu.zb.mapper;

import cdu.zb.dto.TopicDto;
import cdu.zb.entity.TopicEntity;
import cdu.zb.response.TopicResponse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
