package cdu.zb.mapper;

import cdu.zb.entity.PlateAdminEntity;
import cdu.zb.response.PlateAdminResponse;
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
 * @since 2020-02-01
 */
@Repository
public interface PlateAdminMapper extends BaseMapper<PlateAdminEntity> {

    List<PlateAdminResponse> getPlateAdmin(@Param("plateId") String plateId);

    List<PlateAdminResponse> listAdmin(@Param("index")Integer index);

    Integer getcount();
}
