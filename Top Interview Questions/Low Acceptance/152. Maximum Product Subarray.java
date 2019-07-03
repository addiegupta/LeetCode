/*

LeetCode: 152. Maximum Product Subarray

Medium

Link: https://leetcode.com/problems/maximum-product-subarray/

Topics: Array, Dynamic Programming

Acceptance: 29.5

Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.

Example 1:

Input: [2,3,-2,4]
Output: 6
Explanation: [2,3] has the largest product 6.
Example 2:

Input: [-2,0,-1]
Output: 0
Explanation: The result cannot be 2, because [-2,-1] is not a subarray.

 
 */
class Solution {
    public int maxProduct(int[] nums) {
        
		if(nums==null || nums.length==0)return 0;
        int n = nums.length,temp;
        // Init variables to first value in array
		int ans = nums[0];
		// Min product is stored as multiplying with negative val will make it max
        int productMax = ans,productMin = ans;
        for(int i=1;i<n;i++){
			// For negative value, max and min values will be reversed in their product
            if(nums[i]<0){
                temp = productMax;
                productMax = productMin;
                productMin = temp;
            }
            
			// max value till here is either previous max *ed with current val OR just current val; similar for min
			// As subarray should be contiguous, hence we either keep previous product and multiply current val or discard it 
			// completely and start a new product from this index
            productMax = Math.max(nums[i],productMax*nums[i]);
            productMin = Math.min(nums[i],productMin*nums[i]);
            ans = Math.max(productMax,ans);
        }
        return ans;
    }
}