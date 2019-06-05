/*

LeetCode: 217. Contains Duplicate

Easy

Link: https://leetcode.com/problems/contains-duplicate/

Topics: Array,Hash Table

Acceptance: 51.9

Given an array of integers, find if the array contains any duplicates.

Your function should return true if any value appears at least twice in the array, and it should return false if every element is distinct.

Example 1:

Input: [1,2,3,1]
Output: true
Example 2:

Input: [1,2,3,4]
Output: false
Example 3:

Input: [1,1,1,3,3,4,3,2,4,2]
Output: true

 */

class Solution {
    public boolean containsDuplicate(int[] nums) {
        // if(nums==null || nums.length==0)return false;
        // Set<Integer> set = new HashSet<>();
        // int n = nums.length;
        // for(int i=0;i<n;i++){
            // if(set.contains(nums[i]))return true;
            // set.add(nums[i]);
        // }
        // return false;
        
        Arrays.sort(nums);
        for(int i=1;i<nums.length;i++){
            if(nums[i]==nums[i-1])return true;
        }
        return false;
        
    }
}