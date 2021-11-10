package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class AddVToXTest {

    @Test
    void add() {
        // given
        // when
        AddVToX addVToX = new AddVToX(10);
        // 0, 1, 2 , 3 , 4 , 5 , 6 , 7  , 8, 9
        addVToX.add(2, 0, 3);   // 2, 2, 2 , 2 , 0 , 0 , 0 , 0  , 0, 0
        addVToX.add(15, 2, 6);  // 2, 2, 17, 17, 15, 15, 15, 0  , 0, 0
        addVToX.add(7, 9, 9);   // 2, 2, 17, 17, 15, 15, 15, 0  , 0, 7
        addVToX.add(-16, 6, 7); // 2, 2, 17, 17, 15, 15, -1, -16, 0, 7

        // then
        assertThat(addVToX.getX()).containsExactly(2, 2, 17, 17, 15, 15, -1, -16, 0, 7);
        assertThatCode(() -> new AddVToX(0)).doesNotThrowAnyException();
        assertThat(new AddVToX(0).getX()).isEmpty(); // should be able to get empty array from this
    }

    @Test
    void add_failures() {
        // given
        // when
        // then
        assertThatThrownBy(() -> new AddVToX(-1)) // negative array size!
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new AddVToX(7).add(1, 3, 2)) // l > u
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new AddVToX(4).add(1, 3, 7)) // u > N
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new AddVToX(4).add(1, -1, 2)) // l < 0
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new AddVToX(0).add(1, 0, 0)) // you cannot add to zerlo length arrays!
            .isInstanceOf(IllegalArgumentException.class);
    }
}
