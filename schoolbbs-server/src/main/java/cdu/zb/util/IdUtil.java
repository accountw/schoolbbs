package cdu.zb.util;

/**
 * @author accountw
 * @date 2020-04-01 11:17
 * @description: 随机生成id
 */
public class IdUtil {
    private static byte[] lock = new byte[0];

    // 位数，默认是8位
    private final static long w = 100000000;

    public static String createID() {
        long r = 0;
        synchronized (lock) {
            r = (long) ((Math.random() + 1) * w);
        }

        return System.currentTimeMillis() + String.valueOf(r).substring(1);
    }
}
