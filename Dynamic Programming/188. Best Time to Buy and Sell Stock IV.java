/*

LeetCode: 188. Best Time to Buy and Sell Stock IV

Hard

Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/

Topics: Dynamic Programming, Array

Acceptance: 26.7

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most k transactions.

Note:
You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

Example 1:

Input: [2,4,1], k = 2
Output: 2
Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
Example 2:

Input: [3,2,6,5,0,3], k = 2
Output: 7
Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4.
             Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.

 */
class Solution {


    // Recurrence Relation -------------------------------
    // T[i][j] where i is for number of transactions carried out, j is for subarray till where calculation is being done
    //
    //               <--A---->      <------B----------------------->                
    // T[i][j] = max(T[i][j-1], max(prices[j]-prices[m] + T[i-1][m]) for all m 0..j-1)
    //
    // Explanation: A: No transaction carried out on jth day, simply consider best profit till previous day
    //              B: for all m:0..j-1 , stock is bought on mth day and sold on jth so profit in this transaction is
    //                 prices[j] - prices[m] and T[i-1][m] is the best profit carried out in previous transaction
    //                 till mth day
    // Optimisation brings down time from O(n^2 * k) to O(n*k)
    //          Instead of calculating best max profit for previous days 0..m, this can be stored at every point for future use
    //          as in Term B, prices[j] - prices[m] +T[i-1][m], only prices[j] is variable while rest is constant

    public int maxProfit(int k, int[] prices) {

        return tabularSolution(k,prices);
        return optimisedTabularSolution(k,prices);
        // return leetCodeSolution(k,prices);
        // return dpTabularSolution(k,prices);
        return newDpTabularSolution(k,prices);
    }
    
    private int optimisedTabularSolution(int k,int[] prices){
        int n;
        if(prices==null || (n=prices.length)==0)return 0;
        
        // At every point, buy sell can be carried out; use this to do in O(n) and avoids TLE
        if(k>=n/2)return quickSolve(prices);
        
        // Note: First row first column 0 as for 0 transactions or for 0 sells, there is no profit

        // A little tricky linear space implementation, only needed for further optimisations
        // return linearArrayImplementation(k,prices,n);

        // Tabular space implementation
        int[][] dp = new int[k+1][n];
        int maxDiff;

        for(int i=1;i<=k;i++){
            maxDiff = dp[i-1][0]-prices[0];
            for(int j=1;j<n;j++){
                dp[i][j] = Math.max(dp[i][j-1],prices[j]+maxDiff);
                maxDiff = Math.max(maxDiff,dp[i-1][j] - prices[j]);
            }
        }
        return dp[k][n-1];
        
    }
    // Enough transactions allowed, can buy sell freely
     private int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }

    //  Replaces table with array by storing upper row variable in integer when needed
    private int linearArrayImplementation(int k,int[] prices, int n){

        int[] dp = new int[n];
        int maxDiff;
        int newDiff;
        for(int i=1;i<=k;i++){
            maxDiff = dp[0]-prices[0];
            for(int j=1;j<n;j++){
                // Store upper row value for later use 2 lines below
                newDiff = dp[j] - prices[j];
                dp[j] = Math.max(dp[j-1],prices[j]+maxDiff);
                maxDiff = Math.max(maxDiff,newDiff);
            }
        }
        return dp[n-1];

    }

    // O(n^2 * k ) time O(n*k) space
    // optimised version is O(n*k)
    private int tabularSolution(int k,int[] prices){
        int n;
        if(prices==null || (n=prices.length)==0)return 0;
        
        // Rows are for number of transactions carried out 0..k
        // Column represents day of transaction
        int[][] dp = new int[k+1][n];
        

        for(int t=1;t<=k;t++){
            for(int j=1;j<n;j++){
                dp[t][j] = dp[t][j-1];
                
                for(int i=0;i<j;i++){
                    dp[t][j] = Math.max(dp[t][j],prices[j]-prices[i] + dp[t-1][i]);
                }
            }
        }
        return dp[k][n-1];
    }
}