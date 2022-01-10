/*

LeetCode: 213. House Robber II

Medium

Link: https://leetcode.com/problems/house-robber-ii/

Topics: Array, Dynamic Programming

Acceptance: 39.2

You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.

Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.

 

Example 1:

Input: nums = [2,3,2]
Output: 3
Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
Example 2:

Input: nums = [1,2,3,1]
Output: 4
Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
Total amount you can rob = 1 + 3 = 4.
Example 3:

Input: nums = [1,2,3]
Output: 3
 

Constraints:

1 <= nums.length <= 100
0 <= nums[i] <= 1000
 
*/
// this problem is a twist on House Robber 1
//
// at every step, either house is robbed (and added to prev to prev amount) or not robbed ( then prev sum is carry forwarded)
// so dp array, stores the max amount that can be robbed till this index
// since start and end are adjacent, 2 passes of the robbing iteration are done: 1 on start to end - 1 and 1 on start + 1 to end so that both start and end are not picked in the same pass
class Solution {
    private int initialHighMemorySolution(int[] nums){
        int n = nums.length;
        if(n<3){
            return Math.max(nums[0], nums[n-1]);
        }
        int[] rob = new int[n];
        int[] dontRob = new int[n];
        
        rob[0] = nums[0];
        dontRob[0] = 0;
        for(int i = 1; i < n; i++){
            rob[i] = dontRob[i-1] + nums[i];
            dontRob[i] = Math.max(rob[i - 1], dontRob[i-1]);
        }
        int dontRobMax = dontRob[n-1];
        
        rob[n-1] = nums[n-1];
        dontRob[n-1] = 0;
        for(int i = n - 2; i >=0; i--){
            rob[i] = dontRob[i+1] + nums[i];
            dontRob[i] = Math.max(rob[i + 1], dontRob[i + 1]);
        }
        dontRobMax = Math.max(dontRobMax, dontRob[0]);
        return dontRobMax;
    }
    private int helper(int[] nums, int start, int end){
        int prev = 0;
        int prevPrev = 0;
        for(int i = start; i <= end ; i++){
            int curr = Math.max(prev, prevPrev + nums[i]);
            prevPrev = prev;
            prev = curr;
        }
        return prev;
    }
	// as only 2 prev values are required, dp array can be replaced with 2 variables
    private int newSolution(int[] nums){
        int n = nums.length;
        if(n == 1) return nums[0];
        return Math.max(helper(nums, 0, n-2), helper(nums, 1, n-1));
    }
    public int rob(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        //return initialHighMemorySolution(nums);
        return newSolution(nums);
    }
}
