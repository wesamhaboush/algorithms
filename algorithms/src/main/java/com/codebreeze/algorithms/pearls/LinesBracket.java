package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.pair.DoubleDoublePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.NoSuchElementException;
import java.util.function.Function;

/*
A colleague faced the following problem in a program to draw lines on a bit-mapped display.
An array of n pairs of reals (a_i, b_i) defined the n lines y_i = a_i*x + b_i.
The lines were ordered in the x-interval [0, 1] in the sense that y_i < y_{i+1} for all values of
*i* between 0 and n-2 and all values of *x* in [0,1].
Less formally, the lines don't touch in the vertical slab.
Given a point (x,y), where 0<=x<=1, he wanted to determine the two lines that bracket the point.
How could he solve the problem quickly?

basically this is a binary search between the lines
 */
public class LinesBracket implements Function<DoubleDoublePair, Pair<DoubleDoublePair, DoubleDoublePair>> {
    private final DoubleDoublePair[] lines;

    public LinesBracket(DoubleDoublePair[] lines) {
        this.lines = lines;
    }

    @Override
    public Pair<DoubleDoublePair, DoubleDoublePair> apply(DoubleDoublePair point) {
        return binarySearch(lines, 0, lines.length - 1, point);
    }

    private Pair<DoubleDoublePair, DoubleDoublePair> binarySearch(DoubleDoublePair[] lines, int min, int max, DoubleDoublePair point) {
        while (min <= max) {
            int mid = (min + max) / 2;
            double midY = f(lines[mid].first(), lines[mid].second(), point.first());
            if (midY > point.second()) { // the point is under this line (not necessarily bracketed by it
                // three possibilities:
                // 1- the point is below middle, and middle is the first line (mid = 0)
                // 2- the point is below middle, and above middle - 1. bracketed by mid and mid - 1
                // 3- the point is below middle, and below middle - 1. Not bracketed by mid.
                if(mid == 0) {
                    throw new NoSuchElementException("this point is not bracketed by any of the lines! " + point);
                }
                double midYPrevious = f(lines[mid - 1].first(), lines[mid - 1].second(), point.first());
                if(midYPrevious >= point.second()) {
                    max = mid - 1;
                } else {
                    return Pair.of(lines[mid - 1], lines[mid]);
                }
            } else if (midY < point.second()) { // the point is above this line (not necessarily bracketed by it
                // three possibilities:
                // 1- the point is above middle, and middle is the last line (mid = lines.length - 1)
                // 2- the point is above middle, and below mid + 1. bracketed by mid and mid + 1
                // 3- the point is above middle, and above middle + 1. Not bracketed by mid.
                if(mid == lines.length - 1) {
                    throw new NoSuchElementException("this point is not bracketed by any of the lines! " + point);
                }
                double midYNext = f(lines[mid + 1].first(), lines[mid + 1].second(), point.first());
                if(midYNext <= point.second()) {
                    min = mid + 1;
                } else {
                    return Pair.of(lines[mid], lines[mid + 1]);
                }
            } else {
                throw new IllegalArgumentException("this point is on a line, not bracketed! " + point);
            }
        }
        throw new NoSuchElementException("this point is not bracketed by any of the lines! " + point);
    }

    private static double f(double a, double b, double x) {
        return a * x + b;
    }
}

