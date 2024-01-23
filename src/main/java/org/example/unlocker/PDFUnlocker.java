package org.example.unlocker;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.example.generator.NumberGuesser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class PDFUnlocker {

    private final NumberGuesser numberGuesser;

    public PDFUnlocker(NumberGuesser numberGuesser) {
        this.numberGuesser = numberGuesser;
    }

    public void unlockPDF(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());

            numberGuesser.generateAndUsePassword(eachPassword -> {
                if (tryUnlockFile(fileContent, eachPassword)) {
                    System.out.println("\nPassword was found : " + eachPassword);
                    numberGuesser.setStopGeneration(true);
                }
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean tryUnlockFile(byte[] fileContent, String password) {
        try (PDDocument document = Loader.loadPDF(fileContent, password)) {
            // password was found because it otherwise it will throw an Exception
            return true;
        } catch (IOException e) {
            System.out.println(e.getMessage() + " - {" + password + "}");
        }
        return false;
    }
}
