package self.algorithm.util;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Created by didi on 17/4/25.
 */
public class NumberConvert {
        // 一百二十三 => 123 123=>一百二十三
        static ArrayList<String> gap1;
        static ArrayList<String> gap2;
        static ArrayList<String> gap;
        static ArrayList<String> numbers;
        static ArrayList<String> zeros;

        final static int TAG_1 = 1;
        final static int TAG_10 = 2;
        final static int TAG_100 = 3;
        final static int TAG_1000 = 4;
        final static int TAG_YI = 5;
        final static String ZERO = "零";
        final static String WAN = "万";
        final static String YI = "亿";

        public static void main(String args[]) throws Exception {
            //init();
            int i1 = convertDescToNumber("三千零六十三");
            int i2 = convertDescToNumber("一千六百万三千零五");
            int i3 = convertDescToNumber("十五亿二百万");

            /**
             * 这个异常还没有解决，乱序计数问题；
             */
            int i4 = convertDescToNumber("五十三百");
            int i5 = convertDescToNumber("三十五"); //二千四十亿
            System.out.println(i1 + " " + i2 + " " + i3 + " " + i4+ " " + i5);

            int j1 = 2312837;
            int j2 = 2004;
            int j3 = 3000010;
            /**
             * 这个地方可以优化，汉字描述特征。一十五一般读作十五
             */
            int j4 = 102000; // TODO
            String j1Str = convertNumberToDesc(j1);
            String j2Str = convertNumberToDesc(j2);
            String j3Str = convertNumberToDesc(j3);
            String j4Str = convertNumberToDesc(j4);
            System.out.println(j1Str);
            System.out.println(j2Str);
            System.out.println(j3Str);
            System.out.println(j4Str);
        }

        private static void init() {
            gap1 = new ArrayList<String>();
            gap1.add("亿");
            gap1.add("万");
            gap2 = new ArrayList<String>();
            gap2.add("千");
            gap2.add("百");
            gap2.add("十");
            gap = new ArrayList<String>();
            gap.addAll(gap1);
            gap.addAll(gap2);

            numbers = new ArrayList<String>();
            numbers.add("一");
            numbers.add("二");
            numbers.add("三");
            numbers.add("四");
            numbers.add("五");
            numbers.add("六");
            numbers.add("七");
            numbers.add("八");
            numbers.add("九");

            zeros = new ArrayList<String>();
            zeros.add("零");
            zeros.add("〇");

        }

        /**
         * 只处理999999999以内正整数
         *
         * @param number
         * @return
         * @throws Exception
         */
        public static String convertNumberToDesc(int number) throws Exception {
            System.out.println(number);
            if(gap1==null){
                init();
            }
            /**
             * 前置异常判断
             */
            if (number < 0) {
                throw new Exception("数字是负数");
            }
            if (number == 0) {
                return ZERO;
            }
            final String numberStr = String.valueOf(number);
            char[] numberStrArray = numberStr.toCharArray();
            int length = numberStrArray.length;
            if (length > 9) {
                throw new Exception("数字太大了,仅支持0-999999999之间整数");
            }
            StringBuffer result = new StringBuffer();
            /**
             * 亿位处理
             */
            int i = 0;
            if (length == 9) {
                char yiChar = numberStrArray[0];
                i++;
                result.append(singleNumberDesc(String.valueOf(yiChar), TAG_YI));
            }

            /**
             * 万位到亿位之间处理
             */
            if (length > 4) {
                int size = length - 4;
                if (size > 4) {
                    size = 4;
                }
                char[] charArray1 = new char[size];
                for (int j = 0; i < numberStrArray.length - 4; i++, j++) {
                    charArray1[j] = numberStrArray[i];
                }
                final String temp = fourNumberDesc(charArray1);
                if(!temp.equals(ZERO) ){
                    result.append(temp);
                    result.append(WAN);
                }else{
                    result.append(temp);
                }
            }

            /**
             *
             */
            int size  = length;
            if (size > 4) {
                size = 4;
            }
            char[] charArray2 = new char[size];
            for (int j = 0; i < length; i++, j++) {
                charArray2[j] = numberStrArray[i];
            }
            final String temp2 = fourNumberDesc(charArray2);
            if(!temp2.equals(ZERO) ){
                result.append(temp2);
            }
            String finalString = trimZero(result.toString());
            return finalString;
        }

