package cdu.zb.service;

import cdu.zb.entity.PlateEntity;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-01-17
 */
public interface PlateService extends IService<PlateEntity> {

    List<PlateEntity> getplate();
}
