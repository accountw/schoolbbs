package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.response.MessageResponse;
import cdu.zb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-04-27
 */
@RestController
@RequestMapping("/api/message")
public class MessageController extends BaseApiController {

    @Autowired
    private MessageService messageService;

    @GetMapping(value = "/getMessage",name="获取信息")
    public JsonResult<List<MessageResponse>>  getMessage(String userid,Integer index) throws UnsupportedEncodingException {
        return  jr(GlobalConstants.SUCCESS,"index",messageService.getMessage(userid,index));
    }

    @GetMapping(value = "/getcount",name="获取条数")
    public  JsonResult<Integer> getcount(String userid){
        return  jr(GlobalConstants.SUCCESS,"获取成功",messageService.getcount(userid));
    }
}
