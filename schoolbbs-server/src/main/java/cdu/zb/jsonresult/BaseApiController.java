package cdu.zb.jsonresult;

import cdu.zb.constants.GlobalConstants;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author accountw
 * @date 2020-01-16 15:21
 * @description: API接口基类，所有返回json格式数据应该继承该类
 */
@ResponseBody
public abstract class BaseApiController{

    protected <T> JsonResult<T> jr(){
        return jr(GlobalConstants.SUCCESS, "");
    }

    protected <T> JsonResult<T> jr(String message){
        return jr(GlobalConstants.SUCCESS, message);
    }

    protected <T> JsonResult<T> jr(T t){
        return jr(GlobalConstants.SUCCESS, "", t);
    }

    protected  <T> JsonResult<T> jr(String code, String message){
        return new JsonResult<>(code, message.toString());
    }

    protected  <T> JsonResult<T> jr(String message, T t){
        return jr(GlobalConstants.SUCCESS, message, t);
    }

    protected  <T> JsonResult<T> jr(String code, String message, T t){
        return new JsonResult<>(code, message.toString(), t);
    }

    protected <T> PageResult<T> jr(IPage<T> page){
        return jr(GlobalConstants.SUCCESS, "", page);
    }

    protected <T> PageResult<T> jr(String message, IPage<T> page){
        return jr(GlobalConstants.SUCCESS, message, page);
    }

    protected <T> PageResult<T> jr(String code, String message, IPage<T> page){
        return new PageResult<>(code, message.toString(), page);
    }

}
