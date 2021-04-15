package com.codebreeze.algorithms;

import org.junit.Test;

import java.math.BigInteger;

import static com.codebreeze.algorithms.BigIntegerFraction.bigIntegerFraction;
import static com.codebreeze.algorithms.BigIntegers.bi;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class BigIntegerFractionTest {

    @Test
    public void fraction_from_numerator() {
        // 1 = 1/1
        assertThat(bigIntegerFraction(bi(1)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(1), bi(1));

        // 2 = 2/1
        assertThat(bigIntegerFraction(bi(2)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(2), bi(1));

        // 0 = 0/1
        assertThat(bigIntegerFraction(bi(0)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(0), bi(1));

        // -0 = 0/1
        assertThat(bigIntegerFraction(bi(-0)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(0), bi(1));

        // -3 = -3/1
        assertThat(bigIntegerFraction(bi(-3)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(-3), bi(1));
    }

    @Test
    public void fraction_from_two_parameters() {
        // -1 / -2 = 1 / 2
        assertThat(bigIntegerFraction(bi(-1), bi(-2)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(1), bi(2));

        // 1 / 2 = 1 / 2
        assertThat(bigIntegerFraction(bi(1), bi(2)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(1), bi(2));

        // -1 / 2 = -1 / 2
        assertThat(bigIntegerFraction(bi(-1), bi(2)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(-1), bi(2));

        // 5 / 2 = 5 / 2
        assertThat(bigIntegerFraction(bi(5), bi(2)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(5), bi(2));

        // 0 / 2 = 0 / 1
        assertThat(bigIntegerFraction(bi(0), bi(2)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(0), bi(1));

        // -42 / 2 = -21/1
        assertThat(bigIntegerFraction(bi(-42), bi(2)))
                .extracting("numerator", "denominator")
                .containsExactly(bi(-21), bi(1));

        assertThatThrownBy(() -> bigIntegerFraction(bi(2), bi(0)))
                .isInstanceOf(ArithmeticException.class);
    }

    @Test
    public void compareTo_orders_low_to_high() {
        // -1/-2 < -3/-4 => 1/2 < 3/4
        assertThat(
                bigIntegerFraction(bi(-1), bi(-2))
        ).isLessThan(
                bigIntegerFraction(bi(-3), bi(-4))
        );

        // 0/2 < 3/4 => 0 < 3/4
        assertThat(
                bigIntegerFraction(bi(0), bi(2))
        ).isLessThan(
                bigIntegerFraction(bi(3), bi(4))
        );

        // -1/2 > -3/4
        assertThat(
                bigIntegerFraction(bi(-1), bi(2))
        ).isGreaterThan(
                bigIntegerFraction(bi(-3), bi(4))
        );

        // -1/2 < 3/4
        assertThat(
                bigIntegerFraction(bi(-1), bi(2))
        ).isLessThan(
                bigIntegerFraction(bi(3), bi(4))
        );

        // 1/2 > -3/4
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
        ).isGreaterThan(
                bigIntegerFraction(bi(-3), bi(4))
        );

        // 1/2 < 3/4
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
        ).isLessThan(
                bigIntegerFraction(bi(3), bi(4))
        );

        // 3/2 < 7/4
        assertThat(
                bigIntegerFraction(bi(3), bi(2))
        ).isLessThan(
                bigIntegerFraction(bi(7), bi(4))
        );

        // 4/2 = 6/3 => 2/1 = 2/1
        assertThat(
                bigIntegerFraction(bi(4), bi(2))
        ).isEqualTo(
                bigIntegerFraction(bi(6), bi(3))
        );
    }

    @Test
    public void reciprocal_switches_numerator_and_denominator() {
        // -1/-2 => -2/-1 => 2/1
        assertThat(
                bigIntegerFraction(bi(-1), bi(-2)).reciprocal()
        ).isEqualTo(
                bigIntegerFraction(bi(2), bi(1))
        );

        // 1/-2 => -2/1
        assertThat(
                bigIntegerFraction(bi(1), bi(-2)).reciprocal()
        ).isEqualTo(
                bigIntegerFraction(bi(-2), bi(1))
        );

        // -1/2 => -2/1
        assertThat(
                bigIntegerFraction(bi(-1), bi(2)).reciprocal()
        ).isEqualTo(
                bigIntegerFraction(bi(-2), bi(1))
        );

        // 1/2 => 2/1
        assertThat(
                bigIntegerFraction(bi(1), bi(2)).reciprocal()
        ).isEqualTo(
                bigIntegerFraction(bi(2), bi(1))
        );

        // 3/2 => 2/3
        assertThat(
                bigIntegerFraction(bi(3), bi(2)).reciprocal()
        ).isEqualTo(
                bigIntegerFraction(bi(2), bi(3))
        );

        // 4/2 => 2/4 => 1/2
        assertThat(
                bigIntegerFraction(bi(4), bi(2)).reciprocal()
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(2))
        );

        assertThatThrownBy(
                () -> bigIntegerFraction(bi(0), bi(2)).reciprocal()
        ).isInstanceOf(
                ArithmeticException.class
        );
    }

    @Test
    public void add_two_fractions() {
        // 1/2 + 2/1 = 1/2 + 4/2 = 5/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .add(bigIntegerFraction(bi(2), bi(1)))
        ).isEqualTo(
                bigIntegerFraction(bi(5), bi(2))
        );

        // 3/2 + 2/1 = 3/2 + 4/2 = 7/2
        assertThat(
                bigIntegerFraction(bi(3), bi(2))
                        .add(
                                bigIntegerFraction(bi(2), bi(1))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(7), bi(2))
        );

        // 4/2 + 2/1 = 2/1 + 2/1 = 4/1
        assertThat(
                bigIntegerFraction(bi(4), bi(2))
                        .add(
                                bigIntegerFraction(bi(2), bi(1))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(4), bi(1))
        );

        // 1/2 + 1/2 = 2/2 = 1/1
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .add(
                                bigIntegerFraction(bi(1), bi(2))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(1))
        );

        // 1/2 + 1/4 = 2/4 + 1/4 = 3/4
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .add(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(4))
        );

        // -1/-2 + -1/-4 = 1/2 + 1/4 = 2/4 + 1/4 = 3/4
        assertThat(
                bigIntegerFraction(bi(-1), bi(-2))
                        .add(
                                bigIntegerFraction(bi(-1), bi(-4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(4))
        );

        // 1/-2 + -1/-4 = -1/2 + 1/4 = -2/4 + 1/4 = -1/4
        assertThat(
                bigIntegerFraction(bi(1), bi(-2))
                        .add(
                                bigIntegerFraction(bi(-1), bi(-4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(4))
        );

        // -1/-2 + 1/4 = 1/2 + 1/4 = 2/4 + 1/4 = 3/4
        assertThat(
                bigIntegerFraction(bi(-1), bi(-2))
                        .add(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(4))
        );

        // 0/2 + 1/4 = 0/4 + 1/4 = 1/4
        assertThat(
                bigIntegerFraction(bi(0), bi(2))
                        .add(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(4))
        );

        assertThatThrownBy(() ->
                bigIntegerFraction(bi(0), bi(2)).add((BigInteger) null)
        ).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() ->
                bigIntegerFraction(bi(0), bi(2)).add((BigIntegerFraction) null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void add_number_to_fraction() {
        // 1/2 + 0 = 1/2 + 0/2 = 1/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .add(bi(0))
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(2))
        );

        // 3/2 + 2 = 3/2 + 2/1 = 3/2 + 4/2 = 7/2
        assertThat(
                bigIntegerFraction(bi(3), bi(2))
                        .add(bi(2))

        ).isEqualTo(
                bigIntegerFraction(bi(7), bi(2))
        );

        // 4/2 + 1 = 2/1 + 1/1 = 3/1
        assertThat(
                bigIntegerFraction(bi(4), bi(2))
                        .add(bi(1))
        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(1))
        );

        // 1/2 + -1 = 1/2 + -1/1 = 1/2 + -2/2 = -1/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .add(bi(-1))
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(2))
        );

        // 1/2 + -2 = 1/2 + -2/1 = 1/2 + -4/2 = -3/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .add(bi(-2))
        ).isEqualTo(
                bigIntegerFraction(bi(-3), bi(2))
        );

        // -1/-2 + -2 = 1/2 + -2/1 = 1/2 + -4/2 = -3/2
        assertThat(
                bigIntegerFraction(bi(-1), bi(-2))
                        .add(bi(-2))
        ).isEqualTo(
                bigIntegerFraction(bi(-3), bi(2))
        );

        // -1/2 + 2 = -1/2 + 2/1 = -1/2 + 4/2 = 3/2
        assertThat(
                bigIntegerFraction(bi(-1), bi(2))
                        .add(bi(2))
        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(2))
        );
    }

    @Test
    public void subtract_number_from_fraction() {
        // 1/2 - 0 = 1/2 - 0/2 = 1/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .subtract(bi(0))
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(2))
        );

        // 3/2 - 2 = 3/2 - 2/1 = 3/2 - 4/2 = -1/2
        assertThat(
                bigIntegerFraction(bi(3), bi(2))
                        .subtract(bi(2))
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(2))
        );

        // 4/2 - 1 = 2/1 - 1/1 = 1/1
        assertThat(
                bigIntegerFraction(bi(4), bi(2))
                        .subtract(bi(1))
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(1))
        );

        // 1/2 - (-1) = 1/2 - (-1/1) = 1/2 - (-2/2) = 1/2 + 2/2 =3/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .subtract(bi(-1))
        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(2))
        );

        // 1/2 - (-2) = 1/2 - (-2/1) = 1/2 - (-4/2) = 1/2 + 4/2 = 5/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .subtract(bi(-2))
        ).isEqualTo(
                bigIntegerFraction(bi(5), bi(2))
        );

        // -1/2 - (-2) = -1/2 - (-2/1) = -1/2 - (-4/2) = -1/2 + 4/2 = 3/2
        assertThat(
                bigIntegerFraction(bi(-1), bi(2))
                        .subtract(bi(-2))
        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(2))
        );
    }

    @Test
    public void subtract_two_fractions() {
        // 1/2 - 2/1 = 1/2 - 4/2 = -3/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .subtract(
                                bigIntegerFraction(bi(2), bi(1))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(-3), bi(2))
        );

        // 3/2 - 2/1 = 3/2 - 4/2 = -1/2
        assertThat(
                bigIntegerFraction(bi(3), bi(2))
                        .subtract(
                                bigIntegerFraction(bi(2), bi(1))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(2))
        );

        // 4/2 - 2/1 = 2/1 - 2/1 = 0/1
        assertThat(
                bigIntegerFraction(bi(4), bi(2))
                        .subtract(
                                bigIntegerFraction(bi(2), bi(1))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(0), bi(1))
        );

        // 1/2 - 1/2 = 0/1
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .subtract(
                                bigIntegerFraction(bi(1), bi(2))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(0), bi(1))
        );

        // 1/2 - 1/4 = 2/4 - 1/4 = 1/4
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .subtract(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(4))
        );

        // 0/2 - 1/4 = 0/4 - 1/4 = -1/4
        assertThat(
                bigIntegerFraction(bi(0), bi(2))
                        .subtract(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(4))
        );

        // 3/-4 - (-1/4) = -3/4 + 1/4 = -2/4 = -1/2
        assertThat(
                bigIntegerFraction(bi(3), bi(-4))
                        .subtract(
                                bigIntegerFraction(bi(-1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(2))
        );

        // 3/4 - (-1/4) = 3/4 + 1/4 = 4/4 = 1/1
        assertThat(
                bigIntegerFraction(bi(3), bi(4))
                        .subtract(
                                bigIntegerFraction(bi(-1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(1))
        );

        // 3/-4 - 1/4 = -3/4 -1/4 = -4/4 = -1/1
        assertThat(
                bigIntegerFraction(bi(3), bi(-4))
                        .subtract(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(1))
        );

        assertThatThrownBy(() ->
                bigIntegerFraction(bi(0), bi(2)).subtract((BigInteger) null)
        ).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() ->
                bigIntegerFraction(bi(0), bi(2)).subtract((BigIntegerFraction) null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void negate_fraction() {
        // -1/2 = -1/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2)).negate()
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(2))
        );

        // -3/2 = -3/2
        assertThat(
                bigIntegerFraction(bi(3), bi(2)).negate()
        ).isEqualTo(
                bigIntegerFraction(bi(-3), bi(2))
        );

        // -(-4/2) = 4/2 = 2/1
        assertThat(
                bigIntegerFraction(bi(-4), bi(2)).negate()
        ).isEqualTo(
                bigIntegerFraction(bi(2), bi(1))
        );

        // -(-4/-2) = -(4/2) = -2/1
        assertThat(
                bigIntegerFraction(bi(-4), bi(-2)).negate()
        ).isEqualTo(
                bigIntegerFraction(bi(-2), bi(1))
        );

        // -(1/-2) = -(-1/2) = 1/2
        assertThat(
                bigIntegerFraction(bi(1), bi(-2)).negate()
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(2))
        );

        // -(0/2) = -0/2 = 0/1
        assertThat(
                bigIntegerFraction(bi(0), bi(2)).negate()
        ).isEqualTo(
                bigIntegerFraction(bi(0), bi(1))
        );
    }

    @Test
    public void multiply_fraction_and_number() {
        // 1/2 * 0 = 1/2 * 0/1 = 1/2 * 0/2 = 0/2 = 0/1
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .multiply(bi(0))
        ).isEqualTo(
                bigIntegerFraction(bi(0), bi(1))
        );

        // 3/2 * 2 = 3/2 * 2/1 = 6/2 = 3/1
        assertThat(
                bigIntegerFraction(bi(3), bi(2))
                        .multiply(bi(2))

        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(1))
        );

        // 4/2 * 1/1 = 4/2 = 2/1
        assertThat(
                bigIntegerFraction(bi(4), bi(2))
                        .multiply(bi(1))
        ).isEqualTo(
                bigIntegerFraction(bi(2), bi(1))
        );

        // 1/2 * -1 = 1/2 * -1/1 = -1/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .multiply(bi(-1))
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(2))
        );

        // 1/2 * -2/1 = -2/2 = -1/1
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .multiply(bi(-2))
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(1))
        );

        // -1/2 * -2 = -1/2 * -2/1 = 2/2 = 1/1
        assertThat(
                bigIntegerFraction(bi(-1), bi(2))
                        .multiply(bi(-2))
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(1))
        );

        // -1/-2 * -2 = -1/-2 * -2/1 = 1/2 * -2/1 = -2/2 = -1/1
        assertThat(
                bigIntegerFraction(bi(-1), bi(-2))
                        .multiply(bi(-2))
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(1))
        );
    }

    @Test
    public void multiply_two_fractions() {
        // 1/2 * 2/1 = 2/2 = 1/1
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .multiply(bigIntegerFraction(bi(2), bi(1)))
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(1))
        );

        // 3/2 * 2/1 = 6/2 = 3/1
        assertThat(
                bigIntegerFraction(bi(3), bi(2))
                        .multiply(
                                bigIntegerFraction(bi(2), bi(1))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(1))
        );

        // 4/2 * 2/1 = 8/2 = 4/1
        assertThat(
                bigIntegerFraction(bi(4), bi(2))
                        .multiply(
                                bigIntegerFraction(bi(2), bi(1))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(4), bi(1))
        );

        // 1/2 * 1/2 = 1/4
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .multiply(
                                bigIntegerFraction(bi(1), bi(2))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(4))
        );

        // 1/2 * 1/4 = 1/8
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .multiply(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(8))
        );

        // 0/2 * 1/4 = 0/8 = 0/1
        assertThat(
                bigIntegerFraction(bi(0), bi(2))
                        .multiply(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(0), bi(1))
        );

        // 2/3 * -1/4 = -2/12 = -1/6
        assertThat(
                bigIntegerFraction(bi(2), bi(3))
                        .multiply(
                                bigIntegerFraction(bi(-1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(6))
        );

        // -2/3 * -1/4 = 2/12 = 1/6
        assertThat(
                bigIntegerFraction(bi(-2), bi(3))
                        .multiply(
                                bigIntegerFraction(bi(-1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(6))
        );

        // -2/-3 * -1/-4 = 2/3 * 1/4 = 2/12 = 1/6
        assertThat(
                bigIntegerFraction(bi(-2), bi(-3))
                        .multiply(
                                bigIntegerFraction(bi(-1), bi(-4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(6))
        );

        assertThatThrownBy(() ->
                bigIntegerFraction(bi(0), bi(2)).multiply((BigIntegerFraction) null)
        ).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() ->
                bigIntegerFraction(bi(0), bi(2)).multiply((BigInteger) null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void divide_fraction_and_number() {
        // (3/2) / (2) = (3/2) / (2/1) = (3/2) * (1/2) = 3/4
        assertThat(
                bigIntegerFraction(bi(3), bi(2))
                        .divide(bi(2))
        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(4))
        );

        // (4/2) / (3) = (4/2) / (3/1) = (4/2) * (1/3) = 4/6 = 2/3
        assertThat(
                bigIntegerFraction(bi(4), bi(2))
                        .divide(bi(3))
        ).isEqualTo(
                bigIntegerFraction(bi(2), bi(3))
        );

        // (1/2) / (-1) = (1/2) / (-1/1) = (1/2) * (-1/1) = -1/2
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .divide(bi(-1))
        ).isEqualTo(
                bigIntegerFraction(bi(-1), bi(2))
        );

        // (-1/2) / (-2) = (-1/2) / (-2/1) = (-1/2) * (-1/2) = 1/4
        assertThat(
                bigIntegerFraction(bi(-1), bi(2))
                        .divide(bi(-2))
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(4))
        );

        // (1/2) / (0) = (1/2) / (0/1) = (1/2) * (1/0) =>exception
        assertThatThrownBy(() ->
                bigIntegerFraction(bi(1), bi(2))
                        .divide(bi(0))
        ).isInstanceOf(
                ArithmeticException.class
        );
    }

    @Test
    public void divide_two_fractions() {
        // 1/2 / 2/1 = 1/2 * 1/2 = 1/4
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .divide(bigIntegerFraction(bi(2), bi(1)))
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(4))
        );

        // 3/2 / 2/1 = 3/2 * 1/2 = 3/4
        assertThat(
                bigIntegerFraction(bi(3), bi(2))
                        .divide(
                                bigIntegerFraction(bi(2), bi(1))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(3), bi(4))
        );

        // 4/2 / 2/1 = 2/1 / 2/1 = 1/1
        assertThat(
                bigIntegerFraction(bi(4), bi(2))
                        .divide(
                                bigIntegerFraction(bi(2), bi(1))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(1))
        );

        // 1/2 / 1/2 = 1/2 * 2/1 = 1/1
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .divide(
                                bigIntegerFraction(bi(1), bi(2))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(1), bi(1))
        );

        // 1/2 / 1/4 = 1/2 * 4/1 = 2/1
        assertThat(
                bigIntegerFraction(bi(1), bi(2))
                        .divide(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(2), bi(1))
        );

        // 0/2 / 1/4 = 0/2 * 4/1 = 0/2 = 0/1
        assertThat(
                bigIntegerFraction(bi(0), bi(2))
                        .divide(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(0), bi(1))
        );

        // 3/2 / -1/4 = 3/2 * -4/1 = -12/2 = -6/1
        assertThat(
                bigIntegerFraction(bi(3), bi(2))
                        .divide(
                                bigIntegerFraction(bi(-1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(-6), bi(1))
        );

        // -3/2 / 1/4 = -3/2 * 4/1 = -12/2 = -6/1
        assertThat(
                bigIntegerFraction(bi(-3), bi(2))
                        .divide(
                                bigIntegerFraction(bi(1), bi(4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(-6), bi(1))
        );

        // -1/-2 / -1/-4 = 1/2 / 1/4 = 1/2 * 4/1 = 2/1
        assertThat(
                bigIntegerFraction(bi(-1), bi(-2))
                        .divide(
                                bigIntegerFraction(bi(-1), bi(-4))
                        )
        ).isEqualTo(
                bigIntegerFraction(bi(2), bi(1))
        );

        assertThatThrownBy(() ->
                bigIntegerFraction(bi(0), bi(2)).divide((BigInteger) null)
        ).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() ->
                bigIntegerFraction(bi(0), bi(2)).divide((BigIntegerFraction) null)
        ).isInstanceOf(NullPointerException.class);
    }
}
