/*

LeetCode: 304. Range Sum Query 2D - Immutable

Medium

Link: https://leetcode.com/problems/range-sum-query-2d-immutable/

Topics: Dynamic Programming, Array, Matrix, Prefix Sum, Design

Acceptance: 46.8

Given a 2D matrix matrix, handle multiple queries of the following type:

Calculate the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
Implement the NumMatrix class:

NumMatrix(int[][] matrix) Initializes the object with the integer matrix matrix.
int sumRegion(int row1, int col1, int row2, int col2) Returns the sum of the elements of matrix inside the rectangle defined by its upper left corner (row1, col1) and lower right corner (row2, col2).
 

Example 1:


Input
["NumMatrix", "sumRegion", "sumRegion", "sumRegion"]
[[[[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]], [2, 1, 4, 3], [1, 1, 2, 2], [1, 2, 2, 4]]
Output
[null, 8, 11, 12]

Explanation
NumMatrix numMatrix = new NumMatrix([[3, 0, 1, 4, 2], [5, 6, 3, 2, 1], [1, 2, 0, 1, 5], [4, 1, 0, 1, 7], [1, 0, 3, 0, 5]]);
numMatrix.sumRegion(2, 1, 4, 3); // return 8 (i.e sum of the red rectangle)
numMatrix.sumRegion(1, 1, 2, 2); // return 11 (i.e sum of the green rectangle)
numMatrix.sumRegion(1, 2, 2, 4); // return 12 (i.e sum of the blue rectangle)
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 200
-105 <= matrix[i][j] <= 105
0 <= row1 <= row2 < m
0 <= col1 <= col2 < n
At most 104 calls will be made to sumRegion. */

class NumMatrix {

    int[][] matrix;
    int[][] sumDp;
    public NumMatrix(int[][] matrix) {
        this.matrix = matrix;
        if(matrix != null){
            int m = matrix.length;
            int n = matrix[0].length;
            this.sumDp = new int[m + 1][n + 1];
            // sumDp at i, j stores the sum of the matrix from top left 0,0 to bottom right i-1, j-1
            // keeping i - 1 to avoid boundary conditions for 0 value i,j
            for(int i = 1; i <= m ; i++){
                for(int j = 1; j <= n; j++){
                    // value at i - 1, j -1 + sum of matrix to left and top of this value - overlapping sum added twice
                    sumDp[i][j] = matrix[i - 1][j - 1] + sumDp[i - 1][j] + sumDp[i][j - 1] - sumDp[i - 1][j - 1];
                }
            }
        }
    }
    
    /**
     * comment copied from Leetcode discuss
     *
     *   +-----+-+-------+     +--------+-----+     +-----+---------+     +-----+--------+
     *   |     | |       |     |        |     |     |     |         |     |     |        |
     *   |     | |       |     |        |     |     |     |         |     |     |        |
     *   +-----+-+       |     +--------+     |     |     |         |     +-----+        |
     *   |     | |       |  =  |              |  +  |     |         |  -  |              |
     *   +-----+-+       |     |              |     +-----+         |     |              |
     *   |               |     |              |     |               |     |              |
     *   |               |     |              |     |               |     |              |
     *   +---------------+     +--------------+     +---------------+     +--------------+
     *  
     *     sums[i][j]      =    sums[i-1][j]    +     sums[i][j-1]    -   sums[i-1][j-1]   +  
     *  
     *                          matrix[i-1][j-1]
     */
    public int sumRegion(int row1, int col1, int row2, int col2) {
        int sum = 0;
        if(this.matrix == null){
            return sum;
        }
        // adding 1 as sumDp starts at 1 not 0
        int r1 = row1 + 1;
        int r2 = row2 + 1;
        int c1 = col1 + 1;
        int c2 = col2 + 1;
        // total sum till r2,c2 - extra sum added before r1 and c1 + add back the overlapping sum subtracted twice
        sum = sumDp[r2][c2] - sumDp[r2][c1 - 1] - sumDp[r1 - 1][c2] + sumDp[r1 - 1][c1 - 1];
        return sum;
    }
}

/**
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 */
