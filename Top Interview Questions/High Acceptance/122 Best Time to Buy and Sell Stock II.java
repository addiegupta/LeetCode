/*

LeetCode: 122. Best Time to Buy and Sell Stock II

Easy

Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

Acceptance: 51.9

Topics: Array, Greedy

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).

Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

Example 1:

Input: [7,1,5,3,6,4]
Output: 7
Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
             Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
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
        int ans=0;
        int n = prices.length;
        if(prices==null || n==0)return ans;
        int min = prices[0];
        int diff;
        for(int price: prices){
            diff = price-min;
            // Set this as new buying price
            if(diff<0)min=price;
            // Sell; if next value turns out to be even better
            // Then a new sell will be initiated 
            // i.e. one sell will be simulated as 2 sells
            else{
                ans += diff;
                min = price;
            }
        }
        return ans;
   }
}