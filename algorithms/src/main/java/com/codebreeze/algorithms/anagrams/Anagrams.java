package com.codebreeze.algorithms.anagrams;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.*;

public class Anagrams {
    public static Set<Set<String>> from(Set<String> words) {
        Validate.notNull(words, "can not process null set");
        Map<String, Set<String>> groupedAnagrams = words.stream()
            .map(word -> {
                char[] characters = word.toCharArray();
                // sort horizontally
                Arrays.sort(characters);
                // the hash is the set of chars ordered
                // (sorted word hash, word)
                return Pair.of(String.valueOf(characters), word);
            }).collect(
                groupingBy(
                    // hash is on left
                    Pair::getLeft,
                    // word is on right
                    mapping(Pair::getRight, toSet())
                )
            );
        return groupedAnagrams.values()
            .stream()
            // ignore words that have no anagrams
            .filter(set -> set.size() > 1)
            .collect(toSet());
    }
}
