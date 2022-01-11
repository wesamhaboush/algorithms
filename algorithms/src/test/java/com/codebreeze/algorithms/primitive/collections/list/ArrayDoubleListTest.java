package com.codebreeze.algorithms.primitive.collections.list;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

class ArrayDoubleListTest {

    @Test
    void vanilla_case() {
        // given

        // when
        ArrayDoubleList sdl = new ArrayDoubleList(10);

        // then
        assertThat(sdl.isEmpty()).isTrue();
        assertThat(sdl.size()).isEqualTo(0);
        assertThat(sdl.contains(55)).isFalse();

        // when
        IntStream.range(0, 100).forEach(sdl::add);

        // then
        assertThat(sdl.size()).isEqualTo(100);
        assertThat(sdl.contains(55)).isTrue();
        assertThat(sdl.contains(550)).isFalse();
        assertThat(sdl.isEmpty()).isFalse();
        assertThat(sdl.stream().count()).isEqualTo(100);

        // when
        sdl.remove(55);

        // then
        assertThat(sdl.contains(55)).isFalse();
        assertThat(sdl.size()).isEqualTo(99);
        assertThat(sdl.stream().count()).isEqualTo(99);

        // when
        sdl.removeAt(2);

        // then
        assertThat(sdl.contains(2)).isFalse();
        assertThat(sdl.size()).isEqualTo(98);
        assertThat(sdl.stream().count()).isEqualTo(98);
        assertThat(sdl.get(2)).isEqualTo(3);

        // when
        sdl.set(2, 590);

        // then
        assertThat(sdl.get(2)).isEqualTo(590);
    }

    @Test
    void duplicates() {
        // given

        // when
        ArrayDoubleList sdl = new ArrayDoubleList(10);
        sdl.add(2);
        sdl.add(3);
        sdl.add(2);
        sdl.add(3);

        // then
        assertThat(sdl.isEmpty()).isFalse();
        assertThat(sdl.size()).isEqualTo(4);
        assertThat(sdl.contains(2)).isTrue();
        assertThat(sdl.contains(3)).isTrue();

        // when
        sdl.remove(2);

        // then
        assertThat(sdl.isEmpty()).isFalse();
        assertThat(sdl.size()).isEqualTo(2);
        assertThat(sdl.contains(2)).isFalse();
        assertThat(sdl.contains(3)).isTrue();

        // when
        sdl.removeAt(1);

        // then
        assertThat(sdl.isEmpty()).isFalse();
        assertThat(sdl.size()).isEqualTo(1);
        assertThat(sdl.contains(2)).isFalse();
        assertThat(sdl.contains(3)).isTrue();
    }

    @Test
    void failures() {
        // given

        // when
        ArrayDoubleList sdl = new ArrayDoubleList(10);
        sdl.add(1);
        sdl.add(5);

        // then
        assertThatThrownBy(() -> sdl.removeAt(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> sdl.removeAt(2)).isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(() -> sdl.get(-1)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> sdl.get(2)).isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(() -> sdl.set(2, 88)).isInstanceOf(IndexOutOfBoundsException.class);
        assertThatThrownBy(() -> sdl.set(-2, 88)).isInstanceOf(IndexOutOfBoundsException.class);

        assertThatThrownBy(() -> new ArrayDoubleList(-1)).isInstanceOf(IllegalArgumentException.class);
        assertThatCode(ArrayDoubleList::new).doesNotThrowAnyException();
    }
}
