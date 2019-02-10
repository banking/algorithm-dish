package work.interview;

/**
 * From LeetCode
 * Longest Substring Without Repeating Characters.
 * Given a string, find the length of the longest substring without repeating characters.
 *
 * Example 1:
 *
 * Input: "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 * Example 2:
 *
 * Input: "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 * Example 3:
 *
 * Input: "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 *              Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class StringOperation1 {
    public int lengthOfLongestSubstring(String s) {
        if ( s == null || s.length() == 0) {
            return 0;
        }
        int maxCount = 1;
        int startIndex = 0;
        for (;startIndex<s.length();startIndex++) {
            int count = 1;
            int endIndex = startIndex + 1;
            if (endIndex >= s.length()) {
                break;
            }
            String temp = s.substring(startIndex,endIndex);
            String nextChar = s.substring(endIndex, endIndex + 1);
            while (!temp.contains(nextChar)) {
                count ++;
                temp = temp + nextChar;
                if (maxCount < count) {
                    maxCount = count;
                }
                endIndex ++;
                if (endIndex >= s.length()) {
                    break;
                }
                nextChar = s.substring(endIndex, endIndex + 1);
            }
        }
        return maxCount;
    }

    public static void main(String args[]) {
       int count =  new StringOperation1().lengthOfLongestSubstring("adfdsfkfhaadfcksdlad");
       System.out.println(count);
    }
}
