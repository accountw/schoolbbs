package cdu.zb.mapper;

import cdu.zb.entity.CricleMessageEntity;
import cdu.zb.response.CricleMessageResponse;
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
 * @since 2020-05-03
 */
@Repository
public interface CricleMessageMapper extends BaseMapper<CricleMessageEntity> {

    List<CricleMessageResponse> getMessage(@Param("cricleId") String cricleId,@Param("index") Integer index);
}
