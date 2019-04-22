package work.interview;

/**
 * 找出一个整数数组最大和的队列.至少包含一个正整数。
 * 示例：【9,10,-5,6,-78,10,4,-34,80,-1,-100,129】
 */
public class ArrayFindMaxQueue {

    int markedStartIndex =0 ;
    int markedEndIndex = 0;
    int markedMax = 0;
    boolean startCalculate;
    public void findMax(int startIndex, int cachedCount, int[] datas) {
        if (startIndex >= datas.length) {
            return;
        }
        for (;startIndex < datas.length;startIndex++) {
            int currentValue = datas[startIndex];
            if (currentValue < 0) {
                if (cachedCount > 0) {
                     if (currentValue + cachedCount <= 0) {
                         findMax(startIndex + 1, 0 , datas);
                     } else {
                         findMax(startIndex + 1, currentValue + cachedCount, datas);
                     }
                } else { //cachedCount == 0
                    findMax(startIndex + 1, 0 , datas);
                }
            } else {
                if (cachedCount > 0) {
                    if ((currentValue + cachedCount) > markedMax) {
                        markedMax = currentValue + cachedCount;
                    }
                    findMax(startIndex + 1, currentValue + cachedCount, datas);
                } else { //cachedCount == 0
                    //Assert (cachedCount == 0)
                    if ((currentValue + cachedCount) > markedMax) {
                        markedMax = currentValue + cachedCount;
                    }
                    findMax(startIndex + 1, currentValue + cachedCount, datas);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] datas = new int[] {9,10,-5,6,-78}; //,10,4,-34,80,-1,-100,129
        ArrayFindMaxQueue arrayFindMaxQueue = new ArrayFindMaxQueue();
        arrayFindMaxQueue.findMax(0, 0, datas);
        System.out.println(arrayFindMaxQueue.markedMax);
    }
}
