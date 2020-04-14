package cdu.zb.service.impl;

import cdu.zb.dto.BanDto;
import cdu.zb.entity.BanEntity;
import cdu.zb.mapper.BanMapper;
import cdu.zb.response.BanResponse;
import cdu.zb.service.BanService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author accountw
 * @since 2020-01-09
 */
@Service
public class BanServiceImpl extends ServiceImpl<BanMapper, BanEntity> implements BanService {

    @Autowired
    private BanMapper banMapper;


    @Override
    public List<BanResponse> getBanList(Integer index) {
        index=index*15-15;
        List<BanResponse> list=banMapper.getBanList(index);
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getFreeTime().compareTo(LocalDateTime.now())==-1){
                banMapper.deleteById(list.get(i).getId());
                list.remove(i);
            }
        }
        return list;
    }

    @Override
    public Integer addBan(BanDto banDto) {
        banDto.setDealTime(LocalDateTime.now());
        banDto.setFreeTime(banDto.getDealTime().plusDays(banDto.getDate()));
        BanEntity banEntity=banMapper.selectOne(new QueryWrapper<BanEntity>().eq("uid",banDto.getUid()));
        if(banEntity!=null){
            if(banEntity.getFreeTime().compareTo(LocalDateTime.now())==-1){
                      banMapper.update(banDto,new QueryWrapper<BanEntity>().eq("uid",banDto.getUid()));
            }
                return 0;
        }
        return banMapper.insert(banDto);
    }
}
