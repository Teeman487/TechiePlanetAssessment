package akinade.remove_duplicates;

public class RemoveDuplicates {
    public static int[][] removeDuplicates(int[][] arr) {
        if (arr == null) return null;

        for (int i = 0; i < arr.length; i++) {
            int[] row = arr[i];
            if (row == null) continue;

            // Custom expandable "seen" array
            int max = findMaxValue(row);
            int[] seen = new int[max + 1];   // 0 = unseen, 1 = seen

            for (int j = 0; j < row.length; j++) {
                int value = row[j];

                // Ensure "seen" is large enough for this value
                if (value >= seen.length) {
                    seen = expand(seen, value + 1);
                }

                if (seen[value] == 0) {
                    seen[value] = 1; // mark as seen
                } else {
                    row[j] = 0; // duplicate found
                }
            }
        }
        return arr;
    }

    // Find maximum value in a row to size initial seen[] array
    private static int findMaxValue(int[] row) {
        int max = 0;
        for (int val : row) {
            if (val > max) max = val;
        }
        return max;
    }

    // Custom array expansion (no built-in copy methods)
    private static int[] expand(int[] oldArr, int newSize) {
        int[] newArr = new int[newSize];
        for(int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }
        return newArr;
    }


}
