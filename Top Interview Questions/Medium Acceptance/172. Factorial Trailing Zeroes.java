/*

LeetCode: 172. Factorial Trailing Zeroes
Easy

Link: https://leetcode.com/problems/factorial-trailing-zeroes/

Topics: Math

Acceptance: 37.4


Given an integer n, return the number of trailing zeroes in n!.

Example 1:

Input: 3
Output: 0
Explanation: 3! = 6, no trailing zero.
Example 2:

Input: 5
Output: 1
Explanation: 5! = 120, one trailing zero.
Note: Your solution should be in logarithmic time complexity.

 */
class Solution {
	// A trailing zero is found due to a multiplication of 2x5 and as there are more 2s than 5s, we simply need to count 
	// the total 5s that will be multiplied in factorial
    public int trailingZeroes(int n) {
        int ans=0;
        while(n>0){
            n/=5;
            ans+=n;
        }
        return ans;
    }
}