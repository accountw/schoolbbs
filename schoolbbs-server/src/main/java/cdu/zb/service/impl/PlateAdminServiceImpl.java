package cdu.zb.service.impl;

import cdu.zb.entity.PlateAdminEntity;
import cdu.zb.mapper.PlateAdminMapper;
import cdu.zb.response.PlateAdminResponse;
import cdu.zb.service.PlateAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author accountw
 * @since 2020-02-01
 */
@Service
public class PlateAdminServiceImpl extends ServiceImpl<PlateAdminMapper, PlateAdminEntity> implements PlateAdminService {

    @Autowired
    private  PlateAdminMapper plateAdminMapper;
    @Override
    public List<PlateAdminResponse> getPlateAdmin(String plateId) {
        return plateAdminMapper.getPlateAdmin(plateId);
    }

    @Override
    public List<PlateAdminResponse> listAdmin(Integer index) {
        index=index*15-15;
        return plateAdminMapper.listAdmin(index);
    }

    @Override
    public Integer getcount() {
        return plateAdminMapper.getcount();
    }
}
