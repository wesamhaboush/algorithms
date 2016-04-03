package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9.
 * The sum of these multiples is 23. Find the sum of all the multiples of 3 or 5 below 1000.
 */
public class SumOfMultiplesOf3And5BelowNumberTest
{
    @Test
    public void multiples_of_3_and_5()
    {
        assertThat(SumOfMultiplesOf3And5BelowNumber.calculate(10)).isEqualTo(23);
        assertThat(SumOfMultiplesOf3And5BelowNumber.calculate(1000)).isEqualTo(233168);
    }
}
