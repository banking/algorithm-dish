package work.interview;

/**
 * Longest Palindromic Substring
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 *
 * Example 1:
 *
 * Input: "babad"
 * Output: "bab"
 * Note: "aba" is also a valid answer.
 * Example 2:
 *
 * Input: "cbbd"
 * Output: "bb"
 */
public class StringOperation2 {
    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        char[] chars = s.toCharArray();
        String longestPalindrome = chars[0] + "";
        for (int i = 0;i<chars.length;i++) {

            //find evenPalindrome
            int evenIndex = i;
            int evenLength = 0;
            String evenString = "";
            while (evenIndex - evenLength > 0 && evenIndex + evenLength < chars.length) {
                if (chars[evenIndex - 1 - evenLength] == chars[evenIndex + evenLength]) {
                    evenString = chars[evenIndex - 1 - evenLength] + evenString + chars[evenIndex + evenLength];
                    if (longestPalindrome.length() < evenString.length()) {
                        longestPalindrome = evenString;
                    }
                    evenLength ++;
                } else {
                    break;
                }
            }
            //find addPalindrome
            int oddIndex = i;
            int oddLength = 0;
            if (oddIndex > 0) {
                String oddString = chars[oddIndex - 1] + "";
                while (oddIndex - oddLength > 1 && oddIndex + oddLength < chars.length) {
                    if (chars[oddIndex - 2 - oddLength] == chars[oddIndex + oddLength]) {
                        oddString = chars[oddIndex - 2 - oddLength] + oddString + chars[oddIndex + oddLength];
                        if (longestPalindrome.length() < oddString.length()) {
                            longestPalindrome = oddString;
                        }
                        oddLength ++;
                    } else {
                        break;
                    }
                }
            }

        }
        return longestPalindrome;
    }

    public static void main(String args[]) {
        System.out.println(new StringOperation2().longestPalindrome("cbbd"));

        System.out.println(new StringOperation2().longestPalindrome("cbab"));

        System.out.println(new StringOperation2().longestPalindrome("cbadfadaafaafafffaddbbvaadabab"));
    }
}
