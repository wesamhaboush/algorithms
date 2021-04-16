package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Sum square difference
 Problem 6
 The sum of the squares of the first ten natural numbers is,

 12 + 22 + ... + 102 = 385
 The square of the sum of the first ten natural numbers is,

 (1 + 2 + ... + 10)2 = 552 = 3025
 Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.

 Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
public class SumSquareDifferenceTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(SumSquareDifference.calculate(10)).isEqualTo(2640);
        assertThat(SumSquareDifference.calculate(100)).isEqualTo(25164150);
    }

}
