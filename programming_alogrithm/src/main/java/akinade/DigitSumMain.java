package akinade;

import static akinade.digits_sum.DigitSum.sumDigits;

public class DigitSumMain {
    public static void main(String[] args) {

        String input = "1234445123444512344451234445123444512344451234445";

        int result = sumDigits(input);
        System.out.println(result);
    }

}
