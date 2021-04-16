package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static com.codebreeze.algorithms.IntFraction.intFraction;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class IntFractionTest {

    @Test
    public void fraction_from_numerator() {
        // 1 = 1/1
        assertThat(intFraction(1))
                .extracting("numerator", "denominator")
                .containsExactly(1, 1);

        // 2 = 2/1
        assertThat(intFraction(2))
                .extracting("numerator", "denominator")
                .containsExactly(2, 1);

        // 0 = 0/1
        assertThat(intFraction(0))
                .extracting("numerator", "denominator")
                .containsExactly(0, 1);

        // -0 = 0/1
        assertThat(intFraction(-0))
                .extracting("numerator", "denominator")
                .containsExactly(0, 1);

        // -3 = -3/1
        assertThat(intFraction(-3))
                .extracting("numerator", "denominator")
                .containsExactly(-3, 1);
    }

    @Test
    public void fraction_from_two_parameters() {
        // -1 / -2 = 1 / 2
        assertThat(IntFraction.intFraction(-1, -2))
                .extracting("numerator", "denominator")
                .containsExactly(1, 2);

        // 1 / 2 = 1 / 2
        assertThat(IntFraction.intFraction(1, 2))
                .extracting("numerator", "denominator")
                .containsExactly(1, 2);

        // -1 / 2 = -1 / 2
        assertThat(IntFraction.intFraction(-1, 2))
                .extracting("numerator", "denominator")
                .containsExactly(-1, 2);

        // 5 / 2 = 5 / 2
        assertThat(IntFraction.intFraction(5, 2))
                .extracting("numerator", "denominator")
                .containsExactly(5, 2);

        // 0 / 2 = 0 / 1
        assertThat(IntFraction.intFraction(0, 2))
                .extracting("numerator", "denominator")
                .containsExactly(0, 1);

        // -42 / 2 = -21/1
        assertThat(IntFraction.intFraction(-42, 2))
                .extracting("numerator", "denominator")
                .containsExactly(-21, 1);

        assertThatThrownBy(() -> IntFraction.intFraction(2, 0))
                .isInstanceOf(ArithmeticException.class);
    }

    @Test
    public void compareTo_orders_low_to_high() {
        // -1/-2 < -3/-4 => 1/2 < 3/4
        assertThat(
                IntFraction.intFraction(-1, -2)
        ).isLessThan(
                IntFraction.intFraction(-3, -4)
        );

        // 0/2 < 3/4 => 0 < 3/4
        assertThat(
                IntFraction.intFraction(0, 2)
        ).isLessThan(
                IntFraction.intFraction(3, 4)
        );

        // -1/2 > -3/4
        assertThat(
                IntFraction.intFraction(-1, 2)
        ).isGreaterThan(
                IntFraction.intFraction(-3, 4)
        );

        // -1/2 < 3/4
        assertThat(
                IntFraction.intFraction(-1, 2)
        ).isLessThan(
                IntFraction.intFraction(3, 4)
        );

        // 1/2 > -3/4
        assertThat(
                IntFraction.intFraction(1, 2)
        ).isGreaterThan(
                IntFraction.intFraction(-3, 4)
        );

        // 1/2 < 3/4
        assertThat(
                IntFraction.intFraction(1, 2)
        ).isLessThan(
                IntFraction.intFraction(3, 4)
        );

        // 3/2 < 7/4
        assertThat(
                IntFraction.intFraction(3, 2)
        ).isLessThan(
                IntFraction.intFraction(7, 4)
        );

        // 4/2 = 6/3 => 2/1 = 2/1
        assertThat(
                IntFraction.intFraction(4, 2)
        ).isEqualTo(
                IntFraction.intFraction(6, 3)
        );
    }

    @Test
    public void reciprocal_switches_numerator_and_denominator() {
        // -1/-2 => -2/-1 => 2/1
        assertThat(
                IntFraction.intFraction(-1, -2).reciprocal()
        ).isEqualTo(
                IntFraction.intFraction(2, 1)
        );

        // 1/-2 => -2/1
        assertThat(
                IntFraction.intFraction(1, -2).reciprocal()
        ).isEqualTo(
                IntFraction.intFraction(-2, 1)
        );

        // -1/2 => -2/1
        assertThat(
                IntFraction.intFraction(-1, 2).reciprocal()
        ).isEqualTo(
                IntFraction.intFraction(-2, 1)
        );

        // 1/2 => 2/1
        assertThat(
                IntFraction.intFraction(1, 2).reciprocal()
        ).isEqualTo(
                IntFraction.intFraction(2, 1)
        );

        // 3/2 => 2/3
        assertThat(
                IntFraction.intFraction(3, 2).reciprocal()
        ).isEqualTo(
                IntFraction.intFraction(2, 3)
        );

        // 4/2 => 2/4 => 1/2
        assertThat(
                IntFraction.intFraction(4, 2).reciprocal()
        ).isEqualTo(
                IntFraction.intFraction(1, 2)
        );

        assertThatThrownBy(
                () -> IntFraction.intFraction(0, 2).reciprocal()
        ).isInstanceOf(
                ArithmeticException.class
        );
    }

    @Test
    public void add_two_fractions() {
        // 1/2 + 2/1 = 1/2 + 4/2 = 5/2
        assertThat(
                IntFraction.intFraction(1, 2)
                        .add(IntFraction.intFraction(2, 1))
        ).isEqualTo(
                IntFraction.intFraction(5, 2)
        );

        // 3/2 + 2/1 = 3/2 + 4/2 = 7/2
        assertThat(
                IntFraction.intFraction(3, 2)
                        .add(
                                IntFraction.intFraction(2, 1)
                        )
        ).isEqualTo(
                IntFraction.intFraction(7, 2)
        );

        // 4/2 + 2/1 = 2/1 + 2/1 = 4/1
        assertThat(
                IntFraction.intFraction(4, 2)
                        .add(
                                IntFraction.intFraction(2, 1)
                        )
        ).isEqualTo(
                IntFraction.intFraction(4, 1)
        );

        // 1/2 + 1/2 = 2/2 = 1/1
        assertThat(
                IntFraction.intFraction(1, 2)
                        .add(
                                IntFraction.intFraction(1, 2)
                        )
        ).isEqualTo(
                IntFraction.intFraction(1, 1)
        );

        // 1/2 + 1/4 = 2/4 + 1/4 = 3/4
        assertThat(
                IntFraction.intFraction(1, 2)
                        .add(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(3, 4)
        );

        // -1/-2 + -1/-4 = 1/2 + 1/4 = 2/4 + 1/4 = 3/4
        assertThat(
                IntFraction.intFraction(-1, -2)
                        .add(
                                IntFraction.intFraction(-1, -4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(3, 4)
        );

        // 1/-2 + -1/-4 = -1/2 + 1/4 = -2/4 + 1/4 = -1/4
        assertThat(
                IntFraction.intFraction(1, -2)
                        .add(
                                IntFraction.intFraction(-1, -4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(-1, 4)
        );

        // -1/-2 + 1/4 = 1/2 + 1/4 = 2/4 + 1/4 = 3/4
        assertThat(
                IntFraction.intFraction(-1, -2)
                        .add(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(3, 4)
        );

        // 0/2 + 1/4 = 0/4 + 1/4 = 1/4
        assertThat(
                IntFraction.intFraction(0, 2)
                        .add(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(1, 4)
        );

        assertThatThrownBy(() ->
                IntFraction.intFraction(0, 2).add(null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void add_number_to_fraction() {
        // 1/2 + 0 = 1/2 + 0/2 = 1/2
        assertThat(
                IntFraction.intFraction(1, 2)
                        .add(0)
        ).isEqualTo(
                IntFraction.intFraction(1, 2)
        );

        // 3/2 + 2 = 3/2 + 2/1 = 3/2 + 4/2 = 7/2
        assertThat(
                IntFraction.intFraction(3, 2)
                        .add(2)

        ).isEqualTo(
                IntFraction.intFraction(7, 2)
        );

        // 4/2 + 1 = 2/1 + 1/1 = 3/1
        assertThat(
                IntFraction.intFraction(4, 2)
                        .add(1)
        ).isEqualTo(
                IntFraction.intFraction(3, 1)
        );

        // 1/2 + -1 = 1/2 + -1/1 = 1/2 + -2/2 = -1/2
        assertThat(
                IntFraction.intFraction(1, 2)
                        .add(-1)
        ).isEqualTo(
                IntFraction.intFraction(-1, 2)
        );

        // 1/2 + -2 = 1/2 + -2/1 = 1/2 + -4/2 = -3/2
        assertThat(
                IntFraction.intFraction(1, 2)
                        .add(-2)
        ).isEqualTo(
                IntFraction.intFraction(-3, 2)
        );

        // -1/-2 + -2 = 1/2 + -2/1 = 1/2 + -4/2 = -3/2
        assertThat(
                IntFraction.intFraction(-1, -2)
                        .add(-2)
        ).isEqualTo(
                IntFraction.intFraction(-3, 2)
        );

        // -1/2 + 2 = -1/2 + 2/1 = -1/2 + 4/2 = 3/2
        assertThat(
                IntFraction.intFraction(-1, 2)
                        .add(2)
        ).isEqualTo(
                IntFraction.intFraction(3, 2)
        );
    }

    @Test
    public void subtract_number_from_fraction() {
        // 1/2 - 0 = 1/2 - 0/2 = 1/2
        assertThat(
                IntFraction.intFraction(1, 2)
                        .subtract(0)
        ).isEqualTo(
                IntFraction.intFraction(1, 2)
        );

        // 3/2 - 2 = 3/2 - 2/1 = 3/2 - 4/2 = -1/2
        assertThat(
                IntFraction.intFraction(3, 2)
                        .subtract(2)
        ).isEqualTo(
                IntFraction.intFraction(-1, 2)
        );

        // 4/2 - 1 = 2/1 - 1/1 = 1/1
        assertThat(
                IntFraction.intFraction(4, 2)
                        .subtract(1)
        ).isEqualTo(
                IntFraction.intFraction(1, 1)
        );

        // 1/2 - (-1) = 1/2 - (-1/1) = 1/2 - (-2/2) = 1/2 + 2/2 =3/2
        assertThat(
                IntFraction.intFraction(1, 2)
                        .subtract(-1)
        ).isEqualTo(
                IntFraction.intFraction(3, 2)
        );

        // 1/2 - (-2) = 1/2 - (-2/1) = 1/2 - (-4/2) = 1/2 + 4/2 = 5/2
        assertThat(
                IntFraction.intFraction(1, 2)
                        .subtract(-2)
        ).isEqualTo(
                IntFraction.intFraction(5, 2)
        );

        // -1/2 - (-2) = -1/2 - (-2/1) = -1/2 - (-4/2) = -1/2 + 4/2 = 3/2
        assertThat(
                IntFraction.intFraction(-1, 2)
                        .subtract(-2)
        ).isEqualTo(
                IntFraction.intFraction(3, 2)
        );
    }

    @Test
    public void subtract_two_fractions() {
        // 1/2 - 2/1 = 1/2 - 4/2 = -3/2
        assertThat(
                IntFraction.intFraction(1, 2)
                        .subtract(
                                IntFraction.intFraction(2, 1)
                        )
        ).isEqualTo(
                IntFraction.intFraction(-3, 2)
        );

        // 3/2 - 2/1 = 3/2 - 4/2 = -1/2
        assertThat(
                IntFraction.intFraction(3, 2)
                        .subtract(
                                IntFraction.intFraction(2, 1)
                        )
        ).isEqualTo(
                IntFraction.intFraction(-1, 2)
        );

        // 4/2 - 2/1 = 2/1 - 2/1 = 0/1
        assertThat(
                IntFraction.intFraction(4, 2)
                        .subtract(
                                IntFraction.intFraction(2, 1)
                        )
        ).isEqualTo(
                IntFraction.intFraction(0, 1)
        );

        // 1/2 - 1/2 = 0/1
        assertThat(
                IntFraction.intFraction(1, 2)
                        .subtract(
                                IntFraction.intFraction(1, 2)
                        )
        ).isEqualTo(
                IntFraction.intFraction(0, 1)
        );

        // 1/2 - 1/4 = 2/4 - 1/4 = 1/4
        assertThat(
                IntFraction.intFraction(1, 2)
                        .subtract(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(1, 4)
        );

        // 0/2 - 1/4 = 0/4 - 1/4 = -1/4
        assertThat(
                IntFraction.intFraction(0, 2)
                        .subtract(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(-1, 4)
        );

        // 3/-4 - (-1/4) = -3/4 + 1/4 = -2/4 = -1/2
        assertThat(
                IntFraction.intFraction(3, -4)
                        .subtract(
                                IntFraction.intFraction(-1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(-1, 2)
        );

        // 3/4 - (-1/4) = 3/4 + 1/4 = 4/4 = 1/1
        assertThat(
                IntFraction.intFraction(3, 4)
                        .subtract(
                                IntFraction.intFraction(-1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(1, 1)
        );

        // 3/-4 - 1/4 = -3/4 -1/4 = -4/4 = -1/1
        assertThat(
                IntFraction.intFraction(3, -4)
                        .subtract(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(-1, 1)
        );

        assertThatThrownBy(() ->
                IntFraction.intFraction(0, 2).subtract(null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void negate_fraction() {
        // -1/2 = -1/2
        assertThat(
                IntFraction.intFraction(1, 2).negate()
        ).isEqualTo(
                IntFraction.intFraction(-1, 2)
        );

        // -3/2 = -3/2
        assertThat(
                IntFraction.intFraction(3, 2).negate()
        ).isEqualTo(
                IntFraction.intFraction(-3, 2)
        );

        // -(-4/2) = 4/2 = 2/1
        assertThat(
                IntFraction.intFraction(-4, 2).negate()
        ).isEqualTo(
                IntFraction.intFraction(2, 1)
        );

        // -(-4/-2) = -(4/2) = -2/1
        assertThat(
                IntFraction.intFraction(-4, -2).negate()
        ).isEqualTo(
                IntFraction.intFraction(-2, 1)
        );

        // -(1/-2) = -(-1/2) = 1/2
        assertThat(
                IntFraction.intFraction(1, -2).negate()
        ).isEqualTo(
                IntFraction.intFraction(1, 2)
        );

        // -(0/2) = -0/2 = 0/1
        assertThat(
                IntFraction.intFraction(0, 2).negate()
        ).isEqualTo(
                IntFraction.intFraction(0, 1)
        );
    }

    @Test
    public void multiply_fraction_and_number() {
        // 1/2 * 0 = 1/2 * 0/1 = 1/2 * 0/2 = 0/2 = 0/1
        assertThat(
                IntFraction.intFraction(1, 2)
                        .multiply(0)
        ).isEqualTo(
                IntFraction.intFraction(0, 1)
        );

        // 3/2 * 2 = 3/2 * 2/1 = 6/2 = 3/1
        assertThat(
                IntFraction.intFraction(3, 2)
                        .multiply(2)

        ).isEqualTo(
                IntFraction.intFraction(3, 1)
        );

        // 4/2 * 1/1 = 4/2 = 2/1
        assertThat(
                IntFraction.intFraction(4, 2)
                        .multiply(1)
        ).isEqualTo(
                IntFraction.intFraction(2, 1)
        );

        // 1/2 * -1 = 1/2 * -1/1 = -1/2
        assertThat(
                IntFraction.intFraction(1, 2)
                        .multiply(-1)
        ).isEqualTo(
                IntFraction.intFraction(-1, 2)
        );

        // 1/2 * -2/1 = -2/2 = -1/1
        assertThat(
                IntFraction.intFraction(1, 2)
                        .multiply(-2)
        ).isEqualTo(
                IntFraction.intFraction(-1, 1)
        );

        // -1/2 * -2 = -1/2 * -2/1 = 2/2 = 1/1
        assertThat(
                IntFraction.intFraction(-1, 2)
                        .multiply(-2)
        ).isEqualTo(
                IntFraction.intFraction(1, 1)
        );

        // -1/-2 * -2 = -1/-2 * -2/1 = 1/2 * -2/1 = -2/2 = -1/1
        assertThat(
                IntFraction.intFraction(-1, -2)
                        .multiply(-2)
        ).isEqualTo(
                IntFraction.intFraction(-1, 1)
        );
    }

    @Test
    public void multiply_two_fractions() {
        // 1/2 * 2/1 = 2/2 = 1/1
        assertThat(
                IntFraction.intFraction(1, 2)
                        .multiply(IntFraction.intFraction(2, 1))
        ).isEqualTo(
                IntFraction.intFraction(1, 1)
        );

        // 3/2 * 2/1 = 6/2 = 3/1
        assertThat(
                IntFraction.intFraction(3, 2)
                        .multiply(
                                IntFraction.intFraction(2, 1)
                        )
        ).isEqualTo(
                IntFraction.intFraction(3, 1)
        );

        // 4/2 * 2/1 = 8/2 = 4/1
        assertThat(
                IntFraction.intFraction(4, 2)
                        .multiply(
                                IntFraction.intFraction(2, 1)
                        )
        ).isEqualTo(
                IntFraction.intFraction(4, 1)
        );

        // 1/2 * 1/2 = 1/4
        assertThat(
                IntFraction.intFraction(1, 2)
                        .multiply(
                                IntFraction.intFraction(1, 2)
                        )
        ).isEqualTo(
                IntFraction.intFraction(1, 4)
        );

        // 1/2 * 1/4 = 1/8
        assertThat(
                IntFraction.intFraction(1, 2)
                        .multiply(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(1, 8)
        );

        // 0/2 * 1/4 = 0/8 = 0/1
        assertThat(
                IntFraction.intFraction(0, 2)
                        .multiply(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(0, 1)
        );

        // 2/3 * -1/4 = -2/12 = -1/6
        assertThat(
                IntFraction.intFraction(2, 3)
                        .multiply(
                                IntFraction.intFraction(-1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(-1, 6)
        );

        // -2/3 * -1/4 = 2/12 = 1/6
        assertThat(
                IntFraction.intFraction(-2, 3)
                        .multiply(
                                IntFraction.intFraction(-1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(1, 6)
        );

        // -2/-3 * -1/-4 = 2/3 * 1/4 = 2/12 = 1/6
        assertThat(
                IntFraction.intFraction(-2, -3)
                        .multiply(
                                IntFraction.intFraction(-1, -4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(1, 6)
        );

        assertThatThrownBy(() ->
                IntFraction.intFraction(0, 2).multiply(null)
        ).isInstanceOf(NullPointerException.class);
    }

    @Test
    public void divide_fraction_and_number() {
        // (3/2) / (2) = (3/2) / (2/1) = (3/2) * (1/2) = 3/4
        assertThat(
                IntFraction.intFraction(3, 2)
                        .divide(2)
        ).isEqualTo(
                IntFraction.intFraction(3, 4)
        );

        // (4/2) / (3) = (4/2) / (3/1) = (4/2) * (1/3) = 4/6 = 2/3
        assertThat(
                IntFraction.intFraction(4, 2)
                        .divide(3)
        ).isEqualTo(
                IntFraction.intFraction(2, 3)
        );

        // (1/2) / (-1) = (1/2) / (-1/1) = (1/2) * (-1/1) = -1/2
        assertThat(
                IntFraction.intFraction(1, 2)
                        .divide(-1)
        ).isEqualTo(
                IntFraction.intFraction(-1, 2)
        );

        // (-1/2) / (-2) = (-1/2) / (-2/1) = (-1/2) * (-1/2) = 1/4
        assertThat(
                IntFraction.intFraction(-1, 2)
                        .divide(-2)
        ).isEqualTo(
                IntFraction.intFraction(1, 4)
        );

        // (1/2) / (0) = (1/2) / (0/1) = (1/2) * (1/0) =>exception
        assertThatThrownBy(() ->
                IntFraction.intFraction(1, 2)
                        .divide(0)
        ).isInstanceOf(
                ArithmeticException.class
        );
    }

    @Test
    public void divide_two_fractions() {
        // 1/2 / 2/1 = 1/2 * 1/2 = 1/4
        assertThat(
                IntFraction.intFraction(1, 2)
                        .divide(IntFraction.intFraction(2, 1))
        ).isEqualTo(
                IntFraction.intFraction(1, 4)
        );

        // 3/2 / 2/1 = 3/2 * 1/2 = 3/4
        assertThat(
                IntFraction.intFraction(3, 2)
                        .divide(
                                IntFraction.intFraction(2, 1)
                        )
        ).isEqualTo(
                IntFraction.intFraction(3, 4)
        );

        // 4/2 / 2/1 = 2/1 / 2/1 = 1/1
        assertThat(
                IntFraction.intFraction(4, 2)
                        .divide(
                                IntFraction.intFraction(2, 1)
                        )
        ).isEqualTo(
                IntFraction.intFraction(1, 1)
        );

        // 1/2 / 1/2 = 1/2 * 2/1 = 1/1
        assertThat(
                IntFraction.intFraction(1, 2)
                        .divide(
                                IntFraction.intFraction(1, 2)
                        )
        ).isEqualTo(
                IntFraction.intFraction(1, 1)
        );

        // 1/2 / 1/4 = 1/2 * 4/1 = 2/1
        assertThat(
                IntFraction.intFraction(1, 2)
                        .divide(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(2, 1)
        );

        // 0/2 / 1/4 = 0/2 * 4/1 = 0/2 = 0/1
        assertThat(
                IntFraction.intFraction(0, 2)
                        .divide(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(0, 1)
        );

        // 3/2 / -1/4 = 3/2 * -4/1 = -12/2 = -6/1
        assertThat(
                IntFraction.intFraction(3, 2)
                        .divide(
                                IntFraction.intFraction(-1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(-6, 1)
        );

        // -3/2 / 1/4 = -3/2 * 4/1 = -12/2 = -6/1
        assertThat(
                IntFraction.intFraction(-3, 2)
                        .divide(
                                IntFraction.intFraction(1, 4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(-6, 1)
        );

        // -1/-2 / -1/-4 = 1/2 / 1/4 = 1/2 * 4/1 = 2/1
        assertThat(
                IntFraction.intFraction(-1, -2)
                        .divide(
                                IntFraction.intFraction(-1, -4)
                        )
        ).isEqualTo(
                IntFraction.intFraction(2, 1)
        );

        assertThatThrownBy(() ->
                IntFraction.intFraction(0, 2).divide(null)
        ).isInstanceOf(NullPointerException.class);
    }
}
