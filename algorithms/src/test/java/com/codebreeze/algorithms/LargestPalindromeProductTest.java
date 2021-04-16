package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Largest palindrome product
 Problem 4
 A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.

 Find the largest palindrome made from the product of two 3-digit numbers.
 */
public class LargestPalindromeProductTest
{
    @Test
    public void examples(){
        //when n = 2, 9009 =
        assertThat(LargestPalindromeProduct.calculate(2)).isEqualTo(9009);
        assertThat(LargestPalindromeProduct.calculate(3)).isEqualTo(906609);
    }

}
