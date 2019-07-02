/*

LeetCode: 15. 3Sum

Medium

Link: https://leetcode.com/problems/3sum

Topics: Array, Two Pointers

Acceptance: 24.2

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]

 */
 class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
		
        List<List<Integer>> ans = new ArrayList();
        
		// Base case
		if(nums==null || nums.length==0)return ans;
        
		int n = nums.length;
        
		// Sort the array in O(n logn) which is less than O(n^2) which is the complexity of solution
		Arrays.sort(nums);
        int j,k,sum;
        
		// For every i (O(n)), adjust j and k together (either inc. j or dec. k) in O(n) hence total complexity is O(n^2)
		for(int i=0;i<n-2;i++){
			// Smallest is less than 0, no solution can be found further
            if(nums[i]>0)break;
			// Skip duplicates
            if(i>0 && nums[i]==nums[i-1])continue;
            
			// j and k are in remaining array to right
			j=i+1;
            k=n-1;
            sum = 0-nums[i];
            while(j<k){
				// Found a match, add to ans list
                if(nums[j] + nums[k] == sum){
                    ans.add(Arrays.asList(nums[i],nums[j],nums[k]));
					// Skip duplicates
                    while(j<k && nums[j]==nums[j+1])j++;
                    while(j<k && nums[k]==nums[k-1])k--;
                    j++;
                    k--;
                }
				// Find higher sum
                else if(nums[j] + nums[k] < sum){
                    j++;
                }
				// Find lower sum
                else{
                    k--;
                } 
            }
        }
        return ans;
    }
}