package cdu.zb.service.impl;

import cdu.zb.entity.CollectEntity;
import cdu.zb.mapper.CollectMapper;
import cdu.zb.response.CollectResponse;
import cdu.zb.service.CollectService;
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
 * @since 2020-04-14
 */
@Service
public class CollectServiceImpl extends ServiceImpl<CollectMapper, CollectEntity> implements CollectService {

    @Autowired
    private CollectMapper collectMapper;


    @Override
    public List<CollectResponse> getCollectPlate(String userId) {
        return collectMapper.getCollectPlate(userId);
    }
}
