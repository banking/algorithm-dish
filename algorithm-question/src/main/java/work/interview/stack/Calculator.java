package work.interview.stack;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 *
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.
 *
 * Example 1:
 *
 * Input: "3+2*2"
 * Output: 7
 * Example 2:
 *
 * Input: " 3/2 "
 * Output: 1
 * Example 3:
 *
 * Input: " 3+5 / 2 "
 * Output: 5
 * Note:
 *
 * You may assume that the given expression is always valid.
 * Do not use the eval built-in library function.
 */

public class Calculator {

    public int calculate(String s) {
        String input = s.trim();
        Stack<String> stack = new Stack<>();
        boolean numBuildFlag = false;
        for(int i = 0;i<input.length();i++) {
            char c = input.charAt(i);
            if (c == '+') {
                numBuildFlag = false;
                tryCalLastExpression(stack);
                continue;
            }
            if (c == '-') {
                numBuildFlag = false;
                tryCalLastExpression(stack);
                stack.push(c + "");
                continue;
            }
            if (c == '*') {
                numBuildFlag = false;
                tryCalLastExpression(stack);
                stack.push(c + "");
                continue;
            }
            if (c == '/') {
                numBuildFlag = false;
                tryCalLastExpression(stack);
                stack.push(c + "");
                continue;
            }
            if (c == ' ') {
                if (i == input.length() - 1) {
                    tryCalLastExpression(stack);
                }
                continue;
            }
            if (stack.isEmpty()) {
                numBuildFlag = true;
                stack.push(c + "");
                continue;
            }
            //当前字符是数字
            String lastStr = stack.peek();
            switch (lastStr) {
                case "-":
                    stack.pop();
                    numBuildFlag = true;
                    stack.push("-" + c);
                    break;
                case "*":
                    numBuildFlag = true;
                    stack.push( c + "");
                    break;
                case "/":
                    numBuildFlag = true;
                    stack.push(c + "");
                    break;
                default:
                    if (numBuildFlag) {
                        int lastNum = Integer.parseInt(stack.pop());
                        if (lastNum > 0) {
                            lastNum = lastNum * 10 + Integer.parseInt(c + "");
                        } else {
                            lastNum = lastNum * 10 - Integer.parseInt(c + "");
                        }
                        stack.push(lastNum + "");
                    } else {
                        numBuildFlag = true;
                        stack.push(c + "");
                    }

                    break;
            }
            if (i == input.length() - 1) {
                tryCalLastExpression(stack);
            }
        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += Integer.parseInt(stack.pop());
        }
        return result;
    }

    public void tryCalLastExpression(Stack<String> stack) {
        if (stack.isEmpty()) {
            return;
        }
        String preNumStr = stack.pop();
        if (stack.isEmpty()) {
            stack.push(preNumStr);
            return;
        }
        String pre2Str = stack.peek();
        if (pre2Str.equals("*") || pre2Str.equals("/")) {
            stack.pop();
        } else {
            stack.push(preNumStr);
            return;
        }
        if (stack.isEmpty()) {
            //throw some exception
            return;
        }
        String calStr = "";
        String pre3NumStr = stack.pop();
        if (pre2Str.equals("*")) {
            calStr = Integer.parseInt(pre3NumStr) * Integer.parseInt(preNumStr) + "";
        }
        if (pre2Str.equals("/")) {
            calStr = Integer.parseInt(pre3NumStr) / Integer.parseInt(preNumStr) + "";
        }
        stack.push(calStr);

    }

