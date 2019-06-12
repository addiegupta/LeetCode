/*

LeetCode: 162. Find Peak Element

Medium

Link: https://leetcode.com/problems/find-peak-element/

Topics: Array, Binary Search

Acceptance: 41.3

A peak element is an element that is greater than its neighbors.

Given an input array nums, where nums[i] ≠ nums[i+1], find a peak element and return its index.

The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

You may imagine that nums[-1] = nums[n] = -∞.

Example 1:

Input: nums = [1,2,3,1]
Output: 2
Explanation: 3 is a peak element and your function should return the index number 2.
Example 2:

Input: nums = [1,2,1,3,5,6,4]
Output: 1 or 5 
Explanation: Your function can return either index number 1 where the peak element is 2, 
             or index number 5 where the peak element is 6.
Note:

Your solution should be in logarithmic complexity.

LeetCode Article: https://leetcode.com/articles/find-peak-element/

*/

// Interpretation:  Only next element needs to be compared to current and there is no need to check if current is greater
//                  than previous as well. This is because we will reach current value only if it was greater than previous
//                  Total 3 cases can be made: ascending range,descending range and a mix of both
//                  in ascending, last element is ans ; in descending, first element is ans
//                  In 3rd case answer is in between the array somewhere; it can be located using binary search

class Solution {
    public int findPeakElement(int[] nums) {
        int l=0,r=nums.length-1,mid;
        while(l<r){
            mid = l + (r-l)/2;
            //Either this element is peak or peak is to left
            if(nums[mid]>nums[mid+1]) r=mid;
            else l= mid+1;
        }
        return l;        
    }
}