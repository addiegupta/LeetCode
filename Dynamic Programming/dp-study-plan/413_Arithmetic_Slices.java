/*

LeetCode: 413. Arithmetic Slices

Medium

Link: https://leetcode.com/problems/arithmetic-slices/ 

Topics: Dynamic Programming, Array

Acceptance: 62.2

An integer array is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.

For example, [1,3,5,7,9], [7,7,7,7], and [3,-1,-5,-9] are arithmetic sequences.
Given an integer array nums, return the number of arithmetic subarrays of nums.

A subarray is a contiguous subsequence of the array.

 

Example 1:

Input: nums = [1,2,3,4]
Output: 3
Explanation: We have 3 arithmetic slices in nums: [1, 2, 3], [2, 3, 4] and [1,2,3,4] itself.
Example 2:

Input: nums = [1]
Output: 0
 

Constraints:

1 <= nums.length <= 5000
-1000 <= nums[i] <= 1000

 */

class Solution {
    public int numberOfArithmeticSlices(int[] nums) {
        if(nums == null){
            return 0;
        }
        //return initialSquaredSolution(nums);
        return linearSolution(nums);
    }
    private int linearSolution(int[] nums){
        int n = nums.length;
        int count = 0;
        // length of current sequence which is an arithmetic slice
        // at every step, if a number is valid, it will form a combination 
        // with all previous subarrays
        // hence at every step, we will have curr additional combinations
        int curr = 0;
        for(int i = 2; i < n; i++){
            if(nums[i] - nums[i-1] == nums[i-1] - nums[i-2]){
                curr++;
                count += curr;
            }else{
                curr = 0;
            }
        }
        return count;
    }
    private int initialSquaredSolution(int[] nums){
        int count = 0;
        int n = nums.length;
        boolean [][] dp = new boolean[n][n];
        for(int i = 2; i < n; i++){
            for(int j = 0 ; j < n -i; j++){
                int row = j;
                int col = j + i;
                boolean isArithmetic = (nums[row + 1] - nums[row] == nums[col] - nums[col - 1]);
                if((col - row) > 2){
                    isArithmetic = isArithmetic && dp[row][col - 1] && dp[row + 1][col];
                }
                                        
                dp[row][col] = isArithmetic;
                if(isArithmetic){
                    count++;
                }
            }
        }
        return count;
        
    }
}
