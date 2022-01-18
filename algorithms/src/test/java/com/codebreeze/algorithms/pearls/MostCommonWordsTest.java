package com.codebreeze.algorithms.pearls;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Set;
import java.util.regex.Pattern;

import static java.nio.file.Files.readString;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static java.util.Objects.requireNonNull;
import static java.util.function.Predicate.not;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MostCommonWordsTest {

    @Test
    void apply_vanilla() throws URISyntaxException, IOException {
        apply_vanilla(
            "/The_Whale.txt",
            new String[]{"of", "and", "the", "a", "to", "in", "that", "i", "it", "his"},
            10,
            emptySet()
        );
        apply_vanilla(
            "/Frankenstein.txt",
            new String[]{"of", "and", "the", "a", "to", "in", "that", "i", "was", "my"},
            10,
            emptySet()
        );
        apply_vanilla(
            "/The_Adventures_of_Sherlock_Holmes.txt",
            new String[]{"of", "and", "the", "a", "to", "in", "that", "i", "it", "you"},
            10,
            emptySet()
        );
    }

    @Test
    void apply_vanilla_with_exclusions() throws URISyntaxException, IOException {
        Set<String> exclusions = Set.of(
            "of", "and", "the", "a", "to", "in", "that", "i", "it", "his", "this", "all", "for", "is", "was",
            "as", "he", "but", "with", "be", "so", "on", "not", "him", "from", "at", "by", "now", "have", "one",
            "there", "or", "had", "you", "then", "which", "me", "they", "like", "were", "an", "when", "some", "are",
            "what", "their", "up", "out", "my", "upon", "no", "if", "more", "into", "we", "them", "been", "would",
            "other", "will", "these", "over", "its", "though", "down", "who", "such", "only", "yet", "any", "should",
            "she", "her", "could", "your", "may", "did", "our", "am", "might", "than", "shall", "has", "here", "about",
            "can", "how", "must", "us"
        );
        apply_vanilla(
            "/The_Whale.txt",
            new String[]{
                "long", "head", "boat", "sea", "ye", "ahab", "old", "whale", "man", "ship", "time", "captain", "still",
                "very", "do"
            },
            15,
            exclusions
        );
        apply_vanilla(
            "/Frankenstein.txt",
            new String[]{
                "time", "gutenberg", "eyes", "own", "father", "every", "first", "do", "before", "life", "man", "myself",
                "said", "even", "being"
            },
            15,
            exclusions
        );
        apply_vanilla(
            "/The_Adventures_of_Sherlock_Holmes.txt",
            new String[]{
                "before", "well", "see", "man", "little", "do", "holmes", "said", "very", "mr", "room",
                "think", "time", "come", "know"
            },
            15,
            exclusions
        );
    }

    void apply_vanilla(
        String filepath,
        String[] expectedValues,
        int topN,
        Set<String> exclusions
    ) throws URISyntaxException, IOException {
        // given
        URL resource = requireNonNull(this.getClass().getResource(filepath));
        String content = readString(Path.of(resource.toURI()), StandardCharsets.UTF_8);
        Iterable<String> iterable = () -> Pattern.compile("[^a-zA-Z]")
            .splitAsStream(content)
            .map(String::trim)
            .filter(not(String::isEmpty))
            .filter(not("s"::equals))
            .iterator();

        // when
        MostCommonWords mcw = new MostCommonWords();
        String[] mostCommonWords = mcw.apply(new MostCommonWords.Specs(iterable, topN, exclusions));

        // then
        assertThat(mostCommonWords).containsExactlyInAnyOrder(expectedValues);
    }

    @Test
    void apply_failures() {
        assertThatThrownBy(() -> new MostCommonWords().apply(null))
            .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new MostCommonWords.Specs(null, 1, Set.of()))
            .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new MostCommonWords.Specs(singleton(""), 0, Set.of()))
            .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> new MostCommonWords.Specs(singleton(""), 1, null))
            .isInstanceOf(NullPointerException.class);
    }
}
