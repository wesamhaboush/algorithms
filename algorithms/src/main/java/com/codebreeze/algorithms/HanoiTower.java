package com.codebreeze.algorithms;

import java.util.Stack;

public class HanoiTower
{
    /*
    Step 1 − Move n-1 disks from source to aux
Step 2 − Move nth disk from source to dest
Step 3 − Move n-1 disks from aux to dest
A recursive algorithm for Tower of Hanoi can be driven as follows −

START
Procedure Hanoi(disk, source, dest, aux)

   IF disk == 0, THEN
      move disk from source to dest
   ELSE
      Hanoi(disk - 1, source, aux, dest)     // Step 1
      move disk from source to dest          // Step 2
      Hanoi(disk - 1, aux, dest, source)     // Step 3
   END IF

END Procedure
STOP
     */
    public static <T> void of(final Stack<T> source,
                              final Stack<T> destination,
                              final Stack<T> auxiliary)
    {
        hanoi(source.size(), source, destination, auxiliary);
    }

    public static <T> void hanoi(final int disksToMoveCount,
                                 final Stack<T> source,
                                 final Stack<T> destination,
                                 final Stack<T> auxiliary)
    {
        if(disksToMoveCount > 0) //we still have more disks to move
        {
            // move all disks except bottom one from source to auxiliary
            hanoi(disksToMoveCount - 1, source, auxiliary, destination);
            // now, the bottom is ready to go to destination, so move it.
            destination.push(source.pop());
            // now move all what we stacked on auxiliary to destination, using source as auxiliary
            hanoi(disksToMoveCount - 1, auxiliary, destination, source);
        }
    }
}
