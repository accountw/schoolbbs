package cdu.zb.service;

import cdu.zb.entity.CricleMessageEntity;
import cdu.zb.response.CricleMessageResponse;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author accountw
 * @since 2020-05-03
 */
public interface CricleMessageService extends IService<CricleMessageEntity> {

    List<CricleMessageResponse> getMessage(String cricleId,Integer index) throws UnsupportedEncodingException;
}
