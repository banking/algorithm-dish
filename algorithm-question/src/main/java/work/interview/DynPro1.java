package work.interview;

/**
 * Created by banking on 17/9/11.
 * 问题描述：一个字串由7个A和7个B组成，且满足所有的前缀字符串中A的个数大于等于B的个数，求这样的字符串个数
 * AABB ABAB
 * AAA BBB
 * 动态规划问题
 */
public class DynPro1 {

    static int[][] cacheValue;
    public static void main(String args[]) {
        int result = dynProFunction(7, 7);
        System.out.print("7 A and 7 B,final count:" + result);
    }

    public static int dynProFunction(int countA, int countB) {
        if (cacheValue == null) {
            cacheValue = new int[7][7];
        }
        if (countA > countB || countA == 0 || countB == 0) {
            return 0;
        }
        if (countA == 1) {
            cacheValue[0][countB - 1] = 1;
            return 1;
        }
        if (countA == countB) {
            if (cacheValue[countA - 1][countB -1] > 0) {
                return cacheValue[countA - 1][countB -1];
            }
            cacheValue[countA - 1][countB -1] = dynProFunction(countA -1, countB)
                    + dynProFunction(countA -1, countB -1);
            return cacheValue[countA - 1][countB -1];
        }
        if (countA < countB) {
            if (cacheValue[countA - 1][countB -1] > 0) {
                return cacheValue[countA - 1][countB -1];
            }
            cacheValue[countA - 1][countB -1] = dynProFunction(countA -1 , countB)
                    + dynProFunction(countA , countB -1);
            return cacheValue[countA - 1][countB -1];
        }
        return 0;
    }
}
