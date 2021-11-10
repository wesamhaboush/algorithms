package com.codebreeze.algorithms.bits;

import java.util.LinkedList;
import java.util.List;

public class Bits {
    public static int bit(int number, int bitNumber) {
        return (number >> bitNumber) & 1;
    }

    public static int set(int number, int bitNumber) {
        return number | (1 << bitNumber);
    }

    public static byte set(byte number, byte bitNumber) {
        return (byte)(number | (1 << bitNumber));
    }

    public static int unset(int number, int bitNumber) {
        return number & ~(1 << bitNumber);
    }

    public static int store(int number, int bitNumber, int valueToStore) {
        return (number & ~(1 << bitNumber)) | (valueToStore << bitNumber);
    }

    public static int toggle(int number, int bitNumber) {
        return number ^ (1 << bitNumber);
    }

    public static int leastSignificantBit(int number) {
        return number & -number;
    }

    public static int mostSignificantBit(int number) {
        number |= number >> 1;
        number |= number >> 2;
        number |= number >> 4;
        number |= number >> 8;
        number |= number >> 16;
        return (number + 1) >> 1;
    }

    public static int changeEndianness(int number) {
        return ((number >> 24) & 0xFF) |  // Move byte 3 to byte 0
            ((number << 8) & 0xFF0000) |  // Move byte 1 to byte 2
            ((number >> 8) & 0xFF00) |  // Move byte 2 to byte 1
            ((number << 24) & 0xFF000000); // Move byte 0 to byte 3
    }

    public static int multiplyBy2Power(int number, int k) {
        return number << k;
    }

    public static int divideBy2Power(int number, int k) {
        return number >> k;
    }

    public static int moduloBy2Power(int number, int k) {
        return number & ((1 << k) - 1);
    }

    public static boolean isPowerOf2(int number) {
        return (number != 0) && (number & (number - 1)) == 0;
    }

    public static boolean isEven(int number) {
        return (number & 1) == 0;
    }

    public static boolean isOfOppositeSigns(int x, int y) {
        return (x ^ y) < 0;
    }

    public static int min(int x, int y) {
        return y ^ ((x ^ y) & -(x < y ? 1 : 0));
    }

    public static int max(int x, int y) {
        return x ^ ((x ^ y) & -(x < y ? 1 : 0));
    }

    public static boolean isSet(int number, int bitNumber) {
        return ((number & (1 << bitNumber)) >> bitNumber ) == 1;
    }

    public static int turnOffRightMost1Bit(int number) {
        return number & (number - 1);
    }

    public static int isolateRightMost1Bit(int number) {
        return number & (-number);
    }

    public static int rightPropagateRightMost1Bit(int number) {
        return number | (number - 1);
    }

    public static int turnOnRightMost0Bit(int number) {
        return number | (number + 1);
    }

    public static int isolateRightMost0Bit(int number) {
        return ~number & (number + 1);
    }

    public static List<Integer> generateVariations(int from, int to) {
        int max = (1 << (to - from + 1)) - 1;
        List<Integer> values = new LinkedList<>();
        for(int i = 0; i <= max; i++) {
            values.add(i << from);
        }
        return values;
    }

    private static boolean hasSameBitsInRange(int num1, int num2, int from, int to) {
        for(int i = from; i <= to; i++) {
            if(bit(num1, i) != bit(num2, i)) {
                return false;
            }
        }
        return true;
    }
}
