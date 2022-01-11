package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.map.Char2IntHashMap;
import com.codebreeze.algorithms.primitive.collections.map.Char2IntMap;
import com.codebreeze.algorithms.primitive.collections.pair.CharIntPair;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toSet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class Char2IntHashMapTest {

    @Test
    void vanilla_case() {
        // given

        // when
        Char2IntMap c2im = new Char2IntHashMap(9); // arbitrary bucket count
        c2im.put('a', 3);
        c2im.put('b', 3);
        c2im.put('c', 7);

        // then
        assertThat(c2im.get('a')).isEqualTo(3);
        assertThat(c2im.get('b')).isEqualTo(3);
        assertThat(c2im.get('c')).isEqualTo(7);
        assertThat(c2im.size()).isEqualTo(3);
        assertThat(c2im.containsKey('a')).isTrue();
        assertThat(c2im.containsKey('b')).isTrue();
        assertThat(c2im.containsKey('c')).isTrue();

        assertThat(c2im.containsKey('d')).isFalse();
        assertThatThrownBy(() -> c2im.get('d')).isInstanceOf(NoSuchElementException.class);

        // when
        c2im.remove('a');

        // then
        assertThat(c2im.containsKey('a')).isFalse();
        assertThat(c2im.size()).isEqualTo(2);
        assertThatThrownBy(() -> c2im.get('a')).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void duplicate() {
        // given

        // when
        Char2IntMap c2im = new Char2IntHashMap(9); // arbitrary bucket count
        c2im.put('a', 3);
        c2im.put('a', 4);

        // then
        assertThat(c2im.get('a')).isEqualTo(4);
        assertThat(c2im.size()).isEqualTo(1);
        assertThat(c2im.containsKey('a')).isTrue();
    }

    @Test
    void empty() {
        // given

        // when
        Char2IntMap c2im = new Char2IntHashMap(9); // arbitrary bucket count

        // then
        assertThat(c2im.containsKey('d')).isFalse();
        assertThat(c2im.size()).isEqualTo(0);
        assertThatThrownBy(() -> c2im.get('d')).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    void stream() {
        // given
        Set<CharIntPair> expectedStreamContent = IntStream.range(0, 999)
            .mapToObj(i -> new CharIntPair((char)i, i))
            .collect(toSet());

        // when
        Char2IntMap c2im = new Char2IntHashMap(9); // arbitrary bucket count
        IntStream.range(0, 999).forEach(i -> c2im.put((char)i, i));

        // then
        Set<CharIntPair> mapStreamContent = c2im.stream().collect(toSet());

        assertThat(mapStreamContent).isEqualTo(expectedStreamContent);
    }
}
