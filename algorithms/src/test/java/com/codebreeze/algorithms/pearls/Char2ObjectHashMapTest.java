package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.map.Char2ObjectHashMap;
import com.codebreeze.algorithms.primitive.collections.pair.CharObjectPair;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;

class Char2ObjectHashMapTest {

    @Test
    void vanilla_case() {
        // given

        // when
        Char2ObjectHashMap<String> c2im = new Char2ObjectHashMap<>(9); // arbitrary bucket count
        c2im.put('a', "3");
        c2im.put('b', "3");
        c2im.put('c', "7");

        // then
        assertThat(c2im.get('a')).isEqualTo("3");
        assertThat(c2im.get('b')).isEqualTo("3");
        assertThat(c2im.get('c')).isEqualTo("7");
        assertThat(c2im.size()).isEqualTo(3);
        assertThat(c2im.containsKey('a')).isTrue();
        assertThat(c2im.containsKey('b')).isTrue();
        assertThat(c2im.containsKey('c')).isTrue();

        assertThat(c2im.containsKey('d')).isFalse();
        assertThat(c2im.get('d')).isNull();

        // when
        c2im.remove('a');

        // then
        assertThat(c2im.containsKey('a')).isFalse();
        assertThat(c2im.size()).isEqualTo(2);
        assertThat(c2im.get('a')).isNull();
    }

    @Test
    void duplicate() {
        // given

        // when
        Char2ObjectHashMap<String> c2im = new Char2ObjectHashMap<>(9); // arbitrary bucket count
        c2im.put('a', "3");
        c2im.put('a', "4");

        // then
        assertThat(c2im.get('a')).isEqualTo("4");
        assertThat(c2im.size()).isEqualTo(1);
        assertThat(c2im.containsKey('a')).isTrue();
    }

    @Test
    void empty() {
        // given

        // when
        Char2ObjectHashMap<String> c2im = new Char2ObjectHashMap<>(9); // arbitrary bucket count

        // then
        assertThat(c2im.containsKey('d')).isFalse();
        assertThat(c2im.size()).isEqualTo(0);
        assertThat(c2im.get('d')).isNull();
    }

    @Test
    void stream() {
        // given
        Set<CharObjectPair<String>> expectedStreamContent = IntStream.range(0, 999)
            .mapToObj(i -> new CharObjectPair<>((char) i, String.valueOf(i)))
            .collect(toSet());

        // when
        Char2ObjectHashMap<String> c2im = new Char2ObjectHashMap<>(9); // arbitrary bucket count
        IntStream.range(0, 999).forEach(i -> c2im.put((char)i, String.valueOf(i)));

        // then
        Set<CharObjectPair<String>> mapStreamContent = c2im.stream().collect(toSet());

        assertThat(mapStreamContent).isEqualTo(expectedStreamContent);
    }
}
