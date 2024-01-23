package org.example;

import org.example.generator.NumberGuesser;
import org.example.unlocker.PDFUnlocker;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        File file = new File("/Users/rocket/Desktop/ZIP/Homework 1.pdf");

        NumberGuesser numberGuesser = new NumberGuesser(4);
        PDFUnlocker pdfUnlocker = new PDFUnlocker(numberGuesser);
        pdfUnlocker.unlockPDF(file);



    }
}
