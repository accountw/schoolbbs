package cdu.zb.util;

import java.util.Random;

/**
 * @author accountw
 * @date 2020-01-21 17:40
 * @description: 随机生成4位数的验证码
 */
public class CodeUtil {
    public static String getCode(){
        String str="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb=new StringBuilder(4);
        for(int i=0;i<4;i++)
        {
            char ch=str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();

    }

}
