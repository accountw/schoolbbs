package cdu.zb.mapper;

import cdu.zb.entity.UserEntity;
import cdu.zb.response.UserResponse;
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
 * @since 2020-01-09
 */
@Repository
public interface UserMapper extends BaseMapper<UserEntity> {

    List<UserResponse> getFocusList(@Param("userId") String userId);

    List<UserResponse> getFansList(@Param("userId") String userId);
}
