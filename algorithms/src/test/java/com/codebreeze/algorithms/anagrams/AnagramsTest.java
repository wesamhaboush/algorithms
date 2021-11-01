package com.codebreeze.algorithms.anagrams;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.codebreeze.algorithms.Utils.getFile;
import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class AnagramsTest {

    @Test
    void finds_anagrams_for_multiples_only() {
        // given
        Set<Set<String>> expectedAnagrams = Set.of(
            Set.of("deposit", "posited"),
            Set.of("cool", "loco", "colo")
        );

        // when
        Set<Set<String>> anagrams = Anagrams.from(
            Set.of(
                "deposit",
                "posited",
                "posted",
                "cool",
                "colo",
                "loco",
                "pool"
            )
        );

        // then
        assertThat(anagrams).isEqualTo(expectedAnagrams);
    }

    @Test
    void should_work_for_empty() {
        // given

        // when
        Set<Set<String>> anagrams = Anagrams.from(Set.of());

        // then
        assertThat(anagrams).isEmpty();
    }

    @Test
    void finds_anagrams_for_the_dictionary() throws IOException {
        // given
        List<String> expectedAnagrams = Files.readAllLines(
            getFile("anagrams-words-result.txt").toPath()
        );

        // when
        Set<Set<String>> anagrams = Anagrams.from(
            new HashSet<>(
                Files.readAllLines(
                    getFile("words").toPath()
                )
            )
        );

        List<String> output = anagrams.stream()
            .map(ArrayList::new)
            .map(item -> {
                item.sort(String::compareTo);
                return item.toString();
            })
            .sorted()
            .collect(toList());

        // then
        assertThat(output).isEqualTo(expectedAnagrams);
    }
}