    public int calculate2(String s) {
        String input = s.trim();
        Stack<String> stack = new Stack<>();
        boolean numBuildFlag = false;
        for(int i = 0;i<input.length();i++) {
            char c = input.charAt(i);
            if (c == ' ') {
                continue;
            }
            if (c == '+') {
                numBuildFlag = false;
                continue;
            }
            if (c == '-') {
                numBuildFlag = false;
                stack.push(c + "");
                continue;
            }
            if (c == '*') {
                numBuildFlag = false;
                stack.push(c + "");
                continue;
            }
            if (c == '/') {
                numBuildFlag = false;
                stack.push(c + "");
                continue;
            }
            if (stack.isEmpty()) {
                numBuildFlag = true;
                stack.push(c + "");
                continue;
            }
            //当前字符是数字
            String lastStr = stack.peek();
            if (i == input.length() - 1 || input.charAt(i + 1) == '+' || input.charAt(i + 1) == '-'
                || input.charAt(i + 1) == '*' || input.charAt(i + 1) == '/') {
                //当前是数字，后面没有数字
                switch (lastStr) {
                    case "-" :
                        stack.pop();
                        stack.push("-"+c);
                        numBuildFlag = true;
                        break;
                    case "*" :
                        stack.pop();
                        String preNum1 = stack.pop();
                        String calValue1 = String.valueOf(Integer.parseInt(preNum1) * Integer.parseInt(c + ""));
                        stack.push(calValue1);
                        break;
                    case "/":
                        stack.pop();
                        String preNum2 = stack.pop();
                        String calValue2 = String.valueOf(Integer.parseInt(preNum2) / Integer.parseInt(c + ""));
                        stack.push(calValue2);
                        break;
                    default:
//                        if (i == input.length() - 1) { //结尾了做主动计算
//
//                        }
                        //todo
                        if (numBuildFlag || i == input.length() - 1) {
                            int lastNum = Integer.parseInt(stack.pop());
                            if (lastNum > 0) {
                                lastNum = lastNum * 10 + Integer.parseInt(c + "");
                            } else {
                                lastNum = lastNum * 10 - Integer.parseInt(c + "");
                            }
                            if (!stack.isEmpty()) {
                                String preStr = stack.peek();
                                if (preStr.equals("*") || preStr.equals("/")) {
                                    preStr = stack.pop();
                                    String preNum = stack.pop();
                                    if (preStr.equals('*')) {
                                        String calValue = String.valueOf(Integer.parseInt(preNum) * lastNum);
                                        stack.push(calValue);
                                    }
                                    if (preStr.equals('/')) {
                                        String calValue = String.valueOf(Integer.parseInt(preNum) / lastNum);
                                        stack.push(calValue);
                                    }

                                } else {
                                    stack.push(String.valueOf(lastNum));
                                }
                            } else {
                                stack.push(String.valueOf(lastNum));
                            }

                        } else {
                            stack.push(c + "");
                            //numBuildFlag = true;
                        }

                        break;
                }

            } else {
                //当前是数字，后面还有数字
                switch (lastStr) {
                    case "-":
                        stack.pop();
                        stack.push("-" + c);
                        numBuildFlag = true;
                        break;
                    default:
                        if (numBuildFlag) {
                            int lastNum = Integer.parseInt(stack.pop());
                            if (lastNum > 0) {
                                lastNum = lastNum * 10 + Integer.parseInt(c + "");
                            } else {
                                lastNum = lastNum * 10 - Integer.parseInt(c + "");
                            }
                            stack.push(String.valueOf(lastNum));
                        } else {
                            stack.push(c + "");
                            numBuildFlag = true;
                        }
                        break;
                }
                numBuildFlag = true;
            }


        }
        int result = 0;
        while (!stack.isEmpty()) {
            result += Integer.parseInt(stack.pop());
        }
        return result;
    }

    public static void main(String args[]) {
//        System.out.println(new Calculator().calculate("42"));
//        System.out.println(new Calculator().calculate("0-2147483647"));

//        System.out.println(new Calculator().calculate("1*2-3/4+5*6-7*8+9/10"));
//        System.out.println(new Calculator().calculate("3+2*2"));
        System.out.println(new Calculator().calculate("1+2+3+4+5+6+7+8+9+10"));
//        System.out.println(new Calculator().calculate("583+17871/7*21/52/9"));//+1692/6+112*4+288/2+8/3*67*4+6744/4-9480/7-1*6*3*5*2+5993
    }
}
