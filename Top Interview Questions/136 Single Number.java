/*

LeetCode: 136. Single Number   

Link: https://leetcode.com/problems/single-number/

Acceptance: 60.1

Topics: Hash Table, Bit Manipulation

Given a non-empty array of integers, every element appears twice except for one. Find that single one.

Note:

Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?

Example 1:

Input: [2,2,1]
Output: 1
Example 2:

Input: [4,1,2,1,2]
Output: 4

*/

// Java
class Solution {
    public int singleNumber(int[] nums) {
        int xor =0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            xor ^= nums[i];
        }
        return xor;
    }
}


/*
// C++
class Solution {
public:
    int singleNumber(vector<int>& nums) {
        int x = 0;
        for(auto it = nums.begin();it!=nums.end();it++)x ^= *it;
        return x;
    }
};
*/
