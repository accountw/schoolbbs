package cdu.zb.service.impl;

import cdu.zb.entity.CricleMessageEntity;
import cdu.zb.mapper.CricleMessageMapper;
import cdu.zb.response.CricleMessageResponse;
import cdu.zb.service.CricleMessageService;
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
public class CricleMessageServiceImpl extends ServiceImpl<CricleMessageMapper, CricleMessageEntity> implements CricleMessageService {

    @Autowired
    public CricleMessageMapper cricleMessageMapper;
    @Override
    public List<CricleMessageResponse> getMessage(String cricleId,Integer index) throws UnsupportedEncodingException {
        index=index*10-10;
        List<CricleMessageResponse> list=cricleMessageMapper.getMessage(cricleId,index);
        Base64.Decoder decoder = Base64.getDecoder();
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getContext()!=null){
                list.get(i).setContext(new String(decoder.decode(list.get(i).getContext()),"UTF-8"));
            }
        }
        return list;
    }
}
