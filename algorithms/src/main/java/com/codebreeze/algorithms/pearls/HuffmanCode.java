package com.codebreeze.algorithms.pearls;

import com.codebreeze.algorithms.primitive.collections.map.Char2IntHashMap;
import com.codebreeze.algorithms.primitive.collections.map.Char2IntMap;
import com.codebreeze.algorithms.primitive.collections.map.Char2ObjectHashMap;
import com.codebreeze.algorithms.primitive.collections.map.Char2ObjectMap;

import java.util.PriorityQueue;
import java.util.Set;
import java.util.function.Function;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toSet;

/*
Note: if you only have a single character (aa, or bbbb, or c), then you do not need encoding, you only need
a number of characters! so, if you say 78, then you mean 78 of that character
 */
public class HuffmanCode implements Function<String, Char2ObjectMap<String>> {
    @Override
    public Char2ObjectMap<String> apply(String s) {
        // count character frequencies from string
        Set<HuffmanNode> charFrequencies = charFrequencyMap(s).stream()
            .map(charIntPair -> new HuffmanNode(charIntPair.first(), charIntPair.second(), null, null))
            .collect(toSet());

        // add to priority queue
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(comparingInt(HuffmanNode::frequency)) {{
            addAll(charFrequencies);
        }};

        // build huffman tree
        HuffmanNode root = huffmanTree(pq);

        // traverse tree to build final result!
        Char2ObjectHashMap<String> result = new Char2ObjectHashMap<>(10);
        huffmanCodes(root, new StringBuilder(), result);
        return result;
    }

    private HuffmanNode huffmanTree(PriorityQueue<HuffmanNode> pq) {
        HuffmanNode root = pq.peek(); // if only one, we should still create the root?
        while (pq.size() > 1) {
            HuffmanNode x = pq.poll();
            HuffmanNode y = pq.poll();
            HuffmanNode z = new HuffmanNode('\0', x.frequency() + y.frequency(), x, y);
            pq.add(z);
            root = z;
        }
        return root;
    }

    private void huffmanCodes(HuffmanNode root, StringBuilder code, Char2ObjectMap<String> char2Code) {
        if (root != null) {
            if (root.c() != '\0') {
                char2Code.put(root.c(), code.toString());
            } else {
                huffmanCodes(root.leftChild(), new StringBuilder(code.length() + 1).append(code).append('0'), char2Code);
                huffmanCodes(root.rightChild(), new StringBuilder(code.length() + 1).append(code).append('1'), char2Code);
            }
        }
    }

    private Char2IntMap charFrequencyMap(String s) {
        Char2IntMap frequencies = new Char2IntHashMap(26);
        for (int i = 0; i < s.length(); i++) {
            char key = s.charAt(i);
            if (frequencies.containsKey(key)) {
                frequencies.put(
                    key,
                    frequencies.get(key) + 1
                );
            } else {
                frequencies.put(key, 1);
            }
        }
        return frequencies;
    }

    private record HuffmanNode(char c, int frequency, HuffmanNode leftChild, HuffmanNode rightChild) {
    }
}
