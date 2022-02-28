/*

LeetCode: 64. Minimum Path Sum

Medium

Link: https://leetcode.com/problems/minimum-path-sum/

Topics: Dynamic Programming, Array, Matrix

Acceptance: 58.9

Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.

 

Example 1:


Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
Output: 7
Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
Example 2:

Input: grid = [[1,2,3],[4,5,6]]
Output: 12
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 200
0 <= grid[i][j] <= 100

 */

class Solution {
    public int minPathSum(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        // only one row of dp array can suffice since only top and left values are looked up
        // older row values can be discarded
        int[] dp = new int[n];
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int left = j == 0 ? dp[j] : dp[j - 1];
                int top = dp[j];
                // for first row, top is not applicable
                int bestPathSum = i == 0 ? left : Math.min(top, left);
                
                dp[j] = grid[i][j] + bestPathSum;
            }
        }
        return dp[n - 1];
    }
}
