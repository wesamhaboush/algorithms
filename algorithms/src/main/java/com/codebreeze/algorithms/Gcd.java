package com.codebreeze.algorithms;

import java.util.function.IntBinaryOperator;

public class Gcd implements IntBinaryOperator {

    public static final Gcd FUNCTION = new Gcd();

    /*
    This is based on the following ideas:
    - gcd(a, b) = gcd(b, a), see the swap in the else below
    - gcd(a, 0) = a, and gcd(0, b) = b, see why we keep going until i = 0, then return j.
    - if we have a = b . q + r, then gcd(a, b) = gcd(b, r), this is why we keep subtracting b's from a.
     */
    @Override
    public int applyAsInt(int a, int b) {
        while (a != 0) {
            if (b >= a) {
                b -= a;
            } else {
                int t = a;
                a = b;
                b = t;
            }
        }
        return b;
    }
}
