package cdu.zb.service;

import cdu.zb.dto.BanDto;
import cdu.zb.entity.BanEntity;
import cdu.zb.response.BanResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */
public interface BanService extends IService<BanEntity> {

    List<BanResponse> getBanList(Integer index);

    Integer addBan(BanDto banDto);
}
