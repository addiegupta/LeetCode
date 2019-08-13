/*

LeetCode: 309. Best Time to Buy and Sell Stock with Cooldown

Medium

Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

Topics: Dynamic Programming

Acceptance: 44.6

Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times) with the following restrictions:

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
Example:

Input: [1,2,3,0,2]
Output: 3 
Explanation: transactions = [buy, sell, cooldown, buy, sell]

 */
class Solution {


    /* State Diagram explanation -------------------------------
        
        Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75928/Share-my-DP-solution-(By-State-Machine-Thinking)


        3 states:1) Holding stock(bought beforeand resting here or just bought)
                 2) Sold stock ( just now sold, need to go in rest after this)
                 3) Resting state( sold on previous step or resting from before that

    */
    public int maxProfit(int[] prices) {
        int n;
        if(prices == null || (n = prices.length)<=1)return 0;
        
        // Using 3 variables instead of 3 arrays as only the immediate previous value is needed
        // int[] hold = new int[n];
        // int[] release = new int[n];
        // int[] rest = new int[n];
        int release=0,rest=0;

        // Bought at first price
        int hold = 0-prices[0];
        
        // temp variable used to store old value of hold
        int prevHold;
        for(int i=1;i<n;i++){
            
            prevHold = hold;
            // keep holding stock or buy at this price
            hold = Math.max(hold,rest-prices[i]);
            // hold[i] =Math.max(hold[i-1],rest[i-1]-prices[i]);
            
            // Stay in rest or just now arrive in cooldown state after selling
            rest = Math.max(rest,release);
            // rest[i] = Math.max(rest[i-1],release[i-1]);

            // Sell at this point
            release = prevHold + prices[i];
            // release[i] = hold[i-1]+prices[i];
        }

        // ans will be in sold state or resting state, buying at last step is waste
        return Math.max(rest,release);
    }
}