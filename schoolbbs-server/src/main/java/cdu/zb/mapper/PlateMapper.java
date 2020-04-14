package cdu.zb.mapper;

import cdu.zb.entity.PlateEntity;
import cdu.zb.response.PlateResponse;
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
public interface PlateMapper extends BaseMapper<PlateEntity> {

    PlateResponse getPlateByid(@Param("id") String id);

    List<PlateResponse> getlist();
}
