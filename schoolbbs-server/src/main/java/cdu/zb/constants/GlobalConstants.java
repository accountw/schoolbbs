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
     * 静态资源文件访问虚拟路径配置KEY
     */
    public static final String PROP_STATIC_RESOURCE_VIRTUALPATH = "coreland.static.virtualpath";

    /**
     * 静态资源文件访问虚拟路径配置KEY
     */
    public static final String PROP_SERVLET_VIRTUALPATH = "coreland.servlet.virtualpath";

    /**
     * 系统核心包appid
     */
    public static final String SYSTEM_CORE_APPID = "EIP.Core";
}
