/*

LeetCode: 338. Counting Bits

Medium

Link: https://leetcode.com/problems/counting-bits/

Topics: Bit Manipulation, Dynamic Programming

Acceptance: 65.4

Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.

 */
class Solution {
    public int[] countBits(int num) {
        int[] ans = new int[num+1];
        for(int i=1;i<=num;i++){
            // As >> operator is equal to dividing by 2 => i/2 will have same number of bits as i
            // if i is odd, then 1 needs to be added hence i%2
            // e.g. 7 has 3 set bits but 7/2 = 3 which has 2 set bits
            ans[i] = ans[i/2] + i%2;
        }
        return ans;
    }
}