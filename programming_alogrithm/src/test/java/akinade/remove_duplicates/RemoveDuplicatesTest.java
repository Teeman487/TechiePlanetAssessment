package akinade.remove_duplicates;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RemoveDuplicatesTest {

    @Test
    void testRemoveDuplicates_basicCase() {
        int[][] input = {
                {1, 3, 1, 5, 3, 4, 4, 3, 5},
                {1, 2, 1, 1, 1, 1, 1}
        };

        int[][] output = RemoveDuplicates.removeDuplicates(input);

        int[] expectedRow1 = {1, 3, 0, 5, 0, 4, 0, 0, 0};
        int[] expectedRow2 = {1, 2, 0, 0, 0, 0, 0};

        assertArrayEquals(expectedRow1, output[0], "Row 1 duplicates must be replaced with 0");
        assertArrayEquals(expectedRow2, output[1], "Row 2 duplicates must be replaced with 0");
    }

    @Test
    void testRemoveDuplicates_noDuplicates() {
        int[][] input = {
                {1, 2, 3, 4}
        };

        int[][] output = RemoveDuplicates.removeDuplicates(input);

        int[] expected = {1, 2, 3, 4};
        assertArrayEquals(expected, output[0]);
    }

    @Test
    void testRemoveDuplicates_nullRow() {
        int[][] input = {
                {1, 2, 2},
                null,
                {3, 3, 1}
        };

        int[][] output = RemoveDuplicates.removeDuplicates(input);

        assertNull(output[1], "Null rows must remain null");

        assertArrayEquals(new int[]{1, 2, 0}, output[0]);
        assertArrayEquals(new int[]{3, 0, 1}, output[2]);
    }

    @Test
    void testRemoveDuplicates_nullInput() {
        assertNull(RemoveDuplicates.removeDuplicates(null),
                "Null input must return null");
    }

    @Test
    void testRemoveDuplicates_emptyRows() {
        int[][] input = {
                {},
                {1, 1},
                {}
        };

        int[][] output = RemoveDuplicates.removeDuplicates(input);

        assertArrayEquals(new int[]{}, output[0]);
        assertArrayEquals(new int[]{1, 0}, output[1]);
        assertArrayEquals(new int[]{}, output[2]);
    }
}

