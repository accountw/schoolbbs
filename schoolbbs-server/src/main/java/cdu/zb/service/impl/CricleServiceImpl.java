package cdu.zb.service.impl;

import cdu.zb.entity.CricleEntity;
import cdu.zb.mapper.CricleMapper;
import cdu.zb.response.CricleResponse;
import cdu.zb.service.CricleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author accountw
 * @since 2020-05-03
 */
@Service
public class CricleServiceImpl extends ServiceImpl<CricleMapper, CricleEntity> implements CricleService {

    @Autowired
    private  CricleMapper cricleMapper;
    @Override
    public List<CricleResponse> getCriclrbyUserid(String userid, Integer index) throws UnsupportedEncodingException {
        index=index*10-10;
        List<CricleResponse> list=cricleMapper.getCriclrbyUserid(userid,index);
        Base64.Decoder decoder = Base64.getDecoder();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getContext()!=null){
                list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
            }
        }

        return list;
    }

    @Override
    public Integer getFocusCricleCount(String userid) {
        return cricleMapper.getFocusCricleCount(userid);
    }

    @Override
    public List<CricleResponse> getCricle(String userid, Integer index) throws UnsupportedEncodingException {
        index=index*10-10;
        List<CricleResponse> list=cricleMapper.getCricle(userid,index);
        Base64.Decoder decoder = Base64.getDecoder();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getContext()!=null){
                list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
            }
        }

        return list;
    }
}
