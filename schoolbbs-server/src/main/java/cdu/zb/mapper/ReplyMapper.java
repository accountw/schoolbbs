package cdu.zb.mapper;

import cdu.zb.entity.ReplyEntity;
import cdu.zb.response.ReplyResponse;
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
 * @since 2020-01-17
 */
@Repository
public interface ReplyMapper extends BaseMapper<ReplyEntity> {

    List<ReplyResponse> getReplybyTopicid(@Param("topicid")String topicid,@Param("index")Integer index );

    Integer getMaxStorey(@Param("topicId") String topicId);
}
