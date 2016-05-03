package com.codebreeze.algorithms;

/*
1000-digit Fibonacci number
Problem 25
The Fibonacci sequence is defined by the recurrence relation:

Fn = Fn−1 + Fn−2, where F1 = 1 and F2 = 1.
Hence the first 12 terms will be:

F1 = 1
F2 = 1
F3 = 2
F4 = 3
F5 = 5
F6 = 8
F7 = 13
F8 = 21
F9 = 34
F10 = 55
F11 = 89
F12 = 144
The 12th term, F12, is the first term to contain three digits.

What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 */
public class ThousandDigitFibonacciNumber
{
    /*
    Binet formula
    F(n) = [ φn - (-1/φ)n ]/sqrt(5)
    D(p) = ⌊log10(p) + 1⌋
    D(F(n)) =
⌊log10([ φn - (-1/φ)n ]/sqrt(5)) + 1⌋
≈ ⌊n⋅log10(φ) - log10(5)/2 + 1⌋

The Binet Formula

Let φ be the golden section equal to [1+sqrt(5)]/2. Then the explicit formula for F(n) is

F(n) = [ φn - (-1/φ)n ]/sqrt(5)

This is classically known as the Binet formula, named after the French mathematician Jacques Binet. The formula was actually discovered much earlier by Euler and de Moivre. For the Lucas numbers, the explicit formula for L(n) is very similar:

L(n) = φn + (-1/φ)n

Length of a Fibonacci Number

In base-10, the number of digits in an integer p is given by the formula

D(p) = ⌊log10(p) + 1⌋

or equivalently

D(p) = ⌊ln(p)/ln(10) + 1⌋


Let D(F(n)) be the length the nth Fibonacci number and D(L(n)) be the length of the nth Lucas Number. Then we have

D(F(n)) =
⌊log10([ φn - (-1/φ)n ]/sqrt(5)) + 1⌋
≈ ⌊n⋅log10(φ) - log10(5)/2 + 1⌋

D(L(n)) =
⌊log10(φn + (-1/φ)n) + 1⌋
≈ ⌊n⋅log10(φ) + 1⌋
     */
    public static int calculate(final int n)
    {
        final double phi =  (1+Math.sqrt(5.0))/2.0;
        int i = 1;
        int length = (int)(Math.floor(i * Math.log10(phi) - Math.log10(5.0) / 2.0 + 1));
        while(length < n)
        {
            length = (int)(Math.floor(++i * Math.log10(phi) - Math.log10(5.0) / 2.0 + 1));
        }
        return i;
    }
}
