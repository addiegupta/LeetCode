/*

LeetCode: 324. Wiggle Sort II

Medium

Link: https://leetcode.com/problems/wiggle-sort-ii/

Topics: Sort

Acceptance: 28.3

Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....

Example 1:

Input: nums = [1, 5, 1, 1, 6, 4]
Output: One possible answer is [1, 4, 1, 5, 1, 6].
Example 2:

Input: nums = [1, 3, 2, 2, 3, 1]
Output: One possible answer is [2, 3, 1, 3, 1, 2].
Note:
You may assume all input has valid answer.

Follow Up:
Can you do it in O(n) time and/or in-place with O(1) extra space?

 */


class Solution {
    public void wiggleSort(int[] nums) {
        if(nums.length==0)return;
        int n = nums.length;
        int[] temp = Arrays.copyOf(nums,nums.length);
        Arrays.sort(temp);
        int hi = n-1;
        int limit = n/2-1;
        if(n%2!=0)limit++;
        int lo = limit;
        int k=0;
        while(lo>=0 || hi>limit){
            if(lo>=0) nums[k++] = temp[lo--];
            if(hi>limit) nums[k++] = temp[hi--];
        }
    }
}