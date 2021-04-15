package com.codebreeze.algorithms;

import lombok.Data;

import java.math.BigInteger;

import static com.codebreeze.algorithms.Math2.gcd;

@Data
public class BigIntegerFraction implements Comparable<BigIntegerFraction> {

    private static final BigIntegerFraction ZERO = new BigIntegerFraction(BigInteger.ZERO, BigInteger.ONE);

    private final BigInteger numerator;
    private final BigInteger denominator;

    private BigIntegerFraction(BigInteger numerator) {
        this(numerator, BigInteger.ONE);
    }

    private BigIntegerFraction(BigInteger numerator, BigInteger denominator) {
        if (BigInteger.ZERO.equals(denominator)) {
            throw new ArithmeticException("cannot have a intFraction with zero denominator");
        }
        if (Comparables.lessThan(denominator, BigInteger.ZERO)) {
            numerator = numerator.negate();
            denominator = denominator.negate();
        }

        final BigInteger gcd = gcd(numerator, denominator);
        if (Comparables.greaterThan(gcd, BigInteger.ONE)) {
            numerator = numerator.divide(gcd);
            denominator = denominator.divide(gcd);
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static BigIntegerFraction bigIntegerFraction(BigInteger numerator, BigInteger denominator) {
        if (numerator.equals(BigInteger.ZERO)) {
            return ZERO;
        }
        return new BigIntegerFraction(numerator, denominator);
    }

    public static BigIntegerFraction bif(BigInteger numerator) {
        return bigIntegerFraction(numerator);
    }

    public static BigIntegerFraction bigIntegerFraction(BigInteger numerator) {
        if (numerator.equals(BigInteger.ZERO)) {
            return ZERO;
        }
        return new BigIntegerFraction(numerator);
    }

    @Override
    public int compareTo(BigIntegerFraction object) {
        BigInteger nOd = numerator.multiply(object.denominator);
        BigInteger dOn = denominator.multiply(object.numerator);
        return nOd.compareTo(dOn);
    }

    public BigIntegerFraction reciprocal() {
        return new BigIntegerFraction(denominator, numerator);
    }

    public BigIntegerFraction add(BigIntegerFraction fraction) {
        return addOrSub(fraction, true /* add */);
    }

    public BigIntegerFraction add(final BigInteger added) {
        return new BigIntegerFraction(numerator.add(denominator.multiply(added)), denominator);
    }

    public BigIntegerFraction subtract(BigIntegerFraction fraction) {
        return addOrSub(fraction, false /* subtract */);
    }

    public BigIntegerFraction subtract(final BigInteger subtracted) {
        return new BigIntegerFraction(numerator.subtract(denominator.multiply(subtracted)), denominator);
    }

    public BigIntegerFraction negate() {
        return new BigIntegerFraction(numerator.negate(), denominator);
    }

    private BigIntegerFraction addOrSub(BigIntegerFraction fraction, boolean isAdd) {
        if (fraction == null) {
            throw new NullPointerException("cannot process null intFraction");
        }

        // zero is identity for addition.
        if (numerator.equals(BigInteger.ZERO)) {
            return isAdd ? fraction : fraction.negate();
        }

        if (fraction.numerator.equals(BigInteger.ZERO)) {
            return this;
        }

        // if denominators are randomly distributed, d1 will be 1 about 61%
        // of the time.
        BigInteger d1 = gcd(denominator, fraction.denominator);
        if (d1.equals(BigInteger.ONE)) {
            // result is ( (u*v' +/- u'v) / u'v')
            BigInteger uvp = numerator.multiply(fraction.denominator);
            BigInteger upv = fraction.numerator.multiply(denominator);
            BigInteger numerator = isAdd ? uvp.add(upv) : uvp.subtract(upv);
            return new BigIntegerFraction(
                    numerator,
                    denominator.multiply(fraction.denominator)
            );
        }

        // the quantity 't' requires 65 bits of precision; see knuth 4.5.1
        // exercise 7.  we're going to use a BigInteger.
        // t = u(v'/d1) +/- v(u'/d1)
        BigInteger uvp = numerator.multiply(fraction.denominator.divide(d1));
        BigInteger upv = fraction.numerator.multiply(denominator.divide(d1));
        BigInteger t = isAdd ? uvp.add(upv) : uvp.subtract(upv);

        // but d2 doesn't need extra precision because
        // d2 = gcd(t,d1) = gcd(t mod d1, d1)
        BigInteger tmodd1 = t.mod(d1);

        BigInteger d2 = tmodd1.equals(BigInteger.ZERO) ? d1 : gcd(tmodd1, d1);

        // result is (t/d2) / (u'/d1)(v'/d2)
        BigInteger w = t.divide(d2);
        if (w.bitLength() > 31) {
            throw new ArithmeticException("result is too big integer to fit in primitive value: " + w);
        }
        return new BigIntegerFraction(w,
                denominator.divide(d1).multiply(
                        fraction.denominator.divide(d2)));
    }

    public BigIntegerFraction multiply(BigIntegerFraction fraction) {
        if (fraction == null) {
            throw new NullPointerException("cannot process null intFraction");
        }
        if (numerator.equals(BigInteger.ZERO) || fraction.numerator.equals(BigInteger.ZERO)) {
            return ZERO;
        }

        // knuth 4.5.1
        // make sure we don't overflow unless the result *must* overflow.
        BigInteger d1 = gcd(numerator, fraction.denominator);
        BigInteger d2 = gcd(fraction.numerator, denominator);
        BigInteger numerator = this.numerator.divide(d1).multiply(fraction.numerator.divide(d2));
        BigInteger denominator = this.denominator.divide(d2).multiply(fraction.denominator.divide(d1));
        return bigIntegerFraction(numerator, denominator);
    }

    public BigIntegerFraction multiply(final BigInteger i) {
        return multiply(new BigIntegerFraction(i));
    }

    public BigIntegerFraction divide(BigIntegerFraction fraction) {
        if (fraction == null) {
            throw new NullPointerException("cannot process null intFraction");
        }
        if (fraction.numerator.equals(BigInteger.ZERO)) {
            throw new ArithmeticException("cannot divide by intFraction with numerator zero: " + fraction);
        }
        return multiply(fraction.reciprocal());
    }

    public BigIntegerFraction divide(final BigInteger i) {
        return divide(bigIntegerFraction(i));
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}
