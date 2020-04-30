package cdu.zb.util;

import cdu.zb.response.TopicResponse;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author accountw
 * @date 2020-04-26 09:55
 * @description: 基于堆排序的加权排序
 */
public class SortUtil {


    public static void quickSort(List<TopicResponse> list, int low, int high) {

        for(int i=0;i<list.size();i++){
            list.get(i).setNumber(list.get(i).getReplynum()*70
                    +list.get(i).getReplynum()/Duration.between(list.get(i).getFirstTime(), LocalDateTime.now()).toMillis()/7200000*20
            +list.get(i).getLikenum()*10);
        }
        if (low < high) {
            // 找寻基准数据的正确索引
            int index = getIndex(list, low, high);

            // 进行迭代对index之前和之后的数组进行相同的操作使整个数组变成有序
            quickSort(list, 0, index - 1);
            quickSort(list, index + 1, high);
        }

    }

    private static int getIndex(List<TopicResponse> list, int low, int high) {
        // 基准数据
        TopicResponse tmp = list.get(low);
        while (low < high) {
            // 当队尾的元素小于等于基准数据时,向前挪动high指针
            while (low < high && list.get(high).getNumber() <= tmp.getNumber()) {
                high--;
            }
            // 如果队尾元素小于tmp了,需要将其赋值给low
            list.set(low,list.get(high));
            // 当队首元素小于等于tmp时,向前挪动low指针
            while (low < high && list.get(low).getNumber() >= tmp.getNumber()) {
                low++;
            }
            // 当队首元素大于tmp时,需要将其赋值给high
            list.set(high,list.get(low));

        }
        // 跳出循环时low和high相等,此时的low或high就是tmp的正确索引位置
        // 由原理部分可以很清楚的知道low位置的值并不是tmp,所以需要将tmp赋值给arr[low]
        list.set(low,tmp);
        return low; // 返回tmp的正确位置
    }
}