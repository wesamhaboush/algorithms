package com.codebreeze.algorithms;

import java.util.Stack;

public class IsPreOrderTraversalOfBst
{
    public static boolean of(final int preOrderTraversal[]) {
//        System.out.println("assessing list: " + Arrays.toString(preOrderTraversal));
        //the stack contains all items that we have not covered all their left tree
        final Stack<Integer> stack = new Stack<>();
        int currentRoot = Integer.MIN_VALUE;

        // Traverse given array
        for (int i = 0; i < preOrderTraversal.length; i++) {
//            System.out.println("iteration: " + i + ", stack: " + stack +
//                               ", currentRoot: " + currentRoot + ", item: " + preOrderTraversal[i]);
            // If a node which is on right side
            // is smaller than currentRoot, this cannot be a bst preorder traversal
//            System.out.println(String.format("is %s < %s ? %s",
//                    preOrderTraversal[i], currentRoot, preOrderTraversal[i] < currentRoot));
            if (preOrderTraversal[i] < currentRoot) {
//                System.out.println("item " + preOrderTraversal[i] + " is less than " + currentRoot
//                                   + ", then not a bst traversal");
                return false;
            }

            // If preOrderTraversal[i] is in right subtree of stack top,
            // Keep removing items smaller than preOrderTraversal[i]
            // and make the last removed item as new
            // currentRoot.
            //what we are doing here is that we are popping each root that has its
            //left tree done. How you identify a root that has its left tree done? by the current
            //item being greater than it (remember all items on left are smaller than the item, and
            //all items on the right are greater than item).
            //now, once this rules is not satisfied, that means we are on an item that still has
            //its left tree incomplete, so we keep this item as our new root, and push
            // the current item on the list into the stack cz we did not identify whether
            //its left tree has been processed yet.
            while (!stack.empty() && preOrderTraversal[i] > stack.peek()) {
                currentRoot = stack.pop();
//                System.out.println(String.format("poped: %s since %s > %s, stack: %s, "
//                                                 + "i.e. %s is in %s's right tree, so %s's left tree is done",
//                        currentRoot, preOrderTraversal[i], currentRoot, stack, preOrderTraversal[i], currentRoot, currentRoot));
                //                System.out.println("current root: " + currentRoot);
            }

            // At this point either stack is empty or
            // preOrderTraversal[i] is smaller than currentRoot, push preOrderTraversal[i]
            stack.push(preOrderTraversal[i]);
//            System.out.println("pushed: " + preOrderTraversal[i] + ", stack: " + stack);
        }
        return true;
    }
}
