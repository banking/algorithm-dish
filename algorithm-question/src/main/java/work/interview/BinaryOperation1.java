package work.interview;

/**
 * Created by banking on 17/5/10.
 * 问题描述：有一个正整数数组长度为N，和另一个正整数数组长度为N+1。其中N个数都相同，求出不同的数
 *
 * 异或的英文单词：exclusive or
 */
public class BinaryOperation1 {
    public static void main(String args[]) {
        function(new int[]{9, 4, 70}, new int[]{9, 36, 4, 70});
    }

    public static int function(int[] rawArray1, int[] rawArray2) {
        int result = 0;
        for (int i : rawArray1) {
            result = result ^ i;
        }
        for (int j : rawArray2) {
            result = result ^ j;
        }
//        System.out.print(result);
        return result;
    }
}
