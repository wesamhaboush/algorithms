package com.codebreeze.algorithms.primitive.collections.list;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class ArrayIntListTest {

    @Test
    void vanilla_case() {
        // given

        // when
        ArrayIntList sil = new ArrayIntList(10);

        // then
        assertThat(sil.isEmpty()).isTrue();
        assertThat(sil.size()).isEqualTo(0);
        assertThat(sil.contains(55)).isFalse();

        // when
        IntStream.range(0, 100).forEach(sil::add);

        // then
        assertThat(sil.size()).isEqualTo(100);
        assertThat(sil.contains(55)).isTrue();
        assertThat(sil.contains(550)).isFalse();
        assertThat(sil.isEmpty()).isFalse();
        assertThat(sil.stream().count()).isEqualTo(100);

        // when
        sil.remove(55);

        // then
        assertThat(sil.contains(55)).isFalse();
        assertThat(sil.size()).isEqualTo(99);
        assertThat(sil.stream().count()).isEqualTo(99);

        // when
        sil.removeAt(2);

        // then
        assertThat(sil.contains(2)).isFalse();
        assertThat(sil.size()).isEqualTo(98);
        assertThat(sil.stream().count()).isEqualTo(98);
        assertThat(sil.get(2)).isEqualTo(3);

        // when
        sil.set(2, 590);

        // then
        assertThat(sil.get(2)).isEqualTo(590);
    }

    @Test
    void duplicates() {
        // given

        // when
        ArrayIntList sil = new ArrayIntList(10);
        sil.add(2);
        sil.add(3);
        sil.add(2);
        sil.add(3);

        // then
        assertThat(sil.isEmpty()).isFalse();
        assertThat(sil.size()).isEqualTo(4);
        assertThat(sil.contains(2)).isTrue();
        assertThat(sil.contains(3)).isTrue();

        // when
        sil.remove(2);

        // then
        assertThat(sil.isEmpty()).isFalse();
        assertThat(sil.size()).isEqualTo(2);
        assertThat(sil.contains(2)).isFalse();
        assertThat(sil.contains(3)).isTrue();

        // when
        sil.removeAt(1);

        // then
        assertThat(sil.isEmpty()).isFalse();
        assertThat(sil.size()).isEqualTo(1);
        assertThat(sil.contains(2)).isFalse();
        assertThat(sil.contains(3)).isTrue();
    }

    @Test
    void failures() {
        // given

        // when
        ArrayIntList sil = new ArrayIntList(10);
        sil.add(1);
        sil.add(5);

        // then
        assertThatThrownBy(() -> sil.removeAt(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> sil.removeAt(2)).isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(() -> sil.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> sil.get(2)).isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(() -> sil.set(2, 88)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> sil.set(-2, 88)).isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(() -> new ArrayIntList(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatCode(ArrayIntList::new).doesNotThrowAnyException();
    }
}
