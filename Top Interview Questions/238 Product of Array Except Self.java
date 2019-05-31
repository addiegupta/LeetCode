/*

LeetCode: 238. Product of Array Except Self   

Medium

Link: https://leetcode.com/problems/product-of-array-except-self/

Acceptance: 55.0

Topics: Array

Given an array nums of n integers where n > 1,  return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].

Example:

Input:  [1,2,3,4]
Output: [24,12,8,6]
Note: Please solve it without division and in O(n).

Follow up:
Could you solve it with constant space complexity? (The output array does not count as extra space for the purpose of space complexity analysis.)

*/



class Solution {
    public int[] productExceptSelf(int[] nums) {
        
        int n  = nums.length;
        int[] ans = new int[n];
        
        // Creating prefix array i.e. at every index the product of all elements till this index (excluding) is stored
        ans[0]=1;
        for(int i=1;i<n;i++){
            ans[i]=nums[i-1]*ans[i-1];
        }

        // In effect creating suffix array without actually using more memory
        int right = 1;
        for(int i=n-1;i>=0;i--){
            ans[i]*=right;
            right *= nums[i];
        }
        return ans;
    }
}