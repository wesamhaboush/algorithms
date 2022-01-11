package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.iterator.IntIterator;
import com.codebreeze.algorithms.primitive.collections.set.SimpleIntSet;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleIntSetTest {

    @Test
    void eliminates_duplicates() {
        // given
        int[] elements = {1, 2, 3, 2, 1};

        // when
        SimpleIntSet set = new SimpleIntSet();
        for (int i : elements) {
            set.add(i);
        }

        // then
        assertThat(set.toArray(null)).containsExactlyInAnyOrder(1, 2, 3);
        assertThat(set.toArray()).containsExactlyInAnyOrder(1, 2, 3);
    }

    @Test
    void can_go_large() {
        // given
        // when
        SimpleIntSet set = new SimpleIntSet();
        IntStream.range(0, 10000).forEach(set::add);

        // then
        assertThat(set.size()).isEqualTo(10000);
    }

    @Test
    void removes_elements() {
        // given
        // when
        SimpleIntSet set = new SimpleIntSet(new int[]{3, 2, 1});
        set.remove(2);

        // then
        assertThat(set.toArray(null)).containsExactlyInAnyOrder(1, 3);
        assertThat(set.contains(1)).isTrue();
        assertThat(set.contains(2)).isFalse();
        assertThat(set.size()).isEqualTo(2);
        set.clear(); // when
        assertThat(set.size()).isEqualTo(0);
    }

    @Test
    void iterator_captures_all_elements() {
        // given
        int[] result = new int[3];

        // when
        SimpleIntSet set = new SimpleIntSet(new int[]{3, 2, 1});
        IntIterator it = set.iterator();

        // then
        assertThat(it.hasNext()).isTrue();
        result[0] = it.next();
        assertThat(it.hasNext()).isTrue();
        result[1] = it.next();
        assertThat(it.hasNext()).isTrue();
        result[2] = it.next();
        assertThat(it.hasNext()).isFalse();
        assertThat(result).containsExactlyInAnyOrder(result);
    }
}