        /**
         * 防止多个非法零出现在汉字描述中
         * @param rawString
         * @return
         */
        private static String trimZero(String rawString){
            StringBuffer sb = new StringBuffer();
            boolean preZero = false;//上一个字符是否是零
            boolean beginZero = true;//是否以零开头
            char[] charArray = rawString.toCharArray();
            for(int i=0;i<charArray.length;i++){
                //前置去0
                if(i==0){
                    if(String.valueOf(charArray[i]).equals(ZERO)){
                        beginZero = true;
                    }else{
                        sb.append(charArray[i]);
                        beginZero = false;
                    }
                    continue;
                }
                if(i==charArray.length-1){
                    //源数据rawString末尾最多有一个零，可以这样处理;若末尾是零直接跳过
                    if(!String.valueOf(charArray[i]).equals(ZERO)){
                        sb.append(charArray[i]);
                        beginZero = false;
                    }
                    continue;
                }

                if(String.valueOf(charArray[i]).equals(ZERO)){
                    if(!preZero){
                        sb.append(charArray[i]);
                        preZero = true;
                    }
                }else{
                    sb.append(charArray[i]);
                    preZero = false;
                }
            }

            return sb.toString();
        }
        /**
         * 由于汉字计数习惯，需要四位进行一个分段计数，例如三千一百六十五    万/亿
         * @param charArray
         * @param needZerobegin
         * @return
         */
        private static String fourNumberDesc(char[] charArray) {
            StringBuffer sb = new StringBuffer();
            if (charArray.length < 3) {//前置加零
                sb.append(ZERO);
            }
            for (int i = 0; i < charArray.length; i++) {
                final String s = singleNumberDesc(String.valueOf(charArray[i]), charArray.length - i);
                if (sb.length() > 0 && String.valueOf(sb.charAt(sb.length() - 1)).equals(ZERO) && s.equals(ZERO)) {
                    continue;//防止重复加零
                } else {
                    sb.append(s);
                }
            }

            if(String.valueOf(sb.charAt(sb.length() - 1)).equals(ZERO)){
                return sb.substring(0,sb.length() - 1);
            }else{
                return sb.toString();
            }
        }

        public static String singleNumberDesc(String numberStr, int TAG) {
            final String numberDesc = getNumberDesc(numberStr);
            String result = "";
            if (ZERO.equals(numberDesc)) {
                return numberDesc;
            }
            result += numberDesc;
            switch (TAG) {
                case TAG_YI:
                    result += "亿";
                    break;
                case TAG_1000:
                    result += "千";
                    break;
                case TAG_100:
                    result += "百";
                    break;
                case TAG_10:
                    result += "十";
                    break;
                case TAG_1:
                    break;
                default:
                    break;
            }
            return result;
        }

        public static String convertNumberToDesc(String number) {

            return null;
        }

        private  static int getUnit(String s) {
            if (s.equals("亿")) {
                return 100000000;
            }
            if (s.equals("万")) {
                return 10000;
            }
            if (s.equals("千")) {
                return 1000;
            }
            if (s.equals("百")) {
                return 100;
            }
            if (s.equals("十")) {
                return 10;
            }
            return 0;
        }


        private static int getNumber(String s) {
            if (s.equals("一")) {
                return 1;
            }
            if (s.equals("二")) {
                return 2;
            }
            if (s.equals("三")) {
                return 3;
            }
            if (s.equals("四")) {
                return 4;
            }
            if (s.equals("五")) {
                return 5;
            }
            if (s.equals("六")) {
                return 6;
            }
            if (s.equals("七")) {
                return 7;
            }
            if (s.equals("八")) {
                return 8;
            }
            if (s.equals("九")) {
                return 9;
            }

            return 0;// -1

        }

        private static String getNumberDesc(String c) {

            if (c.equals("1")) {
                return "一";
            }
            if (c.equals("2")) {
                return "二";
            }
            if (c.equals("3")) {
                return "三";
            }
            if (c.equals("4")) {
                return "四";
            }
            if (c.equals("5")) {
                return "五";
            }
            if (c.equals("6")) {
                return "六";
            }
            if (c.equals("7")) {
                return "七";
            }
            if (c.equals("8")) {
                return "八";
            }
            if (c.equals("9")) {
                return "九";
            }
            if (c.equals("0")) {
                return "零";
            }
            return "";// -1

        }

        public static int convertDescToNumber(String desc) throws Exception {
            if(gap1==null){
                init();
            }

            BigInteger finalResult = BigInteger.valueOf(0);//最终结果
            int temp = 0; // 一位即得数字
            BigInteger tempResult = BigInteger.valueOf(0);// 暂时结果,在遇到万和亿之前一直的计算总值
            char[] charArray = desc.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                final String str = String.valueOf(charArray[i]);
                if (numbers.contains(str)) {
                    temp = getNumber(str);
                    continue;
                }
                if (gap.contains(str)) {
                    if ("十".equals(str) && temp == 0) {
                        temp += 1;
                    }
                    final int unit = getUnit(str);
                    if (gap1.contains(str)) {
                        // 万亿
                        tempResult = tempResult.add(BigInteger.valueOf(temp));
                        tempResult = tempResult.multiply(BigInteger.valueOf(unit));
                        finalResult = finalResult.add(tempResult);//BigInteger.valueOf(finalResult.intValue()+tempResult);
                        tempResult = BigInteger.valueOf(0);
                    } else {
                        // 千百十
                        tempResult = tempResult.add(BigInteger.valueOf(temp * unit));
                    }
                    temp = 0;
                    continue;
                }
                if (zeros.contains(str)) {
                    continue;
                }
                throw new Exception("非法字符出现于" + i);
            }
		/*finalResult += tempResult;
		finalResult += temp;*/
            //finalResult = BigInteger.valueOf(finalResult.intValue()+tempResult+temp);
            finalResult = finalResult.add(tempResult);
            finalResult = finalResult.add(BigInteger.valueOf(temp));

            //System.out.println("finalResult.longValue()"+finalResult.longValue());
            if (finalResult.doubleValue() > Integer.MAX_VALUE) {
                //数字过大时，当然也有可能
                throw new Exception("数字太大了，超过了整形最大范围！");
            }
            return finalResult.intValue();
        }

}
