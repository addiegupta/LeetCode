/*

LeetCode: 283 Move Zeroes   

Link: https://leetcode.com/problems/move-zeroes/

Topics: Array, Two Pointers

Acceptance: 54.3

Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

Example:

Input: [0,1,0,3,12]
Output: [1,3,12,0,0]
Note:

You must do this in-place without making a copy of the array.
Minimize the total number of operations.

 */
class Solution {
    
     public void moveZeroes(int[] nums) {
    
        if (nums == null || nums.length < 1) return;
        
        int start=0,count=0;
        int n = nums.length;
        
        for(int i=0;i<n;i++){
            // Count the zeroes
            if(nums[i]==0)count++;
            // Set other numbers at start of array
            else{
                nums[start++]=nums[i];
            }
        }
        // Insert 0s at end
        for(int i=0;i<count;i++)nums[n-i-1]=0;
    }
}