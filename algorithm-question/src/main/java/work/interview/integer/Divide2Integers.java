package work.interview.integer;

public class Divide2Integers {
    public int divide(int dividend, int divisor) {
        int operation = 1;
        if (dividend > 0 ^ divisor > 0) {
            operation = -1;
        }
        long dividendL = Math.abs((long) dividend);
        long divisorL = Math.abs((long) divisor);

        long result = doDivide(dividendL, divisorL);
        if (result > Integer.MAX_VALUE){ //Handle overflow.
            result = (operation > 0)? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            if (operation < 0) {
                result = -result;
            }
        }

        return (int)result;

    }

    public long doDivide(long dividend, long divisor) {

        long result = 0;
        long factor = 1;
        long pDivisor = divisor;
        if (dividend < divisor) {
            return 0;
        }
        while (pDivisor + pDivisor <= dividend) {
            factor = factor + factor;
            pDivisor = pDivisor + pDivisor;
        }
        result = factor + doDivide(dividend - pDivisor, divisor);
        return result;
    }

    public static void main(String args[]) {

        System.out.println(new Divide2Integers().divide(-2147483648, -1));
    }
}
