package com.codebreeze.algorithms;

import java.util.function.Function;

import static java.lang.Math.max;

public class Banner implements Function<Character, char[][]> {
    private static final String[][] ENCODED_LETTERS = {
        { // A
            "1l 3b 1x",
            "1l 2b 1x 1b 1x",
            "1l 1b 1x 3b 1x",
            "1l 1x 5b 1x",
            "1l 7x",
            "2l 1x 5b 1x"
        },
        { // B
            "1l 6x",
            "2l 1x 5b 1x",
            "1l 6x",
            "2l 1x 5b 1x",
            "1l 6x"
        },
        { // C
            "1l 1b 5x",
            "1l 1x 5b 1x",
            "3l 1x",
            "1l 1x 5b 1x",
            "1l 1b 5x"
        },
        { // D
            "1l 6x",
            "5l 1x 5b 1x",
            "1l 6x"
        },
        { // E
            "1l 7x",
            "2l 1x",
            "1l 5x",
            "2l 1x",
            "1l 7x"
        },
        { // F
            "1l 7x",
            "2l 1x",
            "1l 5x",
            "3l 1x"
        },
        { // G
            "1l 1b 5x",
            "1l 1x 5b 1x",
            "1l 1x",
            "1l 1x 2b 4x",
            "2l 1x 5b 1x",
            "1l 1b 5x"
        },
        { // H
            "3l 1x 5b 1x",
            "1l 7x",
            "3l 1x 5b 1x"
        },
        { // I
            "1l 3x",
            "5l 1b 1x",
            "1l 3x"
        },
        { // J
            "4l 6b 1x",
            "2l 1x 5b 1x",
            "1l 1b 5x"
        },
        { // K
            "1l 1x 4b 1x",
            "1l 1x 3b 1x",
            "1l 1x 2b 1x",
            "1l 3x",
            "1l 1x 2b 1x",
            "1l 1x 3b 1x",
            "1l 1x 4b 1x",
        },
        { // L
            "6l 1x",
            "1l 7x"
        },
        { // M
            "1l 1x 5b 1x",
            "1l 2x 3b 2x",
            "1l 1x 1b 1x 1b 1x 1b 1x",
            "1l 1x 2b 1x 2b 1x",
            "3l 1x 5b 1x"
        },
        { // N
            "1l 1x 5b 1x",
            "1l 2x 4b 1x",
            "1l 1x 1b 1x 3b 1x",
            "1l 1x 2b 1x 2b 1x",
            "1l 1x 3b 1x 1b 1x",
            "1l 1x 4b 2x",
            "1l 1x 5b 1x"
        },
        { // O
            "1l 7x",
            "5l 1x 5b 1x",
            "1l 7x"
        },
        { // P
            "1l 6x",
            "2l 1x 5b 1x",
            "1l 6x",
            "3l 1x"
        },
        { // Q
            "1l 1b 5x",
            "3l 1x 5b 1x",
            "1l 1x 3b 1x 1b 1x",
            "1l 1x 4b 1x",
            "1l 1b 4x 1b 1x"
        },
        { // R
            "1l 6x",
            "2l 1x 5b 1x",
            "1l 6x",
            "1l 1x 3b 1x",
            "1l 1x 4b 1x",
            "1l 1x 5b 1x"
        },
        { // S
            "1l 1b 5x",
            "1l 1x 5b 1x",
            "1l 1x",
            "1l 1b 5x",
            "1l 6b 1x",
            "1l 1x 5b 1x",
            "1l 1b 5x"
        },
        { // T
            "1l 7x",
            "6l 3b 1x"
        },
        { // U
            "6l 1x 5b 1x",
            "1l 1b 5x"
        },
        { // V
            "4l 1x 5b 1x",
            "1l 1b 1x 3b 1x",
            "1l 2b 1x 1b 1x",
            "1l 3b 1x"
        },
        { // W
            "1l 1x 5b 1x",
            "5l 1x 2b 1x 2b 1x",
            "1l 1b 2x 1b 2x"
        },
        { // X
            "1l 1x 5b 1x",
            "1l 1b 1x 3b 1x",
            "1l 2b 1x 1b 1x",
            "1l 3b 1x",
            "1l 2b 1x 1b 1x",
            "1l 1b 1x 3b 1x",
            "1l 1x 5b 1x"
        },
        { // Y
            "1l 1x 5b 1x",
            "1l 1b 1x 3b 1x",
            "1l 2b 1x 1b 1x",
            "4l 3b 1x"
        },
        { // Z
            "1l 7x",
            "1l 5b 1x",
            "1l 4b 1x",
            "1l 3b 1x",
            "1l 2b 1x",
            "1l 1b 1x",
            "1l 7x"
        }
    };

    @Override
    public char[][] apply(Character c) {
        StringBuilder sb = new StringBuilder();
        String[] encodedLetter = ENCODED_LETTERS[c - 'A'];
        for (String command : encodedLetter) {
            String[] components = command.split("\\s");
            int lineCount = Integer.parseInt(
                components[0].substring(
                    0,
                    components[0].length() - 1
                )
            );
            StringBuilder line = new StringBuilder();
            for (int i = 1; i < components.length; i++) {
                char visualSymbol = components[i].charAt(components[i].length() - 1);
                int symbolCount = Integer.parseInt(
                    components[i].substring(
                        0,
                        components[i].length() - 1
                    )
                );
                String piece = String.valueOf(visualSymbol == 'x' ? 'X' : ' ')
                    .repeat(max(0, symbolCount));
                line.append(piece);
            }
            while (lineCount-- > 0) {
                sb.append('\n');
                sb.append(line);
            }
        }
        return sb.toString()
            .lines()
            .map(String::toCharArray)
            .toArray(char[][]::new);
    }
}
