package akinade.remove_duplicates;

public class RemoveDuplicates {
    public static int[][] removeDuplicates(int[][] arr) {
        if (arr == null) {
            System.out.println("Error: Input array is null");
            return new int[0][0];
        }

        for (int[] row : arr) {
            if (row == null) continue;

            // Custom expandable "seen" array
            int max = findMaxValue(row);  // 5
            int[] seen = new int[max + 1];   // 0 = unseen, 1 = seen

            for (int j = 0; j < row.length; j++) {
                int value = row[j];

                // "seen" is large enough for this value
                if (value >= seen.length) seen = expand(seen, value + 1);

                if (seen[value] == 0) seen[value] = 1;
                  else
                    row[j] = 0; // duplicate found
            }
        }
        return arr;
    }

    // To size initial seen[] array
    private static int findMaxValue(int[] row) {
        int max = 0;
        for (int val : row) {
            if (val > max) max = val;
        }
        return max;
    }

    private static int[] expand(int[] oldArr, int newSize) {
        int[] newArr = new int[newSize];
        for(int i = 0; i < oldArr.length; i++) {
            newArr[i] = oldArr[i];
        }
        return newArr;
    }


}
