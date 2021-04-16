package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NameScoresTest
{
    @Test
    public void calculate() throws Exception
    {
        assertThat(NameScores.calculate()).isEqualTo(871198282L);
    }

}
