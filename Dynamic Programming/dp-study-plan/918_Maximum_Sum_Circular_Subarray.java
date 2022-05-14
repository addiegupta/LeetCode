/*

LeetCode: 918. Maximum Sum Circular Subarray

Medium

Link: https://leetcode.com/problems/maximum-sum-circular-subarray/

Topics: Dynamic Programming, Array, Divide and Conquer, Queue, Monotonic Queue

Acceptance: 36.7

 Given a circular integer array nums of length n, return the maximum possible sum of a non-empty subarray of nums.

A circular array means the end of the array connects to the beginning of the array. Formally, the next element of nums[i] is nums[(i + 1) % n] and the previous element of nums[i] is nums[(i - 1 + n) % n].

A subarray may only include each element of the fixed buffer nums at most once. Formally, for a subarray nums[i], nums[i + 1], ..., nums[j], there does not exist i <= k1, k2 <= j with k1 % n == k2 % n.

 

Example 1:

Input: nums = [1,-2,3,-2]
Output: 3
Explanation: Subarray [3] has maximum sum 3.
Example 2:

Input: nums = [5,-3,5]
Output: 10
Explanation: Subarray [5,5] has maximum sum 5 + 5 = 10.
Example 3:

Input: nums = [-3,-2,-3]
Output: -2
Explanation: Subarray [-2] has maximum sum -2.
 

Constraints:

n == nums.length
1 <= n <= 3 * 104
-3 * 104 <= nums[i] <= 3 * 104

 
 */

class Solution {
    // Time: O(n)
    // Space: O(1)
    // 2 cases possible for circular
    // 1. max sum subarray is present in the normal linear array: use normal Kadane's algo
    // 2. max sum subarray wraps around on edges i.e. left and right edges form the subarray:
    // for this case, using a variation of Kadane's algo, calculate the min sum subarray and subtract it 
    // from total sum to get max sum subarray wrapped at edges
    // e.g. 1. [- - - # # # # - - -]
    //      2. [# # - - - - # # # #]
    //      in case 2, - - would be min subarray and subtracting it from total would give ans
    public int maxSubarraySumCircular(int[] nums) {
        if(nums == null){
            return 0;
        }
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        int totalSum = 0;
        int sum = 0;
        // Simple Kadane's algorithm for maximum sum subarray ignoring circular property
        for(int i = 0; i < n; i++){
            sum += nums[i];
            totalSum += nums[i];
            maxSum = Math.max(sum, maxSum);
            if(sum < 0){
                sum = 0;
            }
        }
        
        // min subarray for circular case
        int minSum = 0;
        sum = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            minSum = Math.min(sum, minSum);
            if(sum > 0){
                sum = 0;
            }
        }
        // intervalsum is for the wrap around case
        int intervalSum = totalSum - minSum;
        // in case all elements are negative, interval sum would be 0
        // for this, return the max possible value which would be maxSum
        if(intervalSum == 0){
            return maxSum;
        }
        return Math.max(maxSum, intervalSum);
    }
}
