package cdu.zb.mapper;

import cdu.zb.entity.MessageEntity;
import cdu.zb.response.MessageResponse;
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
 * @since 2020-04-27
 */
@Repository
public interface MessageMapper extends BaseMapper<MessageEntity> {

    List<MessageResponse> getMessage(@Param("userid") String userid, @Param("index")Integer index);

    Integer getcount(String userid);
}
