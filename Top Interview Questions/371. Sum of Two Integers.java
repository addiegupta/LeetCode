/*

LeetCode: 371. Sum of Two Integers  

Easy

Link: https://leetcode.com/problems/sum-of-two-integers/

Topics: Bit Manipulation

Acceptance: 50.9

Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.

Example 1:

Input: a = 1, b = 2
Output: 3
Example 2:

Input: a = -2, b = 3
Output: 1

 */
class Solution {
    public int getSum(int a, int b) {
        
        // One line recursive solution
        //return (b == 0) ? a : getSum(a ^ b, (a & b) << 1);
       
        while(b!=0){
        
            // Carry is obtained by ANDing the bits
            int c = a&b;
            // Final sum value is obtained by XORing the bits
            a=a^b;
            // b is the new carry value obtained by left shifting by 1
            // next iteration onwards b is the carry to be added
            b=c<<1;
        }
 
        return a;
        
    }
}