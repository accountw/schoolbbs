package cdu.zb.service.impl;

import cdu.zb.entity.PlateEntity;
import cdu.zb.mapper.PlateMapper;
import cdu.zb.response.PlateResponse;
import cdu.zb.service.PlateService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
 * @since 2020-01-17
 */
@Service
public class PlateServiceImpl extends ServiceImpl<PlateMapper, PlateEntity> implements PlateService {

    @Autowired
    private  PlateMapper plateMapper;
    @Override
    public List<PlateEntity> getplate(String blockid) {
        return plateMapper.selectList(new QueryWrapper<PlateEntity>().eq("block_id",blockid));
    }

    @Override
    public PlateResponse getPlateByid(String id) {
        return plateMapper.getPlateByid(id);
    }
}
