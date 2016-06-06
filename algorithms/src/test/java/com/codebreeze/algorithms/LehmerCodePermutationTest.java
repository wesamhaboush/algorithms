package com.codebreeze.algorithms;

import org.junit.Test;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class LehmerCodePermutationTest {
    @Test
    public void permutationToNumber() throws Exception
    {
        System.out.println(LehmerCodePermutation.permutationToNumber(new int[]{0, 2, 1}));
        System.out.println(LehmerCodePermutation.permutationToNumber(new int[]{1, 0, 2}));
        System.out.println(LehmerCodePermutation.permutationToNumber(new int[]{2, 0, 1}));
        System.out.println(LehmerCodePermutation.permutationToNumber(new int[]{0, 1, 2}));
        System.out.println(LehmerCodePermutation.permutationToNumber(new int[]{1, 2, 0}));
        System.out.println(LehmerCodePermutation.permutationToNumber(new int[]{2, 1, 0}));
    }

    @Test
    public void of() throws Exception
    {
        assertThat(LehmerCodePermutation.of(asList(1, 2, 3)))
                .isEqualTo(
                        asList(
                                asList(1, 3, 2),
                                asList(2, 1, 3),
                                asList(3, 1, 2),
                                asList(1, 2, 3),
                                asList(2, 3, 1),
                                asList(3, 2, 1)
                        )
                );
        assertThat(LehmerCodePermutation.of(asList(1, 2, 3, 4)))
                .containsOnly(
                                asList(1, 2, 3, 4),
                                asList(1, 2, 4, 3),
                                asList(1, 3, 2, 4),
                                asList(1, 3, 4, 2),
                                asList(1, 4, 3, 2),
                                asList(1, 4, 2, 3),
                                asList(2, 1, 3, 4),
                                asList(2, 1, 4, 3),
                                asList(2, 3, 1, 4),
                                asList(2, 3, 4, 1),
                                asList(2, 4, 1, 3),
                                asList(2, 4, 3, 1),
                                asList(3, 1, 2, 4),
                                asList(3, 1, 4, 2),
                                asList(3, 2, 1, 4),
                                asList(3, 2, 4, 1),
                                asList(3, 4, 1, 2),
                                asList(3, 4, 2, 1),
                                asList(4, 1, 2, 3),
                                asList(4, 1, 3, 2),
                                asList(4, 2, 1, 3),
                                asList(4, 2, 3, 1),
                                asList(4, 3, 1, 2),
                                asList(4, 3, 2, 1)
                          );
        System.out.println(LehmerCodePermutation.of(asList(1, 2, 3)));
    }

    @Test
    public void of2() throws Exception
    {
        assertThat(LehmerCodePermutation.permToNumber(new int[]{1, 2, 3}, 0 , 3))
                .isEqualTo(4);
        assertThat(LehmerCodePermutation.permToNumber(new int[]{1, 3, 2}, 0 , 3))
                .isEqualTo(7);
        assertThat(LehmerCodePermutation.permToNumber(new int[]{2, 1, 3}, 0 , 3))
                .isEqualTo(5);
        assertThat(LehmerCodePermutation.permToNumber(new int[]{2, 3, 1}, 0 , 3))
                .isEqualTo(8);
        assertThat(LehmerCodePermutation.permToNumber(new int[]{3, 1, 2}, 0 , 3))
                .isEqualTo(6);
        assertThat(LehmerCodePermutation.permToNumber(new int[]{3, 2, 1}, 0 , 3))
                .isEqualTo(9);
        assertThat(LehmerCodePermutation.numberToPerm(4, 3))
                .isEqualTo(new int[]{1, 2, 0});
        assertThat(LehmerCodePermutation.numberToPerm(5, 3))
                .isEqualTo(new int[]{2, 1, 0});
        assertThat(LehmerCodePermutation.numberToPerm(6, 3))
                .isEqualTo(new int[]{0, 1, 1});
        assertThat(LehmerCodePermutation.numberToPerm(7, 3))
                .isEqualTo(new int[]{1, 0, 0});
        assertThat(LehmerCodePermutation.numberToPerm(8, 3))
                .isEqualTo(new int[]{2, 0, 0});
        assertThat(LehmerCodePermutation.numberToPerm(9, 3))
                .isEqualTo(new int[]{0, 2, 1});
        //        System.out.println(LehmerCodePermutation.of(asList(1, 2, 3)));
    }
}
