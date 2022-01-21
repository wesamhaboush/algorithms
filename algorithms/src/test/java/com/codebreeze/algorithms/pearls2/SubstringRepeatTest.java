package com.codebreeze.algorithms.pearls2;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SubstringRepeatTest {

    @Test
    void test1() {
        // given
        // when
        SubstringRepeat substringRepeat = new SubstringRepeat();

        // then
        assertThat(substringRepeat.test("aa")).isTrue();
        assertThat(substringRepeat.test("ab")).isFalse();
        assertThat(substringRepeat.test("abc")).isFalse();
        assertThat(substringRepeat.test("abca")).isFalse();
        assertThat(substringRepeat.test("ababab")).isTrue();
        assertThat(substringRepeat.test("abcabcabc")).isTrue();
        assertThat(substringRepeat.test("abcabcabcd")).isFalse();
        assertThat(substringRepeat.test("aaaaaaaaaaaaaaaaaaaad")).isFalse();
        assertThat(substringRepeat.test("a")).isFalse();
    }
}