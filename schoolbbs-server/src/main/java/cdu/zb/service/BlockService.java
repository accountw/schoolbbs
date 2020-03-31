package cdu.zb.service;

import cdu.zb.entity.BlockEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-03-03
 */
public interface BlockService extends IService<BlockEntity> {

    List<BlockEntity> getblock();
}
