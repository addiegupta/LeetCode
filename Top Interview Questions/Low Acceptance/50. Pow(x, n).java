/*

LeetCode: 50. Pow(x, n)

Medium

Link: https://leetcode.com/problems/powx-n/

Topics: Math, Binary Search

Acceptance: 30.3

Implement pow(x, n), which calculates x raised to the power n (xn).

Example 1:

Input: 2.00000, 10
Output: 1024.00000
Example 2:

Input: 2.10000, 3
Output: 9.26100
Example 3:

Input: 2.00000, -2
Output: 0.25000
Explanation: 2-2 = 1/22 = 1/4 = 0.25
Note:

-100.0 < x < 100.0
n is a 32-bit signed integer, within the range [−231, 231 − 1]

 */
class Solution {
    public double myPow(double x, int n) {
		// Base case
        if(n==0 || x == 1.0 )return 1.0;
        
		// Handles overflow as well
		// if x is set to 1/x and n to -n, then for n = Integer.MIN_VALUE, overflow will occur,
        if(n<0){
            return 1/x * myPow(1/x, -(n + 1));
        }
    
		// for even n, simply divide the power in half while multiplying x with itself; for odd case, multiply with x again
        return (n%2==0) ? myPow(x*x,n/2):myPow(x*x,n/2)*x;
    }
    
}