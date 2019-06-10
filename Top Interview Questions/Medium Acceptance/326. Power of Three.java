/*

LeetCode: 326. Power of Three

Easy

Link: https://leetcode.com/problems/power-of-three/

Topics: Math

Acceptance: 41.7

Given an integer, write a function to determine if it is a power of three.

Example 1:

Input: 27
Output: true
Example 2:

Input: 0
Output: false
Example 3:

Input: 9
Output: true
Example 4:

Input: 45
Output: false
Follow up:
Could you do it without using any loop / recursion?


 */
 
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n<=0)return false;
        
        while(n%3==0) n/=3;
        
        return n==1;
        
        // return baseConversionMethod(n);
        // return logMethod(n);
        // return maxPowerMethod(n);
    }
    private boolean baseConversionMethod(int n){
        return Integer.toString(n, 3).matches("^10*$");
    }
    private boolean logMethod(int n){
        // Epsilon is for avoiding imprecision errors
        return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon;
    }
    private boolean maxPowerMethod(int n){   
        return n > 0 && 1162261467 % n == 0;
    }
}