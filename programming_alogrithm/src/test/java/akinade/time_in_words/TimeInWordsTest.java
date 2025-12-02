package akinade.time_in_words;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeInWordsTest {

    @Test
    public void testExactHour() {
        assertEquals("Five o'clock", TimeInWords.timeInWords(5, 0));
    }

    @Test
    public void testOneMinutePast() {
        assertEquals("One minute past five", TimeInWords.timeInWords(5, 1));
    }

    @Test
    public void testQuarterPast() {
        assertEquals("Quarter past seven", TimeInWords.timeInWords(7, 15));
    }

    @Test
    public void testHalfPast() {
        assertEquals("Half past three", TimeInWords.timeInWords(3, 30));
    }

    @Test
    public void testQuarterTo() {
        assertEquals("Quarter to nine", TimeInWords.timeInWords(8, 45));
    }

    @Test
    public void testMinutesToEdgeCase() {
        assertEquals("One minute to twelve", TimeInWords.timeInWords(11, 59));
    }

    @Test
    public void testMinutesPastEdgeCase() {
        assertEquals("Twenty nine minutes past six", TimeInWords.timeInWords(6, 29));
    }

    @Test
    public void testInvalidHour() {
        assertEquals("Invalid input", TimeInWords.timeInWords(13, 10));
    }

    @Test
    public void testInvalidMinute() {
        assertEquals("Invalid input", TimeInWords.timeInWords(10, 60));
    }

    @Test
    public void testZeroMinute() {
        assertEquals("Ten o'clock", TimeInWords.timeInWords(10, 0));


    }

}
