package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.codebreeze.algorithms.Utils.nanosToRun;
import static com.codebreeze.algorithms.pearls.SelectMFromNWithEqualProbability.Algorithm.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SelectMFromNWithEqualProbabilityTest {

    @Test
    void huge() {
        // given
        int m = 2000;
        final int[] n = IntStream.range(0, 1000000).toArray();
        Map<SelectMFromNWithEqualProbability.Algorithm, Long> algToTime = new HashMap<>();

        // when
        Arrays.stream(SelectMFromNWithEqualProbability.Algorithm.values())
            .filter(algorithm -> !algorithm.equals(ALG1_1)) // recursion isn't good for huge data!!
            .filter(algorithm -> !algorithm.equals(ALG1_2)) // recursion isn't good for huge data!!
            .forEach(algorithm -> {
            SelectMFromNWithEqualProbability smfnwep = new SelectMFromNWithEqualProbability(n, algorithm);
            algToTime.put(
                algorithm,
                nanosToRun(
                    () -> assertThat(smfnwep.apply(m))
                        .hasSize(m)
                )
            );
        });
        algToTime.forEach((a, t) -> {
            System.out.println("algorithm: " + a + ", time: " + t);
        });
    }

    @Test
    void full_length() {
        // given
        final int[] n = {2, 3, 5};

        // when
        Arrays.stream(SelectMFromNWithEqualProbability.Algorithm.values()).forEach(algorithm -> {
            SelectMFromNWithEqualProbability smfnwep1 = new SelectMFromNWithEqualProbability(n, algorithm);
            int[] result = smfnwep1.apply(n.length);

            // then
            System.out.println(Arrays.toString(result));
            assertThat(result)
                .hasSize(3)
                .hasSize(n.length)
                .doesNotHaveDuplicates()
                .doesNotContain(0);
        });
    }

    @Test
    void empty() {
        // given
        final int[] n = {};

        // when
        Arrays.stream(SelectMFromNWithEqualProbability.Algorithm.values()).forEach(algorithm -> {
            SelectMFromNWithEqualProbability smfnwep1 = new SelectMFromNWithEqualProbability(n, algorithm);
            int[] result = smfnwep1.apply(0);

            // then
            assertThat(result).isEmpty();
        });
    }

    @Test
    void failures() {
        assertThatThrownBy(() -> new SelectMFromNWithEqualProbability(null, ALG1))
            .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new SelectMFromNWithEqualProbability(new int[]{1, 2}, null))
            .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new SelectMFromNWithEqualProbability(new int[]{1, 2}, ALG1).apply(-1))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new SelectMFromNWithEqualProbability(new int[]{1, 2}, ALG1).apply(3))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void apply_vanilla_case() {
        final int[] n = {661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809};

        Stream.of(SelectMFromNWithEqualProbability.Algorithm.values())
            .map(algorithm -> new SelectMFromNWithEqualProbability(n, algorithm))
            .forEach(this::apply_vanilla_case_logic);
    }

    void apply_vanilla_case_logic(SelectMFromNWithEqualProbability smfnwep) {
        // given

        // when
        int[] m0 = smfnwep.apply(0);
        int[] m1 = smfnwep.apply(1);
        int[] m2 = smfnwep.apply(2);
        int[] m3 = smfnwep.apply(3);
        int[] m10 = smfnwep.apply(10);

        // then
        Stream.of(m0, m1, m2, m3, m10).map(Arrays::toString).forEach(System.out::println);
        assertThat(m0)
            .hasSize(0);
        assertThat(m1)
            .hasSize(1)
            .doesNotContain(0);
        assertThat(m2)
            .hasSize(2)
            .doesNotHaveDuplicates()
            .doesNotContain(0);
        assertThat(m3)
            .hasSize(3)
            .doesNotHaveDuplicates()
            .doesNotContain(0);
        assertThat(m10)
            .hasSize(10)
            .doesNotHaveDuplicates()
            .doesNotContain(0);
    }
}
