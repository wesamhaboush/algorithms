package com.codebreeze.algorithms;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class NameScores
{
    private static final String[] NAMES = readNames();

    public static long calculate()
    {
//        Arrays.stream(NAMES).forEach(System.out::println);
        return IntStream
                .rangeClosed(1, NAMES.length)
                .mapToLong(alphabeticalPosition ->
                        alphabeticalPosition * alphabeticalValue(NAMES[alphabeticalPosition - 1]))
                .sum();
    }

    private static long alphabeticalValue(final String s)
    {
        return IntStream.rangeClosed(1, s.length())
                        .mapToLong(i -> s.charAt(i - 1) - 'A' + 1)
                        .sum();
    }

    private static String[] readNames()
    {
        final String s = toString(NameScores.class.getResourceAsStream("/p022_names.txt"));
        return Arrays.stream(s.split(","))
                     .map(name -> name.replaceAll("\"", ""))
                     .sorted()
                     .toArray(String[]::new);
    }

    private static String toString(java.io.InputStream is) {
        if(is == null) return "";
        final Scanner s = new Scanner(is, "UTF-8").useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
