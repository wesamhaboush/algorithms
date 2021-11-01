package com.codebreeze.algorithms;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static com.codebreeze.algorithms.Utils.getFile;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class BannerTest {

    @Test
    void apply() throws IOException {
        // given
        List<String> expectedLines = Files.readAllLines(getFile("banner/ascii-art-letters.txt").toPath())
            .stream()
            .filter(s -> !s.trim().isEmpty()) // remove empty lines
            .collect(toList());

        // when
        Banner banner = new Banner();
        List<String> actualLines = new ArrayList<>();

        for (int i = 0; i < 26; i++) {
            actualLines.add("\n");
            char[][] asciiArt = banner.apply((char) ('A' + i));
            for (char[] line : asciiArt) {
                actualLines.add(String.valueOf(line));
            }
        }

        actualLines = actualLines.stream()
            .filter(s -> !s.trim().isEmpty()) // remove empty lines
            .collect(toList());

        // then
        assertThat(expectedLines).isEqualTo(actualLines);
    }
}
