package cdu.zb.service;

import cdu.zb.entity.PlateAdminEntity;
import cdu.zb.response.PlateAdminResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-02-01
 */
public interface PlateAdminService extends IService<PlateAdminEntity> {

    List<PlateAdminResponse> getPlateAdmin(String plateId);

    List<PlateAdminResponse> listAdmin(Integer index);

    Integer getcount();
}
