/*

LeetCode: 1567. Maximum Length of Subarray With Positive Product

Medium

Link: https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/

Topics: Dynamic Programming, Array, Greedy

Acceptance: 42.3

Given an array of integers nums, find the maximum length of a subarray where the product of all its elements is positive.

A subarray of an array is a consecutive sequence of zero or more values taken out of that array.

Return the maximum length of a subarray with positive product.

 

Example 1:

Input: nums = [1,-2,-3,4]
Output: 4
Explanation: The array nums already has a positive product of 24.
Example 2:

Input: nums = [0,1,-2,-3,-4]
Output: 3
Explanation: The longest subarray with positive product is [1,-2,-3] which has a product of 6.
Notice that we cannot include 0 in the subarray since that'll make the product 0 which is not positive.
Example 3:

Input: nums = [-1,-2,-3,0,1]
Output: 2
Explanation: The longest subarray with positive product is [-1,-2] or [-2,-3].
 

Constraints:

1 <= nums.length <= 105
-109 <= nums[i] <= 109
 
 */

class Solution {
    public int getMaxLen(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        int n = nums.length;
        int max = 0;
        // store running max length for which product is positive and negative
        // this is useful because a negative length multiplied by another negative num
        // will result in a positive, hence that can cause max positive length
        int positiveLength = 0;
        int negativeLength = 0;
        
        for(int num : nums){
            
            // encountered 0; reset lengths as 0 will not be part of answer; product would not be positive
            if(num == 0){
                positiveLength = 0;
                negativeLength = 0;
            } else if(num < 0) {
                int temp = positiveLength;
                // if positive length is 0, adding another negative wont make length positive
                positiveLength = negativeLength == 0 ? 0 : negativeLength + 1;
                negativeLength = temp + 1;
                
            } else {
                positiveLength++;
                negativeLength = negativeLength == 0 ? 0 : negativeLength + 1;
            }
            max = Math.max(max, positiveLength);
        }
        return max;
    }
}
