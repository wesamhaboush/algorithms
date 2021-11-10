package com.codebreeze.algorithms.pearls;

/*
David Gries calls this the “Coffee Can Problem” in his Science of Programming.
You are initially given a coffee can that contains some black beans and some white
 beans and a large pile of “extra” black beans. You then repeat the following
 process until there is a single bean left in the can.

Randomly select two beans from the can.
* If they are the same color:
    throw them both out and insert an extra black bean.
* If they are different colors:
    return the white bean to the can and throw out the black.

Prove that the process terminates.
What can you say about the color of the final remaining bean as a function of
the number of black and white beans originally in the can?

 */
public class BlackWhiteBeans {
    // return how many iterations to termination
    public int apply(int blackBeanCount, int whiteBeanCount) {
        System.out.println("====================================");
        System.out.printf("starting with black = %d, white = %d%n", blackBeanCount, whiteBeanCount);
        int iterationCount = 0;
        while (blackBeanCount + whiteBeanCount > 1) {
            iterationCount++;

            // we first, draw one!
            double total = blackBeanCount + whiteBeanCount;
            double whiteRatio = whiteBeanCount / total;
            System.out.println("white ratio: " + whiteRatio);
            boolean isBlack1 = Math.random() > whiteRatio;

            // now we draw the second after updating the counts as we took one out!
            if(isBlack1) {
                whiteRatio = whiteBeanCount / (total - 1); // it was black, so white count goes down
            } else {
                whiteRatio = (whiteBeanCount - 1) / (total - 1); // it was white, so white count and total goes down
            }
            boolean isBlack2 = Math.random() > whiteRatio;
            if (isBlack1 == isBlack2) {
                System.out.printf("same color, isBlack: %b, %b%n", isBlack1, isBlack2);
                if (isBlack1) {
                    blackBeanCount--;
                    blackBeanCount--;
                } else {
                    whiteBeanCount--;
                    whiteBeanCount--;
                }
                blackBeanCount++;
            } else {
                System.out.printf("different color, isBlack: %b, %b%n", isBlack1, isBlack2);
                blackBeanCount--;
            }
            System.out.printf("iteration %d, white = %d, black = %d%n", iterationCount, whiteBeanCount, blackBeanCount);
        }
        return iterationCount;
    }
}
