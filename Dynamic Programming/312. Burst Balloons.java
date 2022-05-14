/*

LeetCode: 312. Burst Balloons

Hard

Link: https://leetcode.com/problems/burst-balloons/

Topics: Dynamic Programming, Divide and Conquer

Acceptance: 48.8

Reference: https://www.youtube.com/watch?v=VFskby7lUbw&t=1015s&ab_channel=NeetCode


Given n balloons, indexed from 0 to n-1. Each balloon is painted with a number on it represented by array nums. You are asked to burst all the balloons. If the you burst balloon i you will get nums[left] * nums[i] * nums[right] coins. Here left and right are adjacent indices of i. After the burst, the left and right then becomes adjacent.

Find the maximum coins you can collect by bursting the balloons wisely.

Note:

You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them.
0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
Example:

Input: [3,1,5,8]
Output: 167 
Explanation: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
             coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167


 */
class Solution {
   
    // Time: O(n^3) : n^2 possible subarrays, for every subarray an iteration of n is done
    // Space: O(n^2): dp cache for all n^2 subarrays
    public int maxCoins(int[] nums) {
        
        // Empty array
        if(nums==null || nums.length ==0)return 0;
        
        int n = nums.length;
        
        // dp[i][j]: Max coins obtained by bursting all balloons between i and j inclusive
        int[][] dp = new int[n][n];
        
        // Left and right limits of subarray
        int l,r;

        // lCoins,rCoins: Coins due to left and right of current balloon being treated as last balloon
        // curr:          Coins due to last balloon being burst
        int lCoins,rCoins,curr;
        // Gap between left and right denoted by i
        for(int i=0;i<n;i++){
            for(l=0;l+i<n;l++){
                // right limit
                r=l+i;
                // Every balloon in the l,r subarray is treated as last balloon to be burst one-by-one and the max value is chosen
                for(int k=l;k<=r;k++){

                    // Coins due to balloons on left of k being burst
                    lCoins = (k>l)?dp[l][k-1]:0;
                    
                    // Coins due to balloons on right of k being burst
                    rCoins = (k<r)?dp[k+1][r]:0;
                    
                    // The balloons for left and right of k are present outside the l,r subarray
                    curr = nums[k] * ((l>0)?nums[l-1]:1) * ((r<n-1)?nums[r+1]:1);

                    dp[l][r] = Math.max(dp[l][r],curr + lCoins + rCoins);
                }
            }
        }

        // DP value for the entire subarray
        return dp[0][n-1];
    }
}
