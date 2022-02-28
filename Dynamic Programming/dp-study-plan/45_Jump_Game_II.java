/*

LeetCode: 45. Jump Game II

Medium

Link: https://leetcode.com/problems/jump-game-ii/

Topics: Dynamic Programming, Array, Greedy

Acceptance: 36.3

Given an array of non-negative integers nums, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

You can assume that you can always reach the last index.

 

Example 1:

Input: nums = [2,3,1,1,4]
Output: 2
Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [2,3,0,1,4]
Output: 2
 

Constraints:

1 <= nums.length <= 104
0 <= nums[i] <= 1000

 */
class Solution {
    
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        
        //return initialDpSolution(nums);
        return newGreedySolution(nums);
    }
    
    // Time: O(n): 1 iteration through array
    // Space: O(1) few variables
    private int newGreedySolution(int[] nums){
        int n = nums.length;
        // current end of limit for the jumps made till now
        int end = 0;
        // farthest possible to go based on current jump limits encountered
        int farthest = 0;
        // jumps to be made
        int count = 0;
        for(int i = 0; i < n - 1; i++){
            farthest = Math.max(farthest, i + nums[i]);
            // end reached for current jumps made, update and and make one more jump
            // this does not necessarily mean that new jump is being made from step i
            // it could be made anywhere betweeen previous end value and current end value
            if(end == i) {
                count += 1;
                end = farthest;
            }
        }
        return count;
    }
    
    // Time: O(n^2) for every value, its subsequent steps ahead of it that can be reached are updated
    // Space: O(n) 1d array for every step
    private int initialDpSolution(int[] nums){
        int n = nums.length;
        int[] dp = new int[n];
        
        // steps to reach i from 0
        for(int i = 1; i < n; i++){
            dp[i] = Integer.MAX_VALUE;
        }
        for(int i = 0 ; i < n - 1; i++){
            // this step cannot be reached
            if(dp[i]==Integer.MAX_VALUE){
                continue;
            }
            // max reach from current step
            int reach = nums[i];
            // count of jumps
            int count = dp[i] + 1;
            for(int j = i + 1; j <= (i + reach) && j < n; j++){
                dp[j] = Math.min(dp[j], count);
            }
        }
        return dp[n-1];
    }
}
