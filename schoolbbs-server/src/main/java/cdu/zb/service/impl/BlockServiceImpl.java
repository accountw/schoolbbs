package cdu.zb.service.impl;

import cdu.zb.entity.BlockEntity;
import cdu.zb.mapper.BlockMapper;
import cdu.zb.service.BlockService;
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
 * @since 2020-03-03
 */
@Service
public class BlockServiceImpl extends ServiceImpl<BlockMapper, BlockEntity> implements BlockService {


    @Autowired
    private BlockMapper blockMapper;

    /**
     * @description: 获取模块
     * @author accountw
     * @date 2020/3/3 18:11
     * @param []
     * @return java.util.List<cdu.zb.entity.BlockEntity>
     **/
    @Override
    public List<BlockEntity> getblock() {
        return blockMapper.selectList(new QueryWrapper<BlockEntity>().orderByAsc("id"));
    }
}
