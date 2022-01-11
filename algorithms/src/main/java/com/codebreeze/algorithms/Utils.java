package com.codebreeze.algorithms;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

public class Utils {
    public static File getFile(String fileName) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

    public static int[] arrayOf(int...ints) {
        return ints;
    }

    public static long nanosToRun(Runnable runnable) {
        long start = System.nanoTime();
        runnable.run();
        long end = System.nanoTime();
        return end - start;
    }

    public static int[] copy(int[] elements) {
        return Arrays.copyOf(elements, elements.length);
    }

    public static <T> T[] copy(T[] elements) {
        return Arrays.copyOf(elements, elements.length);
    }

    public static String[] sorted(String[] unsorted) {
        Arrays.sort(unsorted);
        return unsorted;
    }

    public static void retry(int times, Set<Class<? extends Throwable>> errors, Runnable runnable) {
        Objects.requireNonNull(errors, "cannot operate with null set of exceptions.");
        Objects.requireNonNull(runnable, "cannot operate with null runnable.");
        if(times < 1) {
            throw new IllegalArgumentException("why retry less than once!!");
        }
        if(errors.isEmpty()) {
            throw new IllegalArgumentException("cannot match against empty set of errors!");
        }

        boolean succeeded = false;
        int i = 0;
        while (i < times && !succeeded) {
            try {
                i++;
                runnable.run();
                succeeded = true;
            } catch (Throwable thrown) {
                if(errors.stream().noneMatch(th -> th == thrown.getClass()) || i > times) {
                    throw thrown;
                }
            }
        }
        if(!succeeded) {
            runnable.run();
        }
    }

    public static boolean isEquals(int[] thisOne, int aFromIndex, int aToIndex, int[] thatOne, int bFromIndex, int bToIndex, boolean failForNegativeToValues) {
        if(failForNegativeToValues) {
            return Arrays.equals(thisOne, aFromIndex, aToIndex, thatOne, bFromIndex, bToIndex);
        } else {
            if(aToIndex < 0 && bToIndex < 0) {
                return true;
            } else if (aToIndex < 0 || bToIndex < 0) {
                return false;
            } else {
                return Arrays.equals(thisOne, aFromIndex, aToIndex, thatOne, bFromIndex, bToIndex);
            }
        }
    }
}
