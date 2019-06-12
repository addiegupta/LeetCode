/*

LeetCode: 198. House Robber

Easy

Link: https://leetcode.com/problems/plus-one/

Topics: Dynamic Programming

Acceptance: 41.0

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.

Example 1:

Input: [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
             Total amount you can rob = 1 + 3 = 4.
Example 2:

Input: [2,7,9,3,1]
Output: 12
Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
             Total amount you can rob = 2 + 9 + 1 = 12.

References: https://leetcode.com/problems/house-robber/discuss/156523/From-good-to-great.-How-to-approach-most-of-DP-problems.

 */

class Solution {
    
    // Robber either robs current house i and adds best value till house i-2
    //              or doesnt rob current house and collects loot till previous house
    // dp array contains best loot at every index
    private int dpSolution(int[] nums){
        if(nums==null||nums.length==0)return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        
        dp[0]=nums[0];
        if(n==1)return dp[0];
        
        dp[1]=Math.max(nums[1],nums[0]);
        if(n==2)return dp[1];
        
        for(int i=2;i<n;i++){
            dp[i] = Math.max(dp[i-1],nums[i] + dp[i-2]);    
        }
        return dp[n-1];
    }

    // Only last 2 values are needed, hence array replaced with variables
    private int improvedMemoryDpSolution(int[] nums){
        if(nums==null||nums.length==0)return 0;
        int prev2=0,prev1=0,temp;
        for(int num:nums){
            temp = prev1;
            prev1 = Math.max(prev1,prev2+num);
            prev2=temp;
        }
        return prev1;
    }
    public int rob(int[] nums) {
        // return dpSolution(nums);
        return improvedMemoryDpSolution(nums);
    }
}