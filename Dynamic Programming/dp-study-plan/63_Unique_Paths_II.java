/*

LeetCode: https://leetcode.com/problems/unique-paths-ii/

Medium

Link: https://leetcode.com/problems/unique-paths-ii/

Topics: Dynamic Programming, Array, Matrix

Acceptance: 37.2

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and space is marked as 1 and 0 respectively in the grid.

 

Example 1:


Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
Output: 2
Explanation: There is one obstacle in the middle of the 3x3 grid above.
There are two ways to reach the bottom-right corner:
1. Right -> Right -> Down -> Down
2. Down -> Down -> Right -> Right
Example 2:


Input: obstacleGrid = [[0,1],[0,0]]
Output: 1
 

Constraints:

m == obstacleGrid.length
n == obstacleGrid[i].length
1 <= m, n <= 100
obstacleGrid[i][j] is 0 or 1.

 */

class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if(obstacleGrid == null || obstacleGrid.length == 0){
            return 0;
        }
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // only need previous row dp values
        int[] dp = new int[n];
        dp[0] = 1;
        for(int i = 0 ; i < m; i++){
            for(int j = 0; j < n; j++){
                
                // no obstacle present, add ways of arriving here from left;
                // ways of arriving from top are already present in dp[j]
                if(obstacleGrid[i][j] == 0){
                    if(j != 0){
                        dp[j] += dp[j-1];
                    }
                } else{ // obstacle present, cannot reach here
                    dp[j] = 0;
                }
            }
        }
        return dp[n - 1];
    }
}
