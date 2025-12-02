package akinade.digits_sum;

public class DigitSum {

    public static int sumDigits(String string) {
        if (string == null || string.isEmpty()) return 0;

        // Convert first character to digit
        //int firstDigit = string.charAt(0) - '0';//  '0'–'9'
        int firstDigit = Character.getNumericValue(string.charAt(0));

        return firstDigit + sumDigits(string.substring(1));

    }
}