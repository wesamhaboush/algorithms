package com.codebreeze.algorithms;

/**
 * Follow this process until you run out of bits in the numerator:

 1)  Chop the leftmost bit of the numerator off and stick it to the right of a.
 2)  Extend b with a 0 (on the right).
 3)  If a is greater than or equal to the denominator, subtract the denominator from it and increment b.

 After you're done with that loop, b holds your answer.

 */
public class DivisionWithoutMultiplyAndDivideOperators
{
    //assumes positive numbers and no divide by zero
    public static int divide(int num, int denom)
    {
        int a = 0, b = 0;
        int i = 31; // CAREFUL: works only on int=32-bit machine!
        /* Work from leftmost to rightmost bit in numerator */
        while (i >= 0)
        {
            /* appends one bit from numerator to a */
            a = (a << 1) + ((num & (1 << i)) >> i); // the one is being shifted into num, then out again to the first bit
            b = b << 1;
            //printf("After shifting a=%d and b=%d\n",a,b);
            if (a >= denom)
            {
                a -= denom;
                b++;
            }
            //printf("After subtraction a=%d and b=%d\n",a,b);
            i--;
        }
        return b;
    }

}
