/*

LeetCode: 279. Perfect Squares

Medium

Link: https://leetcode.com/problems/perfect-squares/

Topics: Dynamic Programming, Math, Breadth First Search

Acceptance: 42.7

Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

Example 1:

Input: n = 12
Output: 3 
Explanation: 12 = 4 + 4 + 4.
Example 2:

Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.

 */
class Solution {
    // Time Complexity: O(n * sqrt(n), Space: O(n)
    public int numSquares(int n) {
        if(n<=0)return 0;
        
        // dp[i] = count of minimum perfect square numbers that sum up to i 
        int[]dp = new int[n+1];

        for(int i=1;i<=n;i++)dp[i] = Integer.MAX_VALUE;
        // dp[0] = 0, dp[1] = 1 
        dp[1]=1;

        for(int i=2;i<=n;i++){
            // j*j will give a perfect square number smaller than equal to i
            // subtracting it from i will give us count for another value which has already been computed
            // hence adding 1 to it will give us required value
            // e.g. for i=12, j=2 we access dp[12-(2*2)] = dp[8] = 2 (4+4) hence for 12 ans is 2+1 = 3 (4+4+4=12)
            for(int j=1;j*j<=i;j++){
                dp[i] = Math.min(dp[i],1+dp[i-j*j]);
            }
        }
        return dp[n];
    }
}
