package cdu.zb.service;

import cdu.zb.entity.CollectEntity;
import cdu.zb.response.CollectResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-04-14
 */
public interface CollectService extends IService<CollectEntity> {

    List<CollectResponse> getCollectPlate(String userId);
}
