/*

LeetCode: 416. Partition Equal Subset Sum

Medium

Link: https://leetcode.com/problems/partition-equal-subset-sum/

Topics: Array, Dynamic Programming

Acceptance: 46.4

 Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

 

Example 1:

Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].
Example 2:

Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
 

Constraints:

1 <= nums.length <= 200
1 <= nums[i] <= 100

 */

class Solution {
    public boolean canPartition(int[] nums) {
        // sum all elements
        if(nums == null || nums.length == 0){
            return true;
        }
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
        }
        // cannot divide odd sum equally in 2 halves
        if(sum%2!=0){
            return false;
        }
        
        // problem boils down to check if certain elements add up to  sum / 2
        // since if one subset forms sum: sum/2, then other set will also necessarily form sum/2
	// similar to 0/1 knapsack, either pick num or dont pick as part of sum for subset
        int target = sum / 2;
        // dp[i][j] true represents if only nums upto i are available, the sum j can be formed
        // hence dp[n-1][target] should be our answer, or even if for any i before n, target is formed
        boolean[][] dp = new boolean[n][target + 1];
        for(int i = 0 ; i < n; i++){
            int num = nums[i];
            if(num <= target){
                dp[i][num] = true;
            }
            if(i != 0){
                for(int j = 0; j <= target;j++){
                    // j - num >= 0: valid index check
                    // dp[i-1][j-num]: sum j- num can be formed, adding num would form sum j
                    // dp[i - 1][j]:  sum j can be formed without using num at all
                    if(j - num >= 0 && dp[i-1][j - num] || dp[i-1][j]){
                        dp[i][j] = true;
                    }
                }
            }
            if(dp[i][target]){
                return true;
            }
        }
        return false;
    }
}
