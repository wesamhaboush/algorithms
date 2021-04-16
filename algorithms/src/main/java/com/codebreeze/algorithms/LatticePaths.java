package com.codebreeze.algorithms;

/*
Lattice paths
Problem 15
Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
there are exactly 6 routes to the bottom right corner.

How many such routes are there through a 20×20 grid?
 */
public class LatticePaths
{
    /*
    The solution can depend on dynamic programming where at each vertex, the we count how many ways there is to get
    to that point. The very right bottom vertex, we will know the total number.
     */
    public static long calculate(int gridSize)
    {
        final long[][] grid = new long[gridSize + 1][gridSize + 1];
        //do first row
        for(int i = 1; i < grid[0].length; i++) // we start from 1 cz the first one has value of zero
        {
            grid[0][i] = 1;
        }
        //do first column
        for(int i = 1; i < grid.length; i++) // we start from 1 cz the first one has value of zero
        {
            grid[i][0] = 1;
        }
        //now do the inner grid
        for(int i = 1; i < grid.length; i++) // start from one cz we have done first row
        {
            for(int j = 1; j < grid[0].length; j++) // start from one cz we have done first column
            {
                grid[i][j] = grid[i][j-1] + grid[i -1][j]; // ways to get to this vertex = ways to get to upper vertex + ways to get to vertex on the left
            }
        }
        return grid[gridSize][gridSize];
    }
}
