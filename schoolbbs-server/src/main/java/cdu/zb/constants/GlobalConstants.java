package cdu.zb.constants;

/**
 * @author accountw
 * @date 2020-01-16 15:25
 * @description: 常量
 */
public class GlobalConstants {
    private GlobalConstants() {
    }

    /**
     * 默认当前查询页数
     */
    public static final int PAGE_NUM = 1;

    /**
     * 默认每页条数
     */
    public static final int PAGE_SIZE = 10;

    /**
     * 请求成功状态码
     */
    public static final String SUCCESS = "SUCCESS";

    /**
     * 请求失败
     */
    public static final String ERROR = "ERROR";

    /**
     * 参数错误
     */
    public static final String PARAMS_ERROR = "PARAMSERROR";

    /**
     * 未授权
     */
    public static final String NO_UNAUTHORIZED = "NO_AUTH";

    /**
     * 重载token
     */
    public static final String RELOAD = "RELOAD";

    public  static  final  String EXPIRED="EXPIRED";
    public  static  final  String COLLECTED="COLLECTED";
}
