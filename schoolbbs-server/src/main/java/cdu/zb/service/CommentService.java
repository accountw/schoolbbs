package cdu.zb.service;

import cdu.zb.dto.CommentDto;
import cdu.zb.entity.CommentEntity;
import cdu.zb.response.CommentResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-03-31
 */
public interface CommentService extends IService<CommentEntity> {

    List<CommentResponse> getCommentByreplyid(String replyid,Integer index) throws UnsupportedEncodingException;

    Integer saveComment(CommentDto commentDto);
}
