package cdu.zb.service;

import cdu.zb.dto.TopicDto;
import cdu.zb.entity.TopicEntity;
import cdu.zb.response.TopicResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-02-10
 */
public interface TopicService extends IService<TopicEntity> {

    List<TopicResponse> getFirstTopices();
}
