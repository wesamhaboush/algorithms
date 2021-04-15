package com.codebreeze.algorithms;

import lombok.Data;

import java.math.BigInteger;

import static com.codebreeze.algorithms.Math2.gcd;
import static java.lang.Math.multiplyExact;
import static java.lang.Math.subtractExact;

@Data
public class IntFraction implements Comparable<IntFraction> {

    private static final IntFraction ZERO = new IntFraction(0, 1);

    private final int numerator;
    private final int denominator;

    private IntFraction(int numerator) {
        this(numerator, 1);
    }

    private IntFraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("cannot have a intFraction with zero denominator");
        }
        if (denominator == Integer.MIN_VALUE && (numerator & 1) == 0) {
            numerator /= 2;
            denominator /= 2;
        }
        if (denominator < 0) {
            if (numerator == Integer.MIN_VALUE || denominator == Integer.MIN_VALUE) {
                throw new ArithmeticException(
                        "too big value for numerator or denominator: " + numerator + ", " + denominator
                );
            }
            numerator = -numerator;
            denominator = -denominator;
        }

        final int gcd = gcd(numerator, denominator);
        if (gcd > 1) {
            numerator /= gcd;
            denominator /= gcd;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static IntFraction intFraction(int numerator, int denominator) {
        if (numerator == 0) {
            return ZERO;
        }
        return new IntFraction(numerator, denominator);
    }

    public static IntFraction intFraction(int numerator) {
        if (numerator == 0) {
            return ZERO;
        }
        return new IntFraction(numerator);
    }

    @Override
    public int compareTo(IntFraction object) {
        long nOd = numerator * object.denominator;
        long dOn = denominator * object.numerator;
        return Long.compare(nOd, dOn);
    }

    public IntFraction reciprocal() {
        return new IntFraction(denominator, numerator);
    }

    public IntFraction add(IntFraction fraction) {
        return addOrSub(fraction, true /* add */);
    }

    public IntFraction add(final int i) {
        return new IntFraction(numerator + i * denominator, denominator);
    }

    public IntFraction subtract(IntFraction fraction) {
        return addOrSub(fraction, false /* subtract */);
    }

    public IntFraction subtract(final int i) {
        return new IntFraction(numerator - i * denominator, denominator);
    }

    public IntFraction negate() {
        if (numerator == Integer.MIN_VALUE) {
            throw new ArithmeticException("cannot negate intFraction because numerator is the least integer: " + numerator);
        }
        return new IntFraction(-numerator, denominator);
    }

    private IntFraction addOrSub(IntFraction fraction, boolean isAdd) {
        if (fraction == null) {
            throw new NullPointerException("cannot process null intFraction");
        }

        // zero is identity for addition.
        if (numerator == 0) {
            return isAdd ? fraction : fraction.negate();
        }

        if (fraction.numerator == 0) {
            return this;
        }

        // if denominators are randomly distributed, d1 will be 1 about 61%
        // of the time.
        int d1 = gcd(denominator, fraction.denominator);
        if (d1 == 1) {
            // result is ( (u*v' +/- u'v) / u'v')
            int uvp = multiplyExact(numerator, fraction.denominator);
            int upv = multiplyExact(fraction.numerator, denominator);
            int numerator = isAdd ? Math.addExact(uvp, upv) : subtractExact(uvp, upv);
            return new IntFraction(
                    numerator,
                    multiplyExact(denominator, fraction.denominator)
            );
        }

        // the quantity 't' requires 65 bits of precision; see knuth 4.5.1
        // exercise 7.  we're going to use a BigInteger.
        // t = u(v'/d1) +/- v(u'/d1)
        BigInteger uvp = BigInteger.valueOf(numerator).multiply(BigInteger.valueOf(fraction.denominator / d1));
        BigInteger upv = BigInteger.valueOf(fraction.numerator).multiply(BigInteger.valueOf(denominator / d1));
        BigInteger t = isAdd ? uvp.add(upv) : uvp.subtract(upv);

        // but d2 doesn't need extra precision because
        // d2 = gcd(t,d1) = gcd(t mod d1, d1)
        int tmodd1 = t.mod(BigInteger.valueOf(d1)).intValue();

        int d2 = (tmodd1 == 0) ? d1 : gcd(tmodd1, d1);

        // result is (t/d2) / (u'/d1)(v'/d2)
        BigInteger w = t.divide(BigInteger.valueOf(d2));
        if (w.bitLength() > 31) {
            throw new ArithmeticException("result is too big integer to fit in primitive value: " + w);
        }
        return new IntFraction(w.intValue(),
                multiplyExact(denominator / d1,
                        fraction.denominator / d2));
    }

    public IntFraction multiply(IntFraction fraction) {
        if (fraction == null) {
            throw new NullPointerException("cannot process null intFraction");
        }
        if (numerator == 0 || fraction.numerator == 0) {
            return ZERO;
        }

        // knuth 4.5.1
        // make sure we don't overflow unless the result *must* overflow.
        int d1 = gcd(numerator, fraction.denominator);
        int d2 = gcd(fraction.numerator, denominator);
        int numerator = multiplyExact(this.numerator / d1, fraction.numerator / d2);
        int denominator = multiplyExact(this.denominator / d2, fraction.denominator / d1);
        return intFraction(numerator, denominator);
    }

    public IntFraction multiply(final int i) {
        return multiply(new IntFraction(i));
    }

    public IntFraction divide(IntFraction fraction) {
        if (fraction == null) {
            throw new NullPointerException("cannot process null intFraction");
        }
        if (fraction.numerator == 0) {
            throw new ArithmeticException("cannot divide by intFraction with numerator zero: " + fraction);
        }
        return multiply(fraction.reciprocal());
    }

    public IntFraction divide(final int i) {
        return divide(intFraction(i));
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
