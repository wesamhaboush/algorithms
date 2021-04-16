package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ComparablesTest {

    @Test
    public void lessThan() {
        assertThat(Comparables.lessThan(0, 1)).isTrue();
    }
}
