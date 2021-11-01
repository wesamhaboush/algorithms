package com.codebreeze.algorithms.numpad;

import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.function.Function;

import static java.util.stream.Collectors.joining;

public class NumToPhoneNum implements Function<String, String> {
    private static final Map<Character, Character> letterToDigit = new HashMap<>() {
        {
            put('A', '2');
            put('B', '2');
            put('C', '2');
            put('D', '3');
            put('E', '3');
            put('F', '3');
            put('G', '4');
            put('H', '4');
            put('I', '4');
            put('J', '5');
            put('K', '5');
            put('L', '5');
            put('M', '6');
            put('N', '6');
            put('O', '6');
            put('P', '7');
            put('Q', '7');
            put('R', '7');
            put('S', '7');
            put('T', '8');
            put('U', '8');
            put('V', '8');
            put('W', '9');
            put('X', '9');
            put('Y', '9');
            put('Z', '9');
        }
    };

    // dialed number to a pair of name and number
    private final Map<String, Set<Pair<String, String>>> dialedNumberToNameAndPhoneNumber = new HashMap<>();

    NumToPhoneNum(Map<String, String> nameToPhoneNumber) {
        // so, if we have in the map the following:
        // Moran Lin 123456789
        // then we get
        // 546*6* -> <Moran Lin, 123456789>
        for (Map.Entry<String, String> entry : nameToPhoneNumber.entrySet()) {
            dialedNumberToNameAndPhoneNumber.computeIfAbsent(
                encode(entry.getKey()),
                encodedName -> new HashSet<>()
            ).add(Pair.of(entry.getKey(), entry.getValue()));
        }
    }

    private String encode(String name) {
        // split around space to separate first name and last name
        String[] firstNameLastName = name.split("\\s+");
        String firstName = firstNameLastName[0];
        String lastName = firstNameLastName[1];

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < lastName.length(); i++) {
            sb.append(letterToDigit.get(lastName.toUpperCase(Locale.ENGLISH).charAt(i)));
        }

        sb.append('*');
        sb.append(letterToDigit.get(firstName.toUpperCase(Locale.ENGLISH).charAt(0)));
        sb.append('*');

        return sb.toString();
    }

    @Override
    public String apply(String dialedNumber) {
        if (dialedNumberToNameAndPhoneNumber.containsKey(dialedNumber)) {
            Set<Pair<String, String>> entriesWithThisNumberSequence = dialedNumberToNameAndPhoneNumber.get(dialedNumber);
            if (entriesWithThisNumberSequence.size() == 1) {
                return entriesWithThisNumberSequence.iterator().next().getValue();
            } else {
                return "multiple numbers with that sequence were found: " + entriesWithThisNumberSequence.stream()
                    .map(Pair::getKey)
                    .collect(joining(", "));
            }
        } else {
            throw new RuntimeException("entry for the following number not found: " + dialedNumber);
        }
    }
}
