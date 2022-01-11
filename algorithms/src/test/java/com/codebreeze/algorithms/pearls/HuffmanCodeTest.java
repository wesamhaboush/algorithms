package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.map.Char2ObjectMap;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HuffmanCodeTest {

    /*
    a            5
    b           9
    c           12
    d           13
    e           16
    f           45
     */
    @Test
    void vanilla_case() {
        // given
        String s = "a".repeat(5) +
            "b".repeat(9) +
            "c".repeat(12) +
            "d".repeat(13) +
            "e".repeat(16) +
            "f".repeat(45);

        // when
        HuffmanCode huffmanCode = new HuffmanCode();
        Char2ObjectMap<String> codes = huffmanCode.apply(s);

        // then
        assertThat(codes.get('a')).isEqualTo("1100");
        assertThat(codes.get('b')).isEqualTo("1101");
        assertThat(codes.get('c')).isEqualTo("100");
        assertThat(codes.get('d')).isEqualTo("101");
        assertThat(codes.get('e')).isEqualTo("111");
        assertThat(codes.get('f')).isEqualTo("0");
    }

    @Test
    void all_same() {
        // given
        String s = "a".repeat(5) + "b".repeat(5) + "c".repeat(5) + "d".repeat(5) + "e".repeat(5) + "f".repeat(5);

        // when
        HuffmanCode huffmanCode = new HuffmanCode();
        Char2ObjectMap<String> codes = huffmanCode.apply(s);

        // then
        assertThat(codes.get('a')).isEqualTo("101");
        assertThat(codes.get('b')).isEqualTo("01");
        assertThat(codes.get('c')).isEqualTo("100");
        assertThat(codes.get('d')).isEqualTo("111");
        assertThat(codes.get('e')).isEqualTo("110");
        assertThat(codes.get('f')).isEqualTo("00");
    }

    @Test
    void one() {
        // given
        String s = "a".repeat(5);

        // when
        HuffmanCode huffmanCode = new HuffmanCode();
        Char2ObjectMap<String> codes = huffmanCode.apply(s);

        // then
        assertThat(codes.get('a')).isEqualTo("");
    }

    @Test
    void two() {
        // given
        String s = "a".repeat(5) + "b".repeat(7);

        // when
        HuffmanCode huffmanCode = new HuffmanCode();
        Char2ObjectMap<String> codes = huffmanCode.apply(s);

        // then
        assertThat(codes.get('a')).isEqualTo("0");
        assertThat(codes.get('b')).isEqualTo("1");
    }

    @Test
    void three() {
        // given
        String s = "a".repeat(5) + "b".repeat(7) + "l";

        // when
        HuffmanCode huffmanCode = new HuffmanCode();
        Char2ObjectMap<String> codes = huffmanCode.apply(s);

        // then
        assertThat(codes.get('a')).isEqualTo("01");
        assertThat(codes.get('b')).isEqualTo("1");
        assertThat(codes.get('l')).isEqualTo("00");
    }

    @Test
    void empty() {
        // given
        String s = "";

        // when
        HuffmanCode huffmanCode = new HuffmanCode();
        Char2ObjectMap<String> codes = huffmanCode.apply(s);

        // then
        assertThat(codes.size()).isEqualTo(0);
    }
}
