package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.dto.CricleMessageDto;
import cdu.zb.entity.CricleEntity;
import cdu.zb.entity.CricleMessageEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.CricleMessageResponse;
import cdu.zb.service.CricleMessageService;
import cdu.zb.service.CricleService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-05-03
 */
@RestController
@RequestMapping("/api/criclemessage")
public class CricleMessageController extends BaseApiController {


    @Autowired
    private CricleMessageService cricleMessageService;
    @Autowired
    private CricleService cricleService;

    @GetMapping(value = "/getMessage",name="得到消息")
    public JsonResult<List<CricleMessageResponse>> getMessage(String cricleId,Integer index) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",cricleMessageService.getMessage(cricleId,index));
    }
    @PostMapping(value = "addMessage",name="添加")
    public JsonResult<String> addMessage(@RequestBody CricleMessageDto cricleMessageDto) throws UnsupportedEncodingException {
        cricleMessageDto.setCreateTime(LocalDateTime.now());
        Base64.Encoder encoder = Base64.getEncoder();
        cricleMessageDto.setContext(encoder.encodeToString(cricleMessageDto.getContext().getBytes("UTF-8")));
        CricleEntity cricleEntity=cricleService.getById(cricleMessageDto.getCricleId());
        cricleEntity.setReplynum(cricleEntity.getReplynum()+1);
        cricleService.updateById(cricleEntity);
        cricleMessageService.save(cricleMessageDto);
        return jr(GlobalConstants.SUCCESS,"发表成功");
    }

    @GetMapping(value = "/getCount",name="得到消息")
    public JsonResult<Integer> getCount(String cricleId) throws UnsupportedEncodingException {
        return jr(GlobalConstants.SUCCESS,"获取成功",cricleMessageService.count(new QueryWrapper<CricleMessageEntity>()
        .eq("cricle_id",cricleId)));
    }

}
