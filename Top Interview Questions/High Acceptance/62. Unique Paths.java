/*

LeetCode: 62. Unique Paths

Medium

Link: https://leetcode.com/problems/unique-paths/

Topics: Array, Dynamic Programming

Acceptance: 47.7

A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?


Above is a 7 x 3 grid. How many possible unique paths are there?

Note: m and n will be at most 100.

Example 1:

Input: m = 3, n = 2
Output: 3
Explanation:
From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
1. Right -> Right -> Down
2. Right -> Down -> Right
3. Down -> Right -> Right
Example 2:

Input: m = 7, n = 3
Output: 28

 */
 
class Solution {
    public int uniquePaths(int m, int n) {
		
        // Single dimension array can be used as previous rows data becomes useless except for immediate previous rows
		// whose value is already contained in the dp array
        int[] dp = new int[n];
        Arrays.fill(dp,1);
        
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                // Since robot only moves down or right, it can only arrive from up or left
                // The count of paths till a location is sum of paths till location up and till left
                // The count for above location is already present in dp[j] and count for left is obtained from dp[j-1]
                dp[j] += dp[j-1];
            }
        }
        return dp[n-1];
    }
}