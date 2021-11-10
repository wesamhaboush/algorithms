package com.codebreeze.algorithms;

import java.util.ArrayList;
import java.util.List;

public class CHyphenation {
    private static final String[] hyphenation_suffixes = {
        "etic",
        "alistic",
        "stic",
        "ptic",
        "lytic",
        "otic",
        "antic",
        "ntic",
        "ctic",
        "atic",
        "hnic",
        "nic",
        "mic",
        "llic",
        "blic",
        "clic",
        "lic",
        "hic",
        "fic",
        "dic",
        "bic",
        "aic",
        "mac",
        "iac"
    };

    private static final String[] hyphenation_patterns = {
        "et-ic",
        "al-is-tic",
        "s-tic",
        "p-tic",
        "-lyt-ic",
        "ot-ic",
        "an-tic",
        "n-tic",
        "c-tic",
        "at-ic",
        "h-nic",
        "n-ic",
        "m-ic",
        "l-lic",
        "b-lic",
        "-clic",
        "l-ic",
        "h-ic",
        "f-ic",
        "d-ic",
        "-bic",
        "a-ic",
        "-mac",
        "i-ac"
    };

    public static String[] hyphenations(String word) {
        List<String> hyphenations = new ArrayList<>();
        for(int i = 0; i < hyphenation_suffixes.length; i++) {
            if(word.endsWith(hyphenation_suffixes[i])) {
                hyphenations.add(hyphenation_patterns[i]);
            }
        }
        return hyphenations.toArray(String[]::new);
    }
}
