package work.interview;

/**
 * Created by banking on 17/4/24.
 * 问题描述：若各个数字的值在1-100之间，求重复数字出现的最多的次数。
 */
public class BucketSort1 {

    public static void main(String args[]) {
        sortfunction(100, new int[]{9 , 10 ,9, 80, 4, 4, 4});
    }

    public static int sortfunction(int count, int[] rawDatas) {
        int[] datas = new int[count];
        int maxOccurTimes = 0;
        for(int i : rawDatas) {
            if (maxOccurTimes < ++datas[i]) {
                maxOccurTimes = datas[i];
            }
        }
        System.out.print(maxOccurTimes);
        return maxOccurTimes;
//        for(int i : rawDatas) maxOccurTimes = maxOccurTimes < ++datas[i]? datas[i] : maxOccurTimes;
    }
}
