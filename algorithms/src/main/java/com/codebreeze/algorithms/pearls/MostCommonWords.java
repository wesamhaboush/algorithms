package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.heap.Heap;
import com.codebreeze.algorithms.primitive.collections.heap.SimpleHeap;

import java.util.*;
import java.util.function.Function;

import static java.util.Comparator.comparing;
import static java.util.Objects.requireNonNull;

public class MostCommonWords implements Function<MostCommonWords.Specs, String[]> {
    private static final Comparator<CountedWord> c = comparing(CountedWord::count);

    @Override
    public String[] apply(Specs specs) {
        requireNonNull(specs, "cannot operate with null specs");

        Map<String, CountedWord> counts = new TreeMap<>();

        for (String word : specs.iterable()) {
            String normalized = word.toLowerCase(Locale.US);
            if(!specs.exclusions().contains(normalized)) {
                counts.computeIfAbsent(normalized, CountedWord::new).count++;
            }
        }

//        List<CountedWord> mostCommonWords = new ArrayList<>(counts.values());
//        mostCommonWords.sort(c.reversed());
//
//        return mostCommonWords.stream().limit(specs.topN()).map(CountedWord::word).toArray(String[]::new);

        Heap<CountedWord> mostCommonWords = new SimpleHeap<>(false, c);
        counts.values().forEach(cw -> {
            if(mostCommonWords.size() < specs.topN()) {
                mostCommonWords.insert(cw);
            } else {
                if(cw.count > mostCommonWords.peek().count) {
                    mostCommonWords.extractM();
                    mostCommonWords.insert(cw);
                }
            }
        });
        return mostCommonWords.stream()
            .map(CountedWord::word)
            .toArray(String[]::new);
    }

    public static record Specs(Iterable<String> iterable, int topN, Set<String> exclusions) {
        public Specs {
            requireNonNull(iterable, "cannot operate with null iterable");
            requireNonNull(exclusions, "cannot operate with null exclusions list");
            if (topN < 1) {
                throw new IllegalArgumentException("cannot operate with topN less than 1, but was: " + topN);
            }
        }
    }

    private static class CountedWord {
        private int count;
        private final String word;

        private CountedWord(String word) {
            this.count = 0;
            this.word = requireNonNull(word, "null words are non acceptable");
        }

        public int count() {
            return count;
        }

        public String word() {
            return word;
        }
    }
}
