package com.codebreeze.algorithms;

import java.util.function.IntBinaryOperator;

public class EuclideanGcd implements IntBinaryOperator {

    @Override
    public int applyAsInt(int a, int b) {
        if(a == 0) {
            // if a == 0; then gcd(a, b) = b, since the gcd(0, b) = b
            return b;
        } else if (b == 0) {
            // if b == 0, then gcd(a, b) = a, since the gcd(a, 0) = a
            return a;
        } else {
            // Write a in quotient remainder from a = b . q + r
            // find gcd(b, r) using the same Euclidean Algorithm since gcd(a, b) = gcd(b, r)
            // Use long division to find that A/B remainder, then find gcd (b, remainder)
            int remainder = a % b;
            return applyAsInt(b, remainder);
        }
    }
}
