package work.interview;

/**
 * Wildcard Matching
 *
 * Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.
 *
 * '?' Matches any single character.
 * '*' Matches any sequence of characters (including the empty sequence).
 * The matching should cover the entire input string (not partial).
 *
 * Note:
 *
 * s could be empty and contains only lowercase letters a-z.
 * p could be empty and contains only lowercase letters a-z, and characters like ? or *.
 * Example 1:
 *
 * Input:
 * s = "aa"
 * p = "a"
 * Output: false
 * Explanation: "a" does not match the entire string "aa".
 * Example 2:
 *
 * Input:
 * s = "aa"
 * p = "*"
 * Output: true
 * Explanation: '*' matches any sequence.
 * Example 3:
 *
 * Input:
 * s = "cb"
 * p = "?a"
 * Output: false
 * Explanation: '?' matches 'c', but the second letter is 'a', which does not match 'b'.
 * Example 4:
 *
 * Input:
 * s = "adceb"
 * p = "*a*b"
 * Output: true
 * Explanation: The first '*' matches the empty sequence, while the second '*' matches the substring "dce".
 * Example 5:
 *
 * Input:
 * s = "acdcb"
 * p = "a*c?b"
 * Output: false
 */

/**
 * isMatch1() Time limit failed.
 * 经典的解法，使用二位数组memo(备忘录)缓存之前的计算结果。
 * Boolean[targetX][targetY]是被否赋值来决定是否进行重复计算。
 */
public class StringOperation3 {
    boolean hasTrimP = false;
    public boolean isMatch1(String s, String p) {
        if (!hasTrimP) {
            p = trimP(p);
            System.out.println(p);
            hasTrimP = true;
        }
        System.out.println(p);
        int indexS = 0;
        int indexP = 0;

        while (indexP < p.length()) {
            String charP = p.substring(indexP, indexP + 1);
            if (charP.equals("?")) {
                if (indexS >= s.length()) {
                    return false;
                }
                indexS++;
            } else if (charP.equals("*")) {
                if (indexP == p.length() - 1) { //最后一个*的情况
                    return true;
                }
                boolean matchAny = false;
                String remainP = p.substring(indexP + 1, p.length());
                int remindSIndex = indexS;
                while (remindSIndex <= s.length()) {
                    String remindS = s.substring(remindSIndex, s.length());
                    matchAny = isMatch1(remindS, remainP);
                    if (matchAny) {
                        return true;
                    }
                    remindSIndex++;
                }
                return false;
            } else {
                if (indexS >= s.length()) {
                    return false;
                }
                if (!charP.equals(s.substring(indexS, indexS + 1))) {
                    return false;
                }
                indexS++;
            }
            indexP++;
        }
        if (indexS < s.length()) {
            return false;
        }
        return true;
    }

    public String trimP(String rawP) {
        int firstStar = rawP.indexOf("*");
        int lastStar = rawP.lastIndexOf("*");
        if (firstStar >= 0 && firstStar != lastStar) {
            char[] charArray = rawP.toCharArray();
            boolean preStar = false;
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < charArray.length; i++) {
                if ("*".equals(String.valueOf(charArray[i]))) {
                    if (preStar) {
                        continue;
                    } else {
                        builder.append(charArray[i]);
                        preStar = true;
                    }
                } else {
                    preStar = false;
                    builder.append(charArray[i]);
                }
            }
            return builder.toString();
        } else {
            return rawP;
        }

    }

    Boolean[][] datas;
    public boolean isMatch2(String s, String p) {
        if (!hasTrimP) {
            p = trimP(p);
            System.out.println(p);
            hasTrimP = true;
        }
        datas = new Boolean[s.length()+1][p.length()+1];
        return match(s, p, 0, 0);
    }


    private boolean match(String s, String p, int i, int j) {
        if (datas[i][j] != null) { //import for use cache data
            return datas[i][j];
        }
        if (j == p.length()) {
            datas[i][j] = i == s.length();
            return datas[i][j];
        }

        char c = p.charAt(j);
        if(c == '*') {
            boolean useStar = i < s.length() && match(s, p, i+1, j);
            boolean notUseStar = match(s, p, i, j+1);
            datas[i][j] = useStar || notUseStar;
            return datas[i][j];
        } else if (c == '?') {
            datas[i][j] = i < s.length() && match(s, p, i+1, j+1);
            return datas[i][j];
        } else {
            datas[i][j] = i < s.length() && c == s.charAt(i) && match(s, p, i+1, j+1);
            return datas[i][j];
        }
    }

    public static void main(String args[]) {


        String s = "abc";
//        System.out.println(s.substring(3,3));
//        System.out.println(new StringOperation3().isMatch("pi", "?i*pi"));
//        System.out.println(new StringOperation3().isMatch("mississippi", "m??*ss*?i*pi"));
//        System.out.println(new StringOperation3().isMatch("ho", "ho**"));
//        System.out.println(new StringOperation3().isMatch("aa", "a"));
//        System.out.println(new StringOperation3().isMatch("adceb", "*a*b"));
//        System.out.println(new StringOperation3().isMatch("bbbbbbbabbaabbabbbbaaabbabbabaaabbababbbabbbabaaabaab", "b*b*ab**ba*b**b***bba"));
        System.out.println(new StringOperation3().isMatch2("abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb", "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"));
    }
}
