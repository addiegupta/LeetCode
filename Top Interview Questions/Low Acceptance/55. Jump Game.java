/*

LeetCode: 55. Jump Game

Medium

Link: https://leetcode.com/problems/coin-change/

Topics:Array, Greedy

Acceptance: 32.1

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
             jump length is 0, which makes it impossible to reach the last index.

 */
class Solution {
    // Dp is not optimum in this problem, greedy is the better way
    // DP solution given just for reference
    private dpSolution(int[] nums){
        int n = nums.length;
        boolean dp[] = new boolean[n];
        
        // Last index can be reached if started from last index
        dp[n-1]=true;
        
        int farthest;
        for(int i=n-2;i>=0;i--){
            // Get the farthest jump from this index
            farthest = Math.min(n-1,i+nums[i]);
            // Recur for following indices which can be jumped from ith index
            for(int j=i+1;j<=farthest;j++){
                // An index found from which last element can be reached; set dp[i] to true
                if(dp[j]){
                    dp[i]=true;
                    break;
                }
            }
        }
        // Starting from 0th index , last index can be reached is dp[0] value
        return dp[0];
    }
    private greedySolution(int[] nums){
        // marks the farthest index that can be reached
        int reachable =0;
        for(int i=0;i<nums.length;i++){
            // No point starting from this index as it cannot be reached from start
            if(i>reachable) return false;
            // Maximise reachable position
            reachable = Math.max(reachable,i+nums[i]);
        }
        // All indices iterated; last element can be reached
        return true;
    }
    public boolean canJump(int[] nums) {
        // O(n^2) slow and lengthy code
        // return dpSolution(nums);

        // O(n) fast and compact code
        return greedySolution(nums);
    }
}