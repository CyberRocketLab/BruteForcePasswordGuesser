package org.example.generator;

import java.util.ArrayList;
import java.util.List;

public class NumberGuesser {

    private final int digitSize;
    private boolean stopGeneration = false;

    public NumberGuesser(int digitSize) {
        this.digitSize = digitSize;
    }

    public void generateAndUsePassword(PasswordConsumer consumer) {
        String emptyStartDigit = "";
        generatePasswords(emptyStartDigit, digitSize, consumer);
    }

    private void generatePasswords(String current, int size, PasswordConsumer consumer) {
        if (stopGeneration) {
            return; // Stop generating further passwords
        }

        if (size == 0) {
            consumer.consume(current);
            return;
        }

        for (int i = 0; i < 10; i++) {
            generatePasswords(current + i, size - 1, consumer);
        }
    }

    public void setStopGeneration(boolean stopGeneration) {
        this.stopGeneration = stopGeneration;
    }

    @FunctionalInterface
    public interface PasswordConsumer {
        void consume(String password);
    }


}
