package org.example.generator;

import java.util.ArrayList;
import java.util.List;

public class NumberGuesser {

    private final int digitSize;

    public NumberGuesser(int digitSize) {
        this.digitSize = digitSize;
    }

    public List<String> getPasswordList() {
        List<String> passwordList = new ArrayList<>();
        String emptyStartDigit = "";
        generatePasswords(emptyStartDigit, digitSize, passwordList);

        return passwordList;
    }

    private void generatePasswords(String current, int size, List<String> list) {
        if (size == 0) {
            list.add(current);
            return;
        }

        for (int i = 0; i < 10; i++) {
            generatePasswords(current + i, size - 1, list);
        }
    }


}
