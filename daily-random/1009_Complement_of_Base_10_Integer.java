/*

LeetCode: 1009. Complement of Base 10 Integer

Easy

Link: https://leetcode.com/problems/complement-of-base-10-integer/

Topics: Bit Manipulation

Acceptance: 62

The complement of an integer is the integer you get when you flip all the 0's to 1's and all the 1's to 0's in its binary representation.

For example, The integer 5 is "101" in binary and its complement is "010" which is the integer 2.
Given an integer n, return its complement.

 

Example 1:

Input: n = 5
Output: 2
Explanation: 5 is "101" in binary, with complement "010" in binary, which is 2 in base-10.
Example 2:

Input: n = 7
Output: 0
Explanation: 7 is "111" in binary, with complement "000" in binary, which is 0 in base-10.
Example 3:

Input: n = 10
Output: 5
Explanation: 10 is "1010" in binary, with complement "0101" in binary, which is 5 in base-10.
 

Constraints:

0 <= n < 109
 

*/

class Solution {
    private int originalSolution(int n){
        int ans = n;
        
	// log count is the number of bits in n
	// at every iteration count is divided by 2
	// digit is the bit place where xor with 1 is to be done
        int digit = 0, count = n;
        while(count !=0){
	    // xor with 1 will result in complement as 
	    // 0 ^ 1 = 1 and 1 ^ 1 = 0
            ans ^= (1 << digit);
            digit++;
            count /= 2;
        }
        return ans;
    }
    private int lcSolution(int n){
	 // instead of xor ing with 1 at every bit
	 // directly create number with all ones and then xor it with n
         int allOnes = 1;
	 while (n > allOnes){
	     x = (x << 1) + 1;
	 }
	 return n ^ x;
    }
    public int bitwiseComplement(int n) {
	// 0 is edge case where output > input
        if(n == 0){
            return 1;
        }
	
	//return originalSolution(n);
	return lcSolution(n);
    }
}
