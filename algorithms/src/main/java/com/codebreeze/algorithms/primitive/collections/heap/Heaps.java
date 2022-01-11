package com.codebreeze.algorithms.primitive.collections.heap;

import com.codebreeze.algorithms.primitive.collections.list.ArrayIntList;
import com.codebreeze.algorithms.primitive.collections.list.IntList;

import java.util.List;

import static java.lang.Math.max;

public class Heaps {
    public static int parentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    public static int leftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    public static int rightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    public static <T> void print(List<T> heap, StringBuilder buffer, String prefix, String childrenPrefix, int currentNode) {
        buffer.append(prefix);
        buffer.append(heap.get(currentNode));
        buffer.append('\n');
        int leftChildIndex = leftChildIndex(currentNode);
        int rightChildIndex = rightChildIndex(currentNode);
        if (leftChildIndex < heap.size() && rightChildIndex < heap.size()) {
            print(heap, buffer, childrenPrefix + "├── ", childrenPrefix + "│   ", leftChildIndex);
            print(heap, buffer, childrenPrefix + "└── ", childrenPrefix + "    ", rightChildIndex);
        } else if (leftChildIndex < heap.size()) {
            print(heap, buffer, childrenPrefix + "└── ", childrenPrefix + "    ", leftChildIndex);
        } else if (rightChildIndex < heap.size()) {
            print(heap, buffer, childrenPrefix + "└── ", childrenPrefix + "    ", rightChildIndex);
        }
    }

    public static <T> void printNode(StringBuilder sb, List<T> heap) {
        int maxLevel = maxLevel(0, heap);
        IntList rootInAList = new ArrayIntList(1) {{
            add(0);
        }};
        printNodeInternal(rootInAList, 1, maxLevel, sb, heap);
    }

    private static <T> void printNodeInternal(IntList nodes, int level, int maxLevel, StringBuilder sb, List<T> heap) {
        if (nodes.isEmpty()) {
            return;
        }

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        printWhitespaces(firstSpaces, sb);

        IntList newNodes = new ArrayIntList();
        nodes.stream().forEachOrdered(node -> {
            if (node >= 0 && node < heap.size()) {
                sb.append(heap.get(node));
                newNodes.add(leftChildIndex(node));
                newNodes.add(rightChildIndex(node));
            } else {
                sb.append(' ');
            }

            printWhitespaces(betweenSpaces, sb);
        });
        sb.append('\n');

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(firstSpaces - i, sb);
                if (nodes.get(j) >= heap.size()) {
                    printWhitespaces(endgeLines + endgeLines + i + 1, sb);
                    continue;
                }

                if (leftChildIndex(nodes.get(j)) < heap.size())
                    sb.append('/');
                else
                    printWhitespaces(1, sb);

                printWhitespaces(i + i - 1, sb);

                if (rightChildIndex(nodes.get(j)) < heap.size())
                    sb.append('\\');
                else
                    printWhitespaces(1, sb);

                printWhitespaces(endgeLines + endgeLines - i, sb);
            }

            sb.append('\n');
        }

        printNodeInternal(newNodes, level + 1, maxLevel, sb, heap);
    }

    private static void printWhitespaces(int count, StringBuilder sb) {
        sb.append(" ".repeat(max(0, count)));
    }

    private static <T> int maxLevel(int node, List<T> heap) {
        if (node < 0 || node >= heap.size())
            return 0;

        return max(maxLevel(leftChildIndex(node), heap), maxLevel(rightChildIndex(node), heap)) + 1;
    }
}
