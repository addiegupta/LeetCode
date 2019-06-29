/*

LeetCode: 322. Coin Change

Medium

Link: https://leetcode.com/problems/coin-change/

Topics: Dynamic Programming

Acceptance: 30.8

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.

 */
class Solution {
    private int bottomUpSolution(int[] coins,int amount){
        // Used as a max unattainable value for comparisons while calculating minimum coins required
        int max = amount+1;
        int dp[] = new int[amount+1];
        
        // 0 coins required for 0 amount
        dp[0] = 0;
        int num = coins.length;
        
        // Bottom up DP; for every value from 0,1,..,amount
        // build the array which contains the min number of coins required to form that amount
        // Outer for loop is for every total value
        for(int i=1;i<=amount;i++){
            // Unattainable max value for comparisons
            dp[i]=max;
            // Loop for all coin values
            for(int j=0;j<num;j++){
                // e.g. coin value is 5 and i is 7, then we check dp[7-5] i.e. dp[2] 
                // so we add 1 coin of value 5 to dp[2] and set it to dp[7] if it is less than current dp[7]
                if(coins[j]<=i){
                    dp[i] = Math.min(dp[i],dp[i-coins[j]] + 1);
                }
            }
        }
        return (dp[amount]>amount)? -1: dp[amount];
    }
    private int topDownSolution(int[] coins,int amount,int[] dp){
        
        if(amount<0)return -1;
        if(amount==0)return 0;
        // Already calculated, return from dp array
        if(dp[amount-1]!=0)return dp[amount-1];
        
        int min = Integer.MAX_VALUE;
        int obtained;
        for(int i = 0;i<coins.length;i++){
            // This coin value is too large
            if(amount-coins[i]<0)continue;
            // current coin is used, find ans for amount - current coin value
            obtained = topDownSolution(coins,amount-coins[i],dp);
            // No solution found
            if(obtained==-1)continue;
            min = Math.min(min,obtained+1);
        }
        dp[amount-1] = (min==Integer.MAX_VALUE) ?-1:min;
        return dp[amount-1];
    }
    public int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length==0 || amount<=0) return 0;
        return bottomUpSolution(coins,amount);
        // return topDownSolution(coins,amount,new int[amount]);
    }
}