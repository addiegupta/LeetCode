/*

LeetCode: 518. Coin Change 2

Medium

Link: https://leetcode.com/problems/coin-change-2/

Topics: Dynamic Programming, Array

Acceptance: 56.7

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.

Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.

You may assume that you have an infinite number of each kind of coin.

The answer is guaranteed to fit into a signed 32-bit integer.

 

Example 1:

Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1
Example 2:

Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.
Example 3:

Input: amount = 10, coins = [10]
Output: 1
 

Constraints:

1 <= coins.length <= 300
1 <= coins[i] <= 5000
All the values of coins are unique.
0 <= amount <= 5000

References: https://www.youtube.com/watch?v=Mjy4hd2xgrs
 */

class Solution {
    public int change(int amount, int[] coins) {
        if(coins == null || coins.length == 0){
            return 0;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        int n = coins.length;
        // list of coins is kept in outer loop to skip duplicates
        // at i,j, simulation is of coin at i being the last coin to form sum j
        // so dp[j - coins[i]] is added to dp[j]
        for(int i = 0; i < n ; i++){
            for(int j = 1; j <= amount; j++){
                int addCoinTo = j - coins[i];
                if(addCoinTo >= 0){
                    dp[j] += dp[addCoinTo];   
                } 
            }
        }
        return dp[amount];
    }
}
