package akinade;

import akinade.time_in_words.TimeInWords;

import java.util.Scanner;

public class TimeInWordsMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Hour input: ");
        int H = scanner.nextInt();

        System.out.println();

        System.out.print("Enter Minute input: ");
        int M = scanner.nextInt();

        String timeInWords = TimeInWords.timeInWords(H, M);

        System.out.println();
        System.out.println(timeInWords);

    }

}
