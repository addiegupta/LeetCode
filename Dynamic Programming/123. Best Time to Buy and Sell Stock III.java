/*

LeetCode: 123. Best Time to Buy and Sell Stock III

Hard

Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

Topics: Dynamic Programming, Array

Acceptance: 34.5

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [3,3,5,0,0,3,1,4]
Output: 6
Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
             Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
Example 2:

Input: [1,2,3,4,5]
Output: 4
Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
             Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are
             engaging multiple transactions at the same time. You must sell before buying again.
Example 3:

Input: [7,6,4,3,1]
Output: 0
Explanation: In this case, no transaction is done, i.e. max profit = 0.

 */
class Solution {
    public int maxProfit(int[] prices) {

        // return leetCodeSolution(prices);
        return leftRightTraversalSolution(prices);
    }

    // Easier to understand O(n) time O(n) space
    // Split the problem into 2 'buy sell stock only 1 transaction'
    // Create array profit where profit[i] = max profit when 1 transaction is in [i..n-1] days on first traversal in right to left order
    // On second traversal left to right, add max profit when 1 transaction is in [0..i] hence getting best value for 2 transactions
    private int leftRightTraversalSolution(int[] prices){
        int n;
        if(prices==null || (n=prices.length)==0)return 0;
        
        int[] profit = new int[n];
        int max=prices[n-1];

        // Get best profit from one transaction in [i..n-1]
        for(int i=n-2;i>=0;i--){
            if(prices[i]>max) max = prices[i];
            
            profit[i] = Math.max(profit[i+1],max-prices[i]);
        }
        int min = prices[0];
        
        // add the best profit from one transaction in [0..i]
        // hence at end, profit[n-1] = best profit from 2 transactions in [0..n-1] which is the answer
        for(int i=1;i<n;i++){
            if(prices[i]<min)min = prices[i];
            
            profit[i] =Math.max(profit[i-1],profit[i] + prices[i]-min);
        }
        return profit[n-1];
        
    }


    // A little tricky to understand
    // O(n) time O(1) space
    private int leetCodeSolution(int[] prices){
        // Store the minimum buying price for 1 trans and 2 transaction respectively
        int buy1=Integer.MAX_VALUE,buy2 = Integer.MAX_VALUE;
        // Store the best possible profit
        int sell1=0,sell2=0;
        for(int price: prices){

            // Min value for buying till this point
            buy1 = Math.min(buy1,price);
            // Best profit in 1 transaction till this point
            sell1 = Math.max(sell1,price-buy1);

            // Subtracting sell1 (which is the profit from 1st transaction or the total cash)
            // simulates as if this transaction takes place after the 1st
            buy2 = Math.min(buy2,price-sell1);
            // Best profit from selling 2nd transaction
            sell2 = Math.max(sell2,price-buy2);
        }
        return sell2;
        
    }
}