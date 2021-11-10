package com.codebreeze.algorithms.pearls;

import java.util.function.IntPredicate;

/*
Prove that this program terminates when its input x is a positive integer.

while x != 1 do
    if even(x)
        x = x/2
    else
        x = 3*x+1
 */
public class PositiveTerminatingEquation implements IntPredicate {

    private static final int MAX_TRY = 10_000;

    @Override
    public boolean test(int x) {
        int value = x;
        int count = 0;
        while (value != 1 && count++ < MAX_TRY) {
            if (value % 2 == 0) {
                value = value / 2;
            } else {
                value = 3 * value + 1;
            }
        }
//        System.out.printf("starting with x = %d, got to x = %d after %d iterations.%n", x, value, count);
        return count < MAX_TRY;
    }
}
