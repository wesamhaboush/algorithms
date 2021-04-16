package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NumberLetterCountsTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(NumberLetterCounts.calculate(1)).isEqualTo(3);
        // length(one) = 3, length(two) = 3, length(three) = 5, length(four) = 4
        // 3 + 3 + 5 + 4 = 15
        assertThat(NumberLetterCounts.calculate(4)).isEqualTo(15);
        // length(one) = 3, length(two) = 3, length(three) = 5, length(four) = 4, length(five) = 4
        // 3 + 3 + 5 + 4 + 4 = 19
        assertThat(NumberLetterCounts.calculate(5)).isEqualTo(19);
        // length(one) = 3,
        // length(two) = 3,
        // length(three) = 5,
        // length(four) = 4,
        // length(five) = 4,
        // length(six) = 3,
        // length(seven) = 5,
        // length(eight) = 5,
        // length(nine) = 4,
        // length(ten) = 3,
        // 3 + 3 + 5 + 4 + 4 + 3 + 5 + 5 + 4 + 3 = 39
        assertThat(NumberLetterCounts.calculate(10)).isEqualTo(39);
        // length(one) = 3,
        // length(two) = 3,
        // length(three) = 5,
        // length(four) = 4,
        // length(five) = 4,
        // length(six) = 3,
        // length(seven) = 5,
        // length(eight) = 5,
        // length(nine) = 4,
        // length(ten) = 3,
        // length(eleven) = 6,
        // 3 + 3 + 5 + 4 + 4 + 3 + 5 + 5 + 4 + 3 + 6 = 45
        assertThat(NumberLetterCounts.calculate(11)).isEqualTo(45);
        // length(one) = 3,
        // length(two) = 3,
        // length(three) = 5,
        // length(four) = 4,
        // length(five) = 4,
        // length(six) = 3,
        // length(seven) = 5,
        // length(eight) = 5,
        // length(nine) = 4,
        // length(ten) = 3,
        // length(eleven) = 6,
        // length(twelve) = 6,
        // 3 + 3 + 5 + 4 + 4 + 3 + 5 + 5 + 4 + 3 + 6 + 6 = 51
        assertThat(NumberLetterCounts.calculate(12)).isEqualTo(51);
        // sum(length(nineteen)...length(one)) - sum(length(eighteen)...length(one)) = length(nineteen) = 8
        assertThat(NumberLetterCounts.calculate(19)-NumberLetterCounts.calculate(18)).isEqualTo(8);
        // sum(length(twenty)...length(one)) - sum(length(nineteen)...length(one)) = length(twenty) = 6
        assertThat(NumberLetterCounts.calculate(20)-NumberLetterCounts.calculate(19)).isEqualTo(6);
        // sum(length(twenty one)...length(one)) - sum(length(twenty)...length(one)) = length(twenty one) = 9
        assertThat(NumberLetterCounts.calculate(21)-NumberLetterCounts.calculate(20)).isEqualTo(9);
        // sum(length(twenty nine)...length(one)) - sum(length(twenty eight)...length(one)) = length(twenty nine) = 10
        assertThat(NumberLetterCounts.calculate(29)-NumberLetterCounts.calculate(28)).isEqualTo(10);
        // sum(length(thirty one)...length(one)) - sum(length(twenty nine)...length(one)) = length(thirty) = 6
        assertThat(NumberLetterCounts.calculate(30)-NumberLetterCounts.calculate(29)).isEqualTo(6);
        // sum(length(thirty two)...length(one)) - sum(length(thirty one)...length(one)) = length(thirty two) = 9
        assertThat(NumberLetterCounts.calculate(32)-NumberLetterCounts.calculate(31)).isEqualTo(9);
        // sum(length(ninety nine)...length(one)) - sum(length(ninety eight)...length(one)) = length(ninety nine) = 10
        assertThat(NumberLetterCounts.calculate(99)-NumberLetterCounts.calculate(98)).isEqualTo(10);
        // sum(length(one hundred)...length(one)) - sum(length(ninety nine)...length(one)) = length(one hundred) = 10
        assertThat(NumberLetterCounts.calculate(100)-NumberLetterCounts.calculate(99)).isEqualTo(10);
        // sum(length(three hundred and fifty six)...length(one)) - sum(length(three hundred and fifty two)...length(one))
        // = length(three hundred and fifty six) + length(three hundred and fifty five)
        // + length(three hundred and fifty four) + length(three hundred and fifty three)= 23 + 24 + 24 + 25 = 96
        assertThat(NumberLetterCounts.calculate(356)-NumberLetterCounts.calculate(352)).isEqualTo(96);
        // sum(length(one thousand)...length(one)) - sum(length(nine hundred ninety)...length(one))
        // = length(one thousand)= 11
        assertThat(NumberLetterCounts.calculate(1000)-NumberLetterCounts.calculate(999)).isEqualTo(11);
        //eulers problem?? sumLetterCountForSequence(1000)?
        assertThat(NumberLetterCounts.calculate(1000)).isEqualTo(21124L);
    }

}
