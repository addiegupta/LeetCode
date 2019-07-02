/*

LeetCode: 7. Reverse Integer

Easy

Link: https://leetcode.com/problems/reverse-integer/

Topics: Math

Acceptance: 25.4

Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. 
For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

 */
class Solution {
    public int reverse(int x) {
        return usingLongMethod(x);
        return withoutLongMethod(x);
    }
    private int usingLongMethod(int x){ 
        long ans = 0;
        while(x!=0){
            ans = ans *10 + x%10;
            x=x/10;
        }
		// Check for overflow only once as long can accomodate
        if(ans<Integer.MIN_VALUE || ans> Integer.MAX_VALUE)return 0;
        
        return (int)ans;
    }
    private int withoutLongMethod(int x){
        int prevRev = 0 , rev= 0;
        while( x != 0){
            rev= rev*10 + x % 10;
			// Overflow has occured if previous value does not match
            if((rev - x % 10) / 10 != prevRev){
                return 0;
            }
            prevRev = rev;
            x= x/10;
        }
        return rev;
    }
}