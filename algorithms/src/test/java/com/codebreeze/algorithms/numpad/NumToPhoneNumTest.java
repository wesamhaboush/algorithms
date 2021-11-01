package com.codebreeze.algorithms.numpad;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class NumToPhoneNumTest {

    @Test
    void apply() {
        // given
        Map<String, String> directory = getDirectory();

        // when
        NumToPhoneNum numToPhoneNum = new NumToPhoneNum(directory);

        // then
        assertThat(numToPhoneNum.apply("546*6*")).isEqualTo("multiple numbers with that sequence were found: Moran Lin, Ming Kho");
        assertThat(numToPhoneNum.apply("4226874*4*")).isEqualTo("multiple numbers with that sequence were found: Hakeem Haboush, Heidi Haboush");
        assertThat(numToPhoneNum.apply("6677*5*")).isEqualTo("8888888888");
        assertThat(numToPhoneNum.apply("568243*7*")).isEqualTo("9999999999");
        assertThatCode(() -> numToPhoneNum.apply("5586*4*"))
            .hasMessage("entry for the following number not found: 5586*4*")
            .isInstanceOf(RuntimeException.class);
    }

    /*
    ABC 2
    DEF 3
    GHI 4
    JKL 5
    MNO 6
    PQRS 7
    TUV 8
    WXYZ 9
     */
    private Map<String, String> getDirectory() {
        return new HashMap<>() {{
            put("Moran Lin", "1111111111"); // 546*6*
            put("Ming Kho", "2222222222"); // 546*6*
            put("Sam Haboush", "3333333333"); // 4226874*7*
            put("Heidi Haboush", "4444444444"); // 4226874*4*
            put("Hakeem Haboush", "5555555555"); // 4226874*4*
            put("Amanda Holden", "6666666666"); // 465336*2*
            put("Megan Fox", "7777777777"); // 369*6*
            put("Kate Moss", "8888888888"); // 6677*5*
            put("Sarah Louche", "9999999999"); // 568243*7*
            put("Cameron Diaz", "0000000000"); // 3429*2*
//            put("Heidi Klum", "1212121212"); // 5586*4* doesn't exist in dictionary
        }};
    }
}
