/*

LeetCode: 377. Combination Sum IV

Medium

Link: https://leetcode.com/problems/combination-sum-iv/

Topics: Dynamic Programming, Array

Acceptance: 48.9

Given an array of distinct integers nums and a target integer target, return the number of possible combinations that add up to target.

The test cases are generated so that the answer can fit in a 32-bit integer.

 

Example 1:

Input: nums = [1,2,3], target = 4
Output: 7
Explanation:
The possible combination ways are:
(1, 1, 1, 1)
(1, 1, 2)
(1, 2, 1)
(1, 3)
(2, 1, 1)
(2, 2)
(3, 1)
Note that different sequences are counted as different combinations.
Example 2:

Input: nums = [9], target = 3
Output: 0
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 1000
All the elements of nums are unique.
1 <= target <= 1000
 

Follow up: What if negative numbers are allowed in the given array? How does it change the problem? What limitation we need to add to the question to allow negative numbers?
 
 */
class Solution {
    public int combinationSum4(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        //return initialSquaredSpaceSolution(nums, target);
        return cleanLinearSpaceSolution(nums, target);
    }
    // Time: O(n ^ 2); for every number j in nums,
    // add the count of permutations to sum up till j (i.e. dp[j]) to dp[i]
    // as for every permutation [x] of j, 1 permutation for i would be [x , i - j]
    // e.g. to form 3
    //      from 1: [1, 2]
    //      from 2: [2, 1], [1, 1, 1]
    // Space: O(n)
    private int cleanLinearSpaceSolution(int[] nums, int target){
        int n = nums.length;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for(int i = 1; i <= target; i++){
            for(int j = 0; j < n; j++){
                int num = nums[j];
                // skip negative numbers as they are invalid for use case
                if(i - num >= 0){
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
    // Time: O(n ^ 2)
    // Space: O(n ^ 2)
    private int initialSquaredSpaceSolution(int[] nums, int target){
        int n = nums.length;
        int[][] dp = new int[target + 1][n];
        for(int i = 0; i <= target; i++){
            for(int j = 0; j < n; j++){
                if(i == 0){
                    dp[i][j] = 1;
                    continue;
                }
                int num = nums[j];
                if(i - num >= 0){
                    dp[i][j] += dp[i - num][n - 1];
                }
                if(j > 0){
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }
        return dp[target][n - 1];
    }
}
