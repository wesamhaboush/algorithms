package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DistinctPowersTest
{

    @Test
    public void calculate() throws Exception
    {
//        System.out.println(DistinctPowers.calculateStoreReduced(2, 11));
//        System.out.println(DistinctPowers.calculateStoreReduced(2, 16)); //20^20
        assertThat(DistinctPowers.calculateStoreReduced(2, 5)).isEqualTo(15);
        assertThat(DistinctPowers.calculateStoreReduced(2, 6)).isEqualTo(23);
        assertThat(DistinctPowers.calculateStoreReduced(2, 8)).isEqualTo(44);
        assertThat(DistinctPowers.calculateStoreReduced(2, 9)).isEqualTo(54);
        assertThat(DistinctPowers.calculateStoreReduced(2, 10)).isEqualTo(69);
        assertThat(DistinctPowers.calculateStoreReduced(2, 11)).isEqualTo(88);
        assertThat(DistinctPowers.calculateStoreReduced(2, 12)).isEqualTo(106);
        assertThat(DistinctPowers.calculateStoreReduced(2, 15)).isEqualTo(177);
        assertThat(DistinctPowers.calculateStoreReduced(2, 16)).isEqualTo(195);
//        assertThat(DistinctPowers.calculate6(2, 20)).isEqualTo(320);
        //                assertThat(DistinctPowers.calculate5(2, 100)).isEqualTo(9182L);//9183
        assertThat(DistinctPowers.calculateStoreReduced(2, 100)).isEqualTo(9183);//9183
    }

    @Test
    public void calculateCounting() throws Exception
    {
//                System.out.println(DistinctPowers.calculateBrute(2, 8));
        assertThat(DistinctPowers.calculateCounting(2, 5)).isEqualTo(15);
        assertThat(DistinctPowers.calculateCounting(2, 6)).isEqualTo(23);
        assertThat(DistinctPowers.calculateCounting(2, 8)).isEqualTo(44);
        assertThat(DistinctPowers.calculateCounting(2, 9)).isEqualTo(54);
        assertThat(DistinctPowers.calculateCounting(2, 10)).isEqualTo(69);
        assertThat(DistinctPowers.calculateCounting(2, 11)).isEqualTo(88);
        assertThat(DistinctPowers.calculateCounting(2, 12)).isEqualTo(106);
        assertThat(DistinctPowers.calculateCounting(2, 15)).isEqualTo(177);
        assertThat(DistinctPowers.calculateCounting(2, 16)).isEqualTo(195);
        assertThat(DistinctPowers.calculateCounting(2, 20)).isEqualTo(324L);
        //                assertThat(DistinctPowers.calculate5(2, 100)).isEqualTo(9182L);//9183
        assertThat(DistinctPowers.calculateCounting(2, 100)).isEqualTo(9183);//9183
    }
}
