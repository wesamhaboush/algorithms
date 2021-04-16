package com.codebreeze.algorithms;



import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class FactoradicPermutationTest
{
    @Test
    public void of() throws Exception
    {
        assertThat(toList(FactoradicPermutation.of(asList(1, 2, 3))))
                .isEqualTo(asList(
                        asList(1,2,3),
                        asList(1,3,2),
                        asList(2,1,3),
                        asList(2,3,1),
                        asList(3,1,2),
                        asList(3,2,1)
                                 ));
    }

    private static <T> List<T> toList(final Iterator<T> iterator)
    {
        final List<T> list = new LinkedList<>();
        iterator.forEachRemaining(list::add);
        return list;
    }

    @Test
    public void lehmerCodeToPermutation() throws Exception
    {
        assertThat(FactoradicPermutation.lehmerCodeToPermutation(new int[]{0, 0, 0}, 3)).isEqualTo(new int[]{0, 1, 2});
        assertThat(FactoradicPermutation.lehmerCodeToPermutation(new int[]{0, 1, 0}, 3)).isEqualTo(new int[]{0, 2, 1});
        assertThat(FactoradicPermutation.lehmerCodeToPermutation(new int[]{1, 0, 0}, 3)).isEqualTo(new int[]{1, 0, 2});
        assertThat(FactoradicPermutation.lehmerCodeToPermutation(new int[]{1, 1, 0}, 3)).isEqualTo(new int[]{1, 2, 0});
        assertThat(FactoradicPermutation.lehmerCodeToPermutation(new int[]{2, 0, 0}, 3)).isEqualTo(new int[]{2, 0, 1});
        assertThat(FactoradicPermutation.lehmerCodeToPermutation(new int[]{2, 1, 0}, 3)).isEqualTo(new int[]{2, 1, 0});
        assertThat(FactoradicPermutation.lehmerCodeToPermutation(new int[]{0, 0, 1, 0}, 4)).isEqualTo(new int[]{0, 1, 3,
                                                                                                                2});
        assertThat(FactoradicPermutation.lehmerCodeToPermutation(new int[]{4, 4, 2, 0, 1, 0}, 6)).isEqualTo(new int[]{4,
                                                                                                                      5,
                                                                                                                      2,
                                                                                                                      0,
                                                                                                                      3,
                                                                                                                      1});
        assertThat(FactoradicPermutation.lehmerCodeToPermutation(new int[]{0, 0, 0, 0}, 4)).isEqualTo(new int[]{0, 1, 2,
                                                                                                                3});
    }

    @Test
    public void numberToLehmerCode() throws Exception
    {
        assertThat(FactoradicPermutation.factoradicToLehmerCode(0, 3)).isEqualTo(new int[]{0, 0, 0});
        assertThat(FactoradicPermutation.factoradicToLehmerCode(10, 3)).isEqualTo(new int[]{0, 1, 0});
        assertThat(FactoradicPermutation.factoradicToLehmerCode(10, 4)).isEqualTo(new int[]{0, 0, 1, 0});
        assertThat(FactoradicPermutation.factoradicToLehmerCode(110, 5)).isEqualTo(new int[]{0, 0, 1, 1, 0});
    }

    @Test
    public void digitsToNumber() throws Exception
    {
        assertThat(FactoradicPermutation.digitsToNumber(new int[]{1, 2, 3})).isEqualTo(123);
    }

    @Test
    public void numberToDigits() throws Exception
    {
        assertThat(FactoradicPermutation.numberToDigits(123)).isEqualTo(new int[]{1, 2, 3});
    }

    @Test
    public void numberToFactoradic() throws Exception
    {
        assertThat(FactoradicPermutation.numberToFactoradic(0)).isEqualTo(0);
        assertThat(FactoradicPermutation.numberToFactoradic(1)).isEqualTo(10);
        assertThat(FactoradicPermutation.numberToFactoradic(2)).isEqualTo(100);
        assertThat(FactoradicPermutation.numberToFactoradic(3)).isEqualTo(110);
        assertThat(FactoradicPermutation.numberToFactoradic(4)).isEqualTo(200);
        assertThat(FactoradicPermutation.numberToFactoradic(5)).isEqualTo(210);
        assertThat(FactoradicPermutation.numberToFactoradic(463)).isEqualTo(341010);
    }

}
