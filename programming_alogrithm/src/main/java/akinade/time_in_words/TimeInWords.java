package akinade.time_in_words;

public class TimeInWords {

    private static final String[] NUMBERS = {
            "zero", "one", "two", "three", "four", "five", "six", "seven",
            "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
            "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
            "twenty", "twenty one", "twenty two", "twenty three", "twenty four",
            "twenty five", "twenty six", "twenty seven", "twenty eight", "twenty nine"
    };


    public static String timeInWords(int hour, int minutes) {
        // Base case
        if (hour < 1 || hour > 12 || minutes < 0 || minutes > 59) return "Invalid input";

        return switch (minutes) {
            case 0 -> capitalize(NUMBERS[hour] + " o'clock");
            case 15 -> capitalize("quarter past " + NUMBERS[hour]);
            case 30 -> capitalize("half past " + NUMBERS[hour]);
            case 45 -> capitalize("quarter to " + NUMBERS[(hour % 12) + 1]);

            default -> {
                if (minutes < 30) {
                    String word = minutes == 1 ? "minute" : "minutes";
                   yield  capitalize(NUMBERS[minutes] + " " + word + " past " + NUMBERS[hour]);
                }
                    int remaining = 60 - minutes;
                    String word = remaining == 1 ? "minute" : "minutes";
                   yield capitalize (NUMBERS[remaining] + " " + word + " to " + NUMBERS[(hour % 12) + 1]);
            }
        };


    }

        private static String capitalize(String s) {
            return s.substring(0, 1).toUpperCase() + s.substring(1);
        }
}
