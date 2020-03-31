package cdu.zb.controller;


import cdu.zb.constants.GlobalConstants;
import cdu.zb.entity.BlockEntity;
import cdu.zb.jsonresult.BaseApiController;
import cdu.zb.jsonresult.JsonResult;
import cdu.zb.service.BlockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author accountw
 * @since 2020-03-03
 */
@RestController
@RequestMapping("/api/block")
public class BlockController extends BaseApiController {

    private static final Logger LOG = LoggerFactory.getLogger(BlockController.class);

    @Autowired
    private BlockService blockService;

    /**
     * @description: 得到模块
     * @author accountw
     * @date 2020/3/3 18:09
     * @param []
     * @return cdu.zb.jsonresult.JsonResult<java.util.List < cdu.zb.entity.BlockEntity>>
     **/
    @GetMapping(value = "/getblock",name="得到模块")
    public JsonResult<List<BlockEntity>> getblock(){
        return  jr(GlobalConstants.SUCCESS,"获取成功",blockService.getblock());
    }
}
