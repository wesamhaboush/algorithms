package com.codebreeze.algorithms;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

import static com.codebreeze.algorithms.Comparables.lessThan;
import static java.util.stream.Collectors.toList;

public class BigIntegers {
    private static final List<Length> lengths = IntStream.range(1, 2000) // algorithm fails if numbers have more than 2000 digits
            .boxed()
            .map(Length::new)
            .collect(toList());

    public static int digitCount(BigInteger number) {
        return lengths.stream()
                .filter(length -> length.isLengthOf(number.abs()))
                .findFirst()
                .map(Length::value)
                .orElseThrow();
    }

    private static class Length {
        private final BigInteger tenPowerLengthMax;
        private final int value;

        private Length(int value) {
            this.value = value;
            this.tenPowerLengthMax = BigInteger.valueOf(10).pow(value);
        }

        public boolean isLengthOf(BigInteger number) {
            // all numbers less than 10  (10 ^ 1 ) have length = 1
            // all numbers less than 100 (10 ^ 2) have length = 2
            // etc
            return lessThan(number, tenPowerLengthMax);
        }

        public int value() {
            return value;
        }
    }

    public static BigInteger bi(int n) {
        return BigInteger.valueOf(n);
    }
}
