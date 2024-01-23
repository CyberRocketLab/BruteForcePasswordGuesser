package org.example;

import org.example.generator.NumberGuesser;
import org.example.unlocker.PDFUnlocker;

import java.io.File;
import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    private static final int MAX_DIGITS = 5;

    public static void main(String[] args) {
        System.out.println("Welcome to Cracking PDF script!");
        System.out.println("The Script will try to guess the password up to 5 digits");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the path to the PDF file:");
        String filePath = scanner.nextLine();
        File file = new File(filePath);


        for (int digit = 1; digit <= MAX_DIGITS; ++digit) {
            System.out.println("Trying password with " + digit + " digits!");
            NumberGuesser numberGuesser = new NumberGuesser(digit);
            PDFUnlocker pdfUnlocker = new PDFUnlocker(numberGuesser);

            if (pdfUnlocker.unlockPDF(file)) {
                System.out.println("Thank you!");
                break;
            }
        }

    }
}
