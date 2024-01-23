package org.example.unlocker;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.example.generator.NumberGuesser;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class PDFUnlocker {

    private NumberGuesser numberGuesser;

    public PDFUnlocker(NumberGuesser numberGuesser) {
        this.numberGuesser = numberGuesser;
    }

    public boolean unlockPDF(File file) {
        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());

            List<String> passwordList = numberGuesser.getPasswordList();

            for (String eachPassword : passwordList) {
                if (tryUnlockFile(fileContent, eachPassword)) {
                    System.out.println("Password was found : " + eachPassword);
                    break;
                } else {
                    System.out.println("Password - " + eachPassword + " is incorrect");
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    private boolean tryUnlockFile(byte[] fileContent, String password) {
        try (PDDocument document = Loader.loadPDF(fileContent, password)) {
            if (!document.isEncrypted()) {
                return true;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
