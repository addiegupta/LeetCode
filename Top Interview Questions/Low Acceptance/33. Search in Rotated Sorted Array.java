/*

LeetCode: 33. Search in Rotated Sorted Array

Medium

Link: https://leetcode.com/problems/search-in-rotated-sorted-array/

Topics: Array, Binary Search

Acceptance: 33.0

Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1


 */
 class Solution {
	 // Finds position where array is rotated
    private int getPivot(int[] nums){
        int n= nums.length;
        int l=0,r=n-1;
        int mid=0,prev;
			
		// Look for the smallest element in the array which will be the 
		// position where array is rotated
        while(l<r){
            mid = l + (r-l)/2;
            if(nums[mid]>nums[r])l = mid +1;
            else r = mid;
        }
		
		// At l==r, position of rotation is found
        return l;
    }
    
    public int search(int[] nums, int target) {
        if(nums==null || nums.length==0)return -1;
        int pivot = getPivot(nums);
        
        int n= nums.length;
        int mid,realmid;
        int l = 0,h =n-1;
		
		// Binary search, realmid gives the actual index for mid by adding pivot value
		/*
			while(l<=h){
				mid = l + (h-l)/2;
				realmid = (mid+pivot)%n;
				if(nums[realmid]==target) return realmid;
				else if(nums[realmid]<target) l = mid+1;
				else h = mid-1;
			}
		*/
		// An alternative approach can be to compare target to nums[n-1] and then 
		// apply ordinary binary search only on left of pivot or right of pivot 
        if(target<=nums[n-1])l=pivot;
        else h = pivot-1;
		while(l<=h){
			mid = l + (h-l)/2;
			if(nums[mid]==target)return mid;
			else if(nums[mid] < target)l=mid+1;
			else h = mid-1;
		}
        return -1;
    }
}