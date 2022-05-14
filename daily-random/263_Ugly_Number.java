/*

LeetCode: 263. Ugly Number

Easy

Link: https://leetcode.com/problems/ugly-number/

Topics: Math

Acceptance: 41

An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return true if n is an ugly number.

 

Example 1:

Input: n = 6
Output: true
Explanation: 6 = 2 × 3
Example 2:

Input: n = 1
Output: true
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
Example 3:

Input: n = 14
Output: false
Explanation: 14 is not ugly since it includes the prime factor 7.
 

Constraints:

-231 <= n <= 231 - 1

 
*/

class Solution {
    public boolean isUgly(int n) {
        if(n <= 0){
            return false;
        }
        if(n == 1){
            return true;
        }
        int num = n;
        int[] primes = {2, 3, 5};
	// divide by all primes till there are no longer present
	// if these are the only factors of num, then num should be 1 at end of loop
        for(int i = 0; i < primes.length; i++){
            int prime = primes[i];
            while(num % prime == 0){
                num /= prime;
            }
        }
        return num == 1;
    }
}
