/*

LeetCode: 1486. XOR Operation in an Array

Easy

Link: https://leetcode.com/problems/xor-operation-in-an-array/

Topics: Math, Bit Manipulation

Acceptance: 83

You are given an integer n and an integer start.

Define an array nums where nums[i] = start + 2 * i (0-indexed) and n == nums.length.

Return the bitwise XOR of all elements of nums.

 

Example 1:

Input: n = 5, start = 0
Output: 8
Explanation: Array nums is equal to [0, 2, 4, 6, 8] where (0 ^ 2 ^ 4 ^ 6 ^ 8) = 8.
Where "^" corresponds to bitwise XOR operator.
Example 2:

Input: n = 4, start = 3
Output: 8
Explanation: Array nums is equal to [3, 5, 7, 9] where (3 ^ 5 ^ 7 ^ 9) = 8.
 

Constraints:

1 <= n <= 1000
0 <= start <= 1000
n == nums.length

*/

class Solution { 
    // dont actually need to create array, just keep xoring with same variable
    public int xorOperation(int n, int start) {
        int ans = 0;
        int i = 0;
        while(n > i) {
            ans ^= (2*i + start);
            i++;
        }
        return ans;
    }
}
