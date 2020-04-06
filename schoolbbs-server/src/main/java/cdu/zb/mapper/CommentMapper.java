package cdu.zb.mapper;

import cdu.zb.entity.CommentEntity;
import cdu.zb.response.CommentResponse;
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
 * @since 2020-03-31
 */
@Repository
public interface CommentMapper extends BaseMapper<CommentEntity> {

    List<CommentResponse> getCommentByreplyid(@Param("replyid") String replyid);
}
