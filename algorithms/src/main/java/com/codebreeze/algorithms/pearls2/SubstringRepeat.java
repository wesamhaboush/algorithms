package com.codebreeze.algorithms.pearls2;

import java.util.function.Predicate;

public class SubstringRepeat implements Predicate<String> {

    @Override
    public boolean test(String s) {
        for (int length = 1; length <= s.length() / 2; length++) {
            if (isRepeat(length, s)) {
                return true;
            }
        }
        return false;
    }

    private boolean isRepeat(int length, String s) {
        for (int i = length; i < s.length(); i++) {
            if (s.charAt(i) != s.charAt(i - length)) {
                return false;
            }
        }
        return true;
    }
}
