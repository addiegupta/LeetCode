/*

LeetCode: 714. Best Time to Buy and Sell Stock with Transaction Fee

Medium

Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

Topics: Dynamic Programming, Array, Greedy

Acceptance: 51.2

Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.

You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)

Return the maximum profit you can make.

Example 1:
Input: prices = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: The maximum profit can be achieved by:
Buying at prices[0] = 1
Selling at prices[3] = 8
Buying at prices[4] = 4
Selling at prices[5] = 9
The total profit is ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
Note:

0 < prices.length <= 50000.
0 < prices[i] < 50000.
0 <= fee < 50000.

 */
class Solution {
    public int maxProfit(int[] prices, int fee) {

        int n;
        if(prices ==null || (n=prices.length)==0 )return 0;
        
        // Initial state: profit by selling is 0, buying results in negative profit
        int cash = 0,hold = -prices[0],oldCash;
        for(int i=1;i<n;i++){
            // At every step, buy represents the max profit in buy status, given 
            // that the last action you took is a buy action in the past
            //  And you have the right to sell in future, or do nothing. Similar for sell
            oldCash = cash;
            cash = Math.max(cash,hold+prices[i]-fee); //keep the same as previous day, or sell from buy status of previous day
            hold = Math.max(hold,oldCash-prices[i]); // keep the same as previous , or buy from sell status of previous day
        }
        return cash;
    }
}