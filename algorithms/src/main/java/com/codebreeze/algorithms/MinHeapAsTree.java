package com.codebreeze.algorithms;

/**
 * OUR min heap has two properties:
 * 1) each element is smaller than all its descendants
 * 2) traversing it in order should return the original array
 * so
 * 1,2,3,7,5,6,4
 * will be:
 *  1  1      1      1      1          1             1
 *      \      \      \      \          \             \
 *       2      2      2      2          2             2
 *               \      \      \          \             \
 *                3      3      3          3             3
 *                        \      \          \             \
 *                         7      5          5             4
 *                               /          / \           /
 *                              7          7   6         5
 *                                                      / \
 *                                                     7   6
 */
public class MinHeapAsTree
{
    //    https://www.cpp.edu/~ftang/courses/CS241/notes/heap.htm
    class Node
    {
        Node left;
        Node right;
        Node value;
    }
}
