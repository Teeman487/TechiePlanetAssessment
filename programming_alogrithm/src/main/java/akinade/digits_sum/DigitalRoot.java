package akinade.digits_sum;

public class DigitalRoot {
    public static int sumDigits(String s) {
        if (s == null || s.isEmpty()) return 0;

        int first = s.charAt(0) - '0';  //
        return first + sumDigits(s.substring(1));
    }


    public static int digitalRoot(String s) {
        int sum = sumDigits(s);

        // Base case: single digit  // 23
        if (sum < 10) {
            System.out.println("Next sum: " + sum);
            return sum;
        }

        return digitalRoot(String.valueOf(sum));
    }

}
