package cdu.zb.mapper;

import cdu.zb.entity.CricleEntity;
import cdu.zb.response.CricleResponse;
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
public interface CricleMapper extends BaseMapper<CricleEntity> {

    List<CricleResponse> getCriclrbyUserid(@Param("userid") String userid,@Param("index") Integer index);

    Integer getFocusCricleCount(@Param("userid")String userid);

    List<CricleResponse> getCricle(@Param("userid") String userid,@Param("index") Integer index);
}
