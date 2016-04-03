package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LargestPrimeFactorTest
{
    @Test
    public void examples(){
        //1, 2, 3, 5, 8 (sum evens) = 2 + 8 = 10
        assertThat(LargestPrimeFactor.calculate(10)).isEqualTo(5);
        assertThat(LargestPrimeFactor.calculate(13195)).isEqualTo(29);
        assertThat(LargestPrimeFactor.calculate(600851475143L)).isEqualTo(6857L);
    }
}
