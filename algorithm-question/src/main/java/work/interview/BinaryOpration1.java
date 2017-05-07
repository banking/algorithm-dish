package work.interview;

import java.util.ArrayList;

/**
 * Created by banking on 17/4/24.
 * 问题描述：有一个正整数数组长度为N，和另一个正整数数组长度为N+2。其中N个数都相同，求出不同的2个数
 *
 * 异或的英文单词：exclusive or
 */
public class BinaryOpration1 {

    public static void main(String args[]) {

        function(new int[]{9,4,70}, new int[]{9 , 3 ,5, 4, 70});
    }

    public static int[] function(int[] rawArray1, int[] rawArray2) {
        int[] result = new int[2];
        int exclusiveOrSum = 0;
        for(int i : rawArray1) {
            exclusiveOrSum = exclusiveOrSum ^ i;
        }
        for(int j : rawArray2) {
            exclusiveOrSum = exclusiveOrSum ^ j;
        }
        int indexLastBit1 = 0;
        while (true) {
            if ((exclusiveOrSum & (int)(Math.pow(2, indexLastBit1))) > 0) {
                break;
            }
            //todo handle exception
            indexLastBit1 ++;
        }
        int spiltNum = (int)(Math.pow(2, indexLastBit1));
        for(int i : rawArray1) {
            if ((i & spiltNum) > 0) {
                result[0] = result[0] ^ i;
            } else {
                result[1] = result[1] ^ i;
            }
        }
        for (int j : rawArray2) {
            if ((j & spiltNum) > 0) {
                result[0] = result[0] ^ j;
            } else {
                result[1] = result[1] ^ j;
            }
        }
        System.out.println("first num:" + result[0]);
        System.out.println("second num:" + result[1]);
        return result;
    }
}
