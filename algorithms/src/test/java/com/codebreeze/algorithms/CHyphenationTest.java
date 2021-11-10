package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CHyphenationTest {

    @Test
    void hyphenations() {
        // given/when/then
        assertThat(CHyphenation.hyphenations("realistic")).containsExactly("al-is-tic", "s-tic");
        assertThat(CHyphenation.hyphenations("ethnic")).containsExactly("h-nic", "n-ic");
        assertThat(CHyphenation.hyphenations("clinic")).containsExactly("n-ic");
        assertThat(CHyphenation.hyphenations("periodic")).containsExactly("d-ic");
        assertThat(CHyphenation.hyphenations("gigantic")).containsExactly("an-tic", "n-tic");
    }
}
