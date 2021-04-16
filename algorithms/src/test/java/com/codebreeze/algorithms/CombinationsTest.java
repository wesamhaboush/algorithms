package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class CombinationsTest
{
    @Test
    public void of() {
        assertThat(Combinations.of(asList(0, 1))).containsExactlyInAnyOrder(
                asList(),
                asList(0),
                asList(1),
                asList(0, 1));

        assertThat(Combinations.of(1, asList(0, 1))).containsExactlyInAnyOrder(
                asList(0),
                asList(1));
        assertThat(Combinations.of(2, asList(0, 1))).containsExactlyInAnyOrder(
                asList(0, 1));
        assertThat(Combinations.of(3, asList(0, 1))).isEmpty();
    }

}
