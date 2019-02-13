package work.interview;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 *
 * Median of Two Sorted Arrays
 *
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 *
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 *
 * You may assume nums1 and nums2 cannot be both empty.
 *
 * Example 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * The median is 2.0
 * Example 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * The median is (2 + 3)/2 = 2.5
 */
public class ArrayOperation1 {
    /**
     * nums1和nums2都是从小到大排列的有序整数数组
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int count1 = nums1.length;
        int count2 = nums2.length;
        int totalCount = count1 +  count2;

        if (totalCount % 2 == 1) {
            return findKthMin(nums1, nums2, (totalCount + 1)/2);
        } else {
            return ((double) (findKthMin(nums1, nums2, totalCount/2) + findKthMin(nums1, nums2, totalCount/2 + 1))) / 2;
        }

    }

    public int findKthMin(int[] nums1, int[] nums2, int k) {
        int count1 = nums1.length;
        int count2 = nums2.length;
        if (count1 > count2) {
            return findKthMin(nums2 , nums1, k);
        }
        if (count1 == 0) {
            return nums2[k-1];
        }
        if (k == 1) {
            return Math.min(nums1[0], nums2[0]);
        }

        int findKthForA = Math.min(count1, k/2);
        int findKthForB = k - findKthForA;

        if (nums1[findKthForA - 1] > nums2[findKthForB - 1]) {
            return findKthMin(nums1, Arrays.copyOfRange(nums2, findKthForB, count2), k - findKthForB);
        } else if (nums1[findKthForA - 1] < nums2[findKthForB - 1]) {
            return findKthMin(Arrays.copyOfRange(nums1, findKthForA, count1), nums2, k - findKthForA);
        } else {
            return nums1[findKthForA - 1];
        }
    }

    public double findMedianSortedArraysByIndex(int[] nums1, int[] nums2) {
        int count1 = nums1.length;
        int count2 = nums2.length;
        int totalCount = count1 +  count2;

        if (totalCount % 2 == 1) {
            return findKthMinByIndex(nums1, 0,nums2, 0,(totalCount + 1)/2);
        } else {
            return ((double) (findKthMinByIndex(nums1, 0,nums2, 0,totalCount/2) +
                    findKthMinByIndex(nums1, 0,nums2, 0,totalCount/2 + 1))) / 2;
        }

    }

    public int findKthMinByIndex(int[] nums1, int abortCount1,int[] nums2, int abortCount2,int k) {
        int count1 = nums1.length;
        int count2 = nums2.length;
        if (count1 - abortCount1 > count2 - abortCount2) {
            return findKthMinByIndex(nums2 , abortCount2, nums1,abortCount1, k);
        }
        if (count1-abortCount1 == 0) {
            return nums2[k-1 + abortCount2];
        }
        if (k == 1) {
            return Math.min(nums1[abortCount1], nums2[abortCount2]);
        }

        int findKthFor1 = Math.min(count1-abortCount1, k/2);
        int findKthFor2 = k - findKthFor1;

        if (nums1[findKthFor1 - 1 + abortCount1] > nums2[findKthFor2 - 1 + abortCount2]) {
            return findKthMinByIndex(nums1, abortCount1, nums2, abortCount2 + findKthFor2, k - findKthFor2);
        } else if (nums1[findKthFor1 - 1 + abortCount1] < nums2[findKthFor2 - 1 + abortCount2]) {
            return findKthMinByIndex(nums1, abortCount1+ findKthFor1,nums2, abortCount2,k - findKthFor1);
        } else {
            return nums1[findKthFor1 - 1 + abortCount1];
        }
    }

    public static void main(String args[]) {
        System.out.println(new ArrayOperation1().findMedianSortedArrays(new int[] {2,3,6,10}, new int[] {1,8,9,19}));
        System.out.println(new ArrayOperation1().findMedianSortedArrays(new int[] {1,1}, new int[] {1,2}));
        System.out.println(new ArrayOperation1().findMedianSortedArrays(new int[] {2,4}, new int[] {1,3,5,6}));

        System.out.println(new ArrayOperation1().findMedianSortedArraysByIndex(new int[] {2,3,6,10}, new int[] {1,8,9,19}));
        System.out.println(new ArrayOperation1().findMedianSortedArraysByIndex(new int[] {1,1}, new int[] {1,2}));
        System.out.println(new ArrayOperation1().findMedianSortedArraysByIndex(new int[] {2,4}, new int[] {1,3,5,6}));
    }


}
