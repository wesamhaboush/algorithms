package com.codebreeze.algorithms;

import com.codebreeze.algorithms.bits.Bits;

import java.util.function.IntFunction;

/*
[S. C. Johnson] Seven-segment devices provide an inexpensive display of the ten decimal digits:
0, 1, 2, 3, 4, 5, 6, 7, 8, 9. The seven segments are usually numbered as:

Write a program that displays a 16-bit positive integer in five seven-segment digits. the ouput is an array of
five bytes; bit i of byte j is one if and only if the i-th segment of digit j should be on.

  2
3   4
  1
5   6
  0

0 = 0 2 3 4 5 6
1 = 4 6
2 = 0 1 2 4 5
3 = 0 1 2 4 6
4 = 1 3 4 6
5 = 0 1 2 3 6
6 = 0 1 2 3 5 6
7 = 2 4 6
8 = 0 1 2 3 4 5 6
9 = 1 2 3 4 6
 */
public class SevenDigitSegments implements IntFunction<byte[]> {
    private static final byte[][] INT_TO_SEGMENTS_ON = {
        {0, 2, 3, 4, 5, 6},
        {4, 6},
        {0, 1, 2, 4, 5},
        {0, 1, 2, 4, 6},
        {1, 3, 4, 6},
        {0, 1, 2, 3, 6},
        {0, 1, 2, 3, 5, 6},
        {2, 4, 6},
        {0, 1, 2, 3, 4, 5, 6},
        {1, 2, 3, 4, 6}
    };
    private static final byte SEGMENTS_COUNT = 5;

    @Override
    public byte[] apply(int input) {
        if (input > 99999) {
            throw new IllegalArgumentException("cannot operate on numbers not between 0 and 99999 inclusive");
        }
        byte[] bytes = new byte[SEGMENTS_COUNT];
        for (int i = 0; i < SEGMENTS_COUNT; i++) {
            int poppedValue = input % 10;
            input = input / 10;
            for (byte segment : INT_TO_SEGMENTS_ON[poppedValue]) {
                bytes[i] = Bits.set(bytes[i], segment);
            }
        }
        return bytes;
    }
}
