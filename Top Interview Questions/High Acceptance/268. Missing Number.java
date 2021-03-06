/*

LeetCode: 268. Missing Number

Easy

Link: https://leetcode.com/problems/missing-number/

Topics: Array,Math,Bit Manipulation 

Acceptance: 48.4

Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.

Example 1:

Input: [3,0,1]
Output: 2
Example 2:

Input: [9,6,4,2,3,5,7,0,1]
Output: 8
Note:
Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?

*/

class Solution {
    public int missingNumber(int[] nums) {

        // Each number will be XORed twice with ans and hence will get canceled out of ans
        // Except the missing number which will be only XORed only once
        int n = nums.length;
        int ans = n;
        for(int i=0;i<n;i++){
            ans = ans ^ i ^ nums[i];
        }
        return ans;
    }
}