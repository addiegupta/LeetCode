/*

LeetCode: 448. Find All Numbers Disappeared in an Array

Easy

Link: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

Topics: Array

Acceptance: 53.9

Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.

Find all the elements of [1, n] inclusive that do not appear in this array.

Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.

Example:

Input:
[4,3,2,7,8,2,3,1]

Output:
[5,6]

 */
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        
        List<Integer> ans = new ArrayList();
        if(nums==null || nums.length==0)return ans;
        int n = nums.length;
       	
       	// Make element negative if its index+1 is present in array
        for(int i=0;i<n;i++){
            if(nums[Math.abs(nums[i])-1]>0)
            	nums[Math.abs(nums[i])-1]*=-1;
        }
        // if a number is positive, then the value corresponding to its index is not present in array
        for(int i=0;i<n;i++)if(nums[i]>0)ans.add(i+1);
        return ans;
    }
}