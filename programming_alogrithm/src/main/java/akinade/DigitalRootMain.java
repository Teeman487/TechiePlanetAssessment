package akinade;
import java.util.Scanner;

import static akinade.digits_sum.DigitalRoot.digitalRoot;
import static akinade.digits_sum.DigitalRoot.sumDigits;

public class DigitalRootMain {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.print("Input digits (e.g: 1234445): ");
        String input = sc.nextLine(); //

        System.out.println();

        int firstSum = sumDigits(input);
        System.out.println("First sum: = " + firstSum);
        int result = digitalRoot(input);
        System.out.println("Digital root = " + result);}

}
