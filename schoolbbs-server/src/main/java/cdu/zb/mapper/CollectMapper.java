package cdu.zb.mapper;

import cdu.zb.entity.CollectEntity;
import cdu.zb.response.CollectResponse;
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
 * @since 2020-04-14
 */
@Repository
public interface CollectMapper extends BaseMapper<CollectEntity> {

    List<CollectResponse> getCollectPlate(@Param("userId") String userId);
}
