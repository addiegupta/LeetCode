/*

LeetCode: 494. Target Sum

Medium

Link: https://leetcode.com/problems/target-sum/solution/

Topics: Array, Dynamic Programming, Backtracking

Acceptance: 45.4

You are given an integer array nums and an integer target.

You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.

For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
Return the number of different expressions that you can build, which evaluates to target.

 

Example 1:

Input: nums = [1,1,1,1,1], target = 3
Output: 5
Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
-1 + 1 + 1 + 1 + 1 = 3
+1 - 1 + 1 + 1 + 1 = 3
+1 + 1 - 1 + 1 + 1 = 3
+1 + 1 + 1 - 1 + 1 = 3
+1 + 1 + 1 + 1 - 1 = 3
Example 2:

Input: nums = [1], target = 1
Output: 1
 

Constraints:

1 <= nums.length <= 20
0 <= nums[i] <= 1000
0 <= sum(nums[i]) <= 1000
-1000 <= target <= 1000 
 
 */
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0){
            return 0;
        }
        int sum = 0;
        for(int num : nums){
            sum += num;
        }
	// target greater than max possible sum
        if(Math.abs(target) > sum){
            return 0;
        }
        int n = nums.length;
        // taking twice range to allow negative sums
        int range = 2 * sum + 1;
        int[][] dp = new int[n][range];
        
        // adding sum in most places as offset to adjust for negative sums
	// so index j relates to dp value for a sum of : (j - sum)
        dp[0][nums[0]+ sum]++;
        dp[0][-nums[0]+ sum]++;
        
        for(int i = 1; i < n; i++){
            int num = nums[i];
	    // iterate over entire range of sums
            for(int j = -sum; j <= sum; j++){
		// adding or subtracting the current num from the sum represented by j and updating their dp values as per current num
		// i.e. ways to form sum
                int plus = (num + sum) + j;
                int minus = (-num + sum) + j;
                if(plus < range){
                    dp[i][j + sum] += dp[i-1][plus];
                }
                if(minus >= 0){
                    dp[i][j + sum] += dp[i-1][minus];
                }
            }
        }
        return dp[n - 1][target + sum];
    }
}
