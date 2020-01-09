package cdu.zb.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author accountw
 * @date 2020-01-07 10:44
 * @description: md5加密
 */
public class MD5Util {
    public static String toMd5(String str){
        return DigestUtils.md5Hex(str);
    }
}
