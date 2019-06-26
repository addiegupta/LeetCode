/*

LeetCode: 34. Find First and Last Position of Element in Sorted Array

Medium

Link: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/

Topics: String, Backtracking

Acceptance: 33.7

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]


 */
 
class Solution {
	
    public int[] searchRange(int[] nums, int target) {
        // return firstSolution(nums,target);
        return leetCodeSolution(nums,target);
    }
	// Instead of searching for target( say target is 4) 
	// search for 3.5 for left index and for 4.5 for right index
    private int[] firstSolution(int[] nums, int target){
        int[] ans = {-1, -1};
       
        if(nums==null||nums.length==0)return ans; 
		
		// Check if number exists in array
        int pos = binarySearchForFirstSol(nums,target);
        if(nums[pos]!=target)return ans;

		// Find leftmost index
        ans[0] = binarySearchForFirstSol(nums,(float)(target-0.5));        
		if(nums[ans[0]]!=target)ans[0]++;

		// FInd rightmost index
        ans[1] = binarySearchForFirstSol(nums,(float)(target+0.5));
        if(nums[ans[1]]!=target)ans[1]--;
        return ans;
     
    }
	private int binarySearchForFirstSol(int[] nums ,float target){
        int start=0,end=nums.length-1,mid=-1;
        // System.out.println("Target is " + target);
        float curr;
        while(start<=end){
            mid = start + (end-start)/2;
            curr = (float)(nums[mid]);
            // System.out.println("curr value is "+ curr);
            if(curr==target)return mid;
            else if(curr>target)end=mid-1;
            else start = mid+1;
        }
        return mid;
    }
	
	// Uses binary search but instead of terminating search when target found,
	// search keeps on going left or right for finding start or end respectively
    private int[] leetCodeSolution(int[] nums,int target){
        int[] ans = {-1,-1};
        int leftIndx = binarySearchLeetSol(nums,target,true);
        
		// Target does not exist
        if(leftIndx == nums.length || nums[leftIndx] != target){
            return ans;
        }
        
        ans[0] = leftIndx;
		// Find end index
        ans[1] = binarySearchLeetSol(nums,target,false)-1;
        return ans;
    }
	// boolean left tells if we are searching for leftmost or rightmost index
    private int binarySearchLeetSol(int[] nums,int target,boolean left){
        int lo =0;
        int hi = nums.length;
        int mid;
        while(lo<hi){
            mid = lo + (hi-lo)/2;
            if(nums[mid]>target || (left && target == nums[mid])) hi = mid;
            else lo = mid + 1;
        }
        return lo;
    }
}