package com.codebreeze.algorithms;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/*
Number letter counts
Problem 17
If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there
are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.

If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?


NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two)
contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
The use of "and" when writing out numbers is in compliance with British usage.
 */
public class NumberLetterCounts
{
    private static final Map<Integer, Integer> NUMBER_TO_CHAR_COUNT = new HashMap<Integer, Integer>()
    {
        {
           put(1, "one".length());
           put(2, "two".length());
           put(3, "three".length());
           put(4, "four".length());
           put(5, "five".length());
           put(6, "six".length());
           put(7, "seven".length());
           put(8, "eight".length());
           put(9, "nine".length());
           put(10, "ten".length());
           put(11, "eleven".length());
           put(12, "twelve".length());
           put(13, "thirteen".length());
           put(14, "fourteen".length());
           put(15, "fifteen".length());
           put(16, "sixteen".length());
           put(17, "seventeen".length());
           put(18, "eighteen".length());
           put(19, "nineteen".length());
           put(20, "twenty".length());
           put(30, "thirty".length());
           put(40, "forty".length());
           put(50, "fifty".length());
           put(60, "sixty".length());
           put(70, "seventy".length());
           put(80, "eighty".length());
           put(90, "ninety".length());
           put(100, "hundred".length());
           put(1000, "thousand".length());
        }
    };
    public static long calculate(final int n)
    {
        if(n > 1000)
        {
            throw new RuntimeException("cannot work on numbers greater than 1000");
        }
        return IntStream
                .rangeClosed(1, n)
                .mapToLong(i -> extractCharacterCount(i))
                .sum();
    }

    private static long extractCharacterCount(int i)
    {
        //first handle
        // first 20 numbers, and
        // all 10 multiples (10 , 20, 30, 40, etc), and also
        // 100 itself (which is 'one hundred' not only 'hundred')
        if( i >=1 && i <= 20 || ( i < 100 && i % 10 == 0))
        {
            return NUMBER_TO_CHAR_COUNT.get(i);
        }
        else if ( i < 100)
        {
            // break it into the 10s and the 1s (twenty two, fourty one, etc)
            return extractCharacterCount( i % 10 ) + extractCharacterCount( (i / 10) * 10);
        }
        else
        {
            final int nThousands = i / 1000;
            final long charCountForThousandsPart = nThousands > 0
                                                   ? extractCharacterCount(nThousands) + NUMBER_TO_CHAR_COUNT.get(1000)
                                                   : 0;
            final int threeDigitNumber = i % 1000;
            //break 3 digit numbers into 2 digit numbers and 'and' and a digit for how many hundreds, and the hundred
            //eg.: 345 is (three) + (hundred) + (and) + (fourty five)
            final int nHundreds = threeDigitNumber / 100;
            final long charCountForHundredPart = nHundreds > 0
                                ? extractCharacterCount(nHundreds) + NUMBER_TO_CHAR_COUNT.get(100)
                                : 0;
            final int twoDigitNumber = threeDigitNumber % 100;
            final long charCountForTwoDigitNumber = twoDigitNumber > 0
                                                    ? 3 + extractCharacterCount(twoDigitNumber) //3 is for 'and' length
                                                    : 0;
            return charCountForThousandsPart + charCountForHundredPart + charCountForTwoDigitNumber;
        }
    }
}
