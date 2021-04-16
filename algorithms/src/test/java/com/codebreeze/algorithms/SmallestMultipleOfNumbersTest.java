package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Smallest multiple
 Problem 5
 Published on Friday, 30th November 2001, 06:00 pm; Solved by 303654; Difficulty rating: 5%
 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.

 What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
public class SmallestMultipleOfNumbersTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(SmallestMultipleOfNumbers.calculate(10)).isEqualTo(2520);
        assertThat(SmallestMultipleOfNumbers.calculate(20)).isEqualTo(232792560L);
    }

}
