package akinade;

import static akinade.remove_duplicates.RemoveDuplicates.removeDuplicates;

public class RemoveDuplicatesMain {
    public static void main(String[] args) {
        int[][] input = {
                {1, 3, 1, 5, 3, 4, 4, 3, 5},
                {1, 2, 1, 1, 1, 1, 1}
        };

        int[][] output = removeDuplicates(input);


        for (int[] row : output) {
            System.out.print("{ ");
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println("}");
        }

    }

}
