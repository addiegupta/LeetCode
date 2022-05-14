/*

LeetCode: 35. Search Insert Position

Easy

Link: https://leetcode.com/problems/search-insert-position/

Topics: Array, Binary Search

Acceptance: 42.3

Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You must write an algorithm with O(log n) runtime complexity.

 

Example 1:

Input: nums = [1,3,5,6], target = 5
Output: 2
Example 2:

Input: nums = [1,3,5,6], target = 2
Output: 1
Example 3:

Input: nums = [1,3,5,6], target = 7
Output: 4
 

Constraints:

1 <= nums.length <= 104
-104 <= nums[i] <= 104
nums contains distinct values sorted in ascending order.
-104 <= target <= 104 
 
 */

class Solution {
    public int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int lo = 0;
        int hi = n - 1;
        while(lo <= hi){
            int mid = lo + (hi - lo) / 2;
            if(nums[mid] == target){
                return mid;
            } else if(nums[mid] > target){
                hi = mid - 1;
            } else{
                lo = mid + 1;
            }
        }
	// if element has not been found, its correct insert position would be lo
	// e.g. array is [2] , target is 1
	// lo at end would be 0 which is correct position as current element is greater and would be replaced
	// if target was 3, lo at end would be 1 which is correct position as element would be inserted afterwards
        return lo;
    }
}
