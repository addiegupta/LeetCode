/*

LeetCode: 560. Subarray Sum Equals K

Medium

Link: https://leetcode.com/problems/subarray-sum-equals-k/

Topics: Array, Hash Table, Prefix Sum

Acceptance: 44.2

Given an array of integers nums and an integer k, return the total number of subarrays whose sum equals to k.

 

Example 1:

Input: nums = [1,1,1], k = 2
Output: 2
Example 2:

Input: nums = [1,2,3], k = 3
Output: 2
 

Constraints:

1 <= nums.length <= 2 * 104
-1000 <= nums[i] <= 1000
-107 <= k <= 107
 
*/
class Solution {
    public int subarraySum(int[] nums, int k) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        //return squaredTLESolution(nums, k);
        return prefixSumsMapSolution(nums, k);
    }
    private int prefixSumsMapSolution(int[] nums, int k){
        int n = nums.length;
        int ans = 0;
        int currSum = 0;
        Map<Integer, Integer> prefixSums = new HashMap();
        // empty prefix, sum 0
        prefixSums.put(0, 1);
        for(int num : nums){
            currSum += num;
            // look for any prefix sums which if removed from total sum of (0, i), sum will == k
            // i.e. if we remove a certain prefix sum of currSum -k , we will get a subarray with sum k
            ans += prefixSums.getOrDefault(currSum - k, 0);
            prefixSums.put(currSum, prefixSums.getOrDefault(currSum, 0) + 1);
        }
        return ans;
    }
    
    // this is probably not even a proper dp solution, simple brute force could have done same
    private int squaredTLESolution(int[] nums, int k){
        int ans = 0;
        int n = nums.length;
        int[][] dp = new int[n][n];
        for(int i = 0 ; i < n; i++){
            for(int j = 0; j < n - i; j++){
                int row = j;
                int col = i + j;
                dp[row][col] = nums[col];
                if(row != col){
                    dp[row][col] += dp[row][col - 1];
                }
                if(dp[row][col] == k){
                    ans++;
                }
            }
        }
        return ans;
    }
}
