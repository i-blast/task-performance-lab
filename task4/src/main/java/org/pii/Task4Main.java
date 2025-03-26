package org.pii;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Task4Main {

    private static final Logger LOG = Logger.getLogger(Task4Main.class.getName());

    public static void main(String[] args) {

        configureLogger();

        if (args.length != 1) {
            LOG.severe("Need array file path.");
            throw new IllegalArgumentException("Program args error.");
        }
        var arrayFilePath = Path.of(args[0]);

        List<String> lines;
        try {
            lines = Files.readAllLines(arrayFilePath);
        } catch (IOException exc) {
            LOG.fine("Array file reading error.");
            throw new RuntimeException(exc);
        }
        var array = lines.stream()
                .mapToLong(Long::parseLong)
                .toArray();
        LOG.fine("💎 array: " + Arrays.toString(array));

        var pivot = calcPivot(array);
        LOG.fine("💎 pivot: " + pivot);

        System.out.println(countSwaps(array, pivot));
    }

    public static long calcPivot(long[] array) {
        return Arrays.stream(array).sum() / array.length;
    }

    public static long countSwaps(long[] array, long pivot) {

        var counter = 0L;
        var arrayIndex = 0;
        while (arrayIndex < array.length) {
            while (array[arrayIndex] != pivot) {
                if (array[arrayIndex] < pivot) {
                    array[arrayIndex]++;
                    counter++;
                } else if (array[arrayIndex] > pivot) {
                    array[arrayIndex]--;
                    counter++;
                }
            }
            arrayIndex++;
        }
        return counter;
    }

    private static void configureLogger() {
        var handler = new ConsoleHandler();
//        handler.setLevel(Level.INFO);
        handler.setLevel(Level.FINE);
        LOG.addHandler(handler);
        LOG.setLevel(Level.FINE);
        LOG.setUseParentHandlers(false);
    }
}
