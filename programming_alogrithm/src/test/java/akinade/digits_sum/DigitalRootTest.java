package akinade.digits_sum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class DigitalRootTest {

    @ParameterizedTest(name = "digitalRoot({0}) should be {1}")
    @CsvSource({
            "1234445, 5",
            "1234, 1",
            "9, 9",
            "0, 0",
            "99, 9",
            "9999, 9",
            "100000, 1",
            "'00045', 9"
    })
    void testDigitalRootBasic(String input, int expected) {
        assertEquals(expected, DigitalRoot.digitalRoot(input));
    }

    @Test
    void testSumDigitsSameAsDigitSumClass() {
        assertEquals(161, DigitalRoot.sumDigits("1234445123444512344451234445123444512344451234445"));
    }

    @Test
    void testDigitalRootNullShouldBeZero() {
        assertEquals(0, DigitalRoot.digitalRoot(null),
                "Null input must produce digital root 0");
    }

    @Test
    void testDigitalRootEmptyStringShouldBeZero() {
        assertEquals(0, DigitalRoot.digitalRoot(""),
                "Empty string must produce digital root 0");
    }

    @Test
    void testDigitalRootCrossCheckWithMathFormula() {
        // digital root formula: 1 + ((n - 1) % 9)
        String input = "987654321123456789";
        int sum = DigitalRoot.sumDigits(input);

        int expected = (sum == 0) ? 0 : 1 + ((sum - 1) % 9);

        assertEquals(expected, DigitalRoot.digitalRoot(input),
                "Digital root must match mathematical formula");
    }
}

