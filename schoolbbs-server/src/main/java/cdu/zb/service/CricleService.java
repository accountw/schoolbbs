package cdu.zb.service;

import cdu.zb.entity.CricleEntity;
import cdu.zb.response.CricleResponse;
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
public interface CricleService extends IService<CricleEntity> {

    List<CricleResponse> getCriclrbyUserid(String userid, Integer index) throws UnsupportedEncodingException;

    Integer getFocusCricleCount(String userid);

    List<CricleResponse> getCricle(String userid, Integer index) throws UnsupportedEncodingException;
}
