package com.codebreeze.algorithms;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IsPreOrderTraversalOfBstTest
{
    @Test
    public void of() throws Exception
    {
        assertThat(IsPreOrderTraversalOfBst.of(new int[]{})).isTrue();
        assertThat(IsPreOrderTraversalOfBst.of(new int[]{1})).isTrue();
        System.out.println("----");
        assertThat(IsPreOrderTraversalOfBst.of(new int[]{1, 2})).isTrue();
        System.out.println("----");
        assertThat(IsPreOrderTraversalOfBst.of(new int[]{1, 2, 3})).isTrue();
        System.out.println("----");
        assertThat(IsPreOrderTraversalOfBst.of(new int[]{3, 2, 1})).isTrue();
        System.out.println("----");
        assertThat(IsPreOrderTraversalOfBst.of(new int[]{2, 3, 1})).isFalse();
        System.out.println("----");
        assertThat(IsPreOrderTraversalOfBst.of(new int[]{40, 30, 35, 80, 100})).isTrue();
        System.out.println("----");
        assertThat(IsPreOrderTraversalOfBst.of(new int[]{40, 30, 35, 20, 80, 100})).isFalse();

    }

}
