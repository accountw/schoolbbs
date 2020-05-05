package cdu.zb.mapper;

import cdu.zb.entity.BanEntity;
import cdu.zb.response.BanResponse;
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
 * @since 2020-01-09
 */
@Repository
public interface BanMapper extends BaseMapper<BanEntity> {

    List<BanResponse> getBanList(@Param("index") Integer index, @Param("local")LocalDateTime local);


}
