/*

LeetCode: 202. Happy Number

Easy

Link: https://leetcode.com/problems/happy-number/

Topics: Hash Table, Math

Acceptance: 45.3

Write an algorithm to determine if a number is "happy".

A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1. Those numbers for which this process ends in 1 are happy numbers.

Example: 

Input: 19
Output: true
Explanation: 
12 + 92 = 82
82 + 22 = 68
62 + 82 = 100
12 + 02 + 02 = 1


 */
 
class Solution {
    
    // Helper method for both solutions
    private int getDigitSumOfSquares(int n){
        int ans=0;
        while(n>0){
            ans += Math.pow(n%10,2);
            n/=10;
        }
        return ans;
    }
    
    private boolean hashSetMethod(int n){
        HashSet<Integer> set = new HashSet<>();
        // If value alrady exists in set, Set.add() returns false
        while(set.add(n)){
            n = getDigitSumOfSquares(n);
            if(n==1)return true;
        }
        return false;
    }
    
    // Inspired by the Floyds List Cycle Detection Algorithm for Linked Lists
    private boolean floydCycleMethod(int n){
        int slow=n,fast=n;
        do{
            slow = getDigitSumOfSquares(slow);
            fast = getDigitSumOfSquares(getDigitSumOfSquares(fast));
            if(slow==1 || fast==1)return true;
        }while(slow!=fast);
        return false;
    }
    
    // Insight: If a number is not happy ( i.e. does not end in 1) then it ends in a loop containing 4
    public boolean isHappy(int n) {
        return floydCycleMethod(n);
        // return hashSetMethod(n);
    }
}