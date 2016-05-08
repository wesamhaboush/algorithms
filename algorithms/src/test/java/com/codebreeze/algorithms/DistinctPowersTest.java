package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DistinctPowersTest
{

    @Test
    public void calculate() throws Exception
    {
//        System.out.println(DistinctPowers.calculate(2, 11));
//        System.out.println(DistinctPowers.calculate(2, 16)); //20^20
        assertThat(DistinctPowers.calculate(2, 5)).isEqualTo(15);
        assertThat(DistinctPowers.calculate(2, 6)).isEqualTo(23);
        assertThat(DistinctPowers.calculate(2, 8)).isEqualTo(44);
        assertThat(DistinctPowers.calculate(2, 9)).isEqualTo(54);
        assertThat(DistinctPowers.calculate(2, 10)).isEqualTo(69);
        assertThat(DistinctPowers.calculate(2, 11)).isEqualTo(88);
        assertThat(DistinctPowers.calculate(2, 12)).isEqualTo(106);
        assertThat(DistinctPowers.calculate(2, 15)).isEqualTo(177);
        assertThat(DistinctPowers.calculate(2, 16)).isEqualTo(195);
//        assertThat(DistinctPowers.calculate6(2, 20)).isEqualTo(320);
        //                assertThat(DistinctPowers.calculate5(2, 100)).isEqualTo(9182L);//9183
        assertThat(DistinctPowers.calculate(2, 100)).isEqualTo(9183);//9183
    }


}
