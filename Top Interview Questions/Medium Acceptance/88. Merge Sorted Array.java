/*

LeetCode: 88. Merge Sorted Array

Easy

Link: https://leetcode.com/problems/merge-sorted-array/

Topics: Array, Two Pointers

Acceptance: 35.9

Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]


*/
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int a = m-1,b=n-1,c=m+n-1;

        while(a>=0 && b>=0){
            if(nums1[a]>=nums2[b]) nums1[c--]=nums1[a--];
            else nums1[c--]=nums2[b--];
        }
        // This loop (which is for copying over remaining elements when one array gets exhausted)
        // is not duplicated for nums1 as its elements are already present in nums1 which is the final array as well
        while(b>=0)nums1[c--]=nums2[b--];
    }
}