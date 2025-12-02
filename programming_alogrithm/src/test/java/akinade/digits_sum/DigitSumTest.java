package akinade.digits_sum;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

class DigitSumTest {

    @ParameterizedTest(name = "sumDigits({0}) should return {1}")
    @CsvSource({
            "1234, 10",
            "0, 0",
            "9, 9",
            "11111, 5",
            "99999, 45",
            "'', 0",
            "'000123', 6"
    })
    void testSumDigitsParameterized(String input, int expected) {
        assertEquals(expected, DigitSum.sumDigits(input));
    }

    @Test
    void testSumDigitsNullInput() {
        assertEquals(0, DigitSum.sumDigits(null), "Null input must return 0");
    }

    @Test
    void testSumDigitsLongString() {
        String input = "1234445123444512344451234445123444512344451234445";
        int expectedSum = 161;

        assertEquals(expectedSum, DigitSum.sumDigits(input));
    }
}

