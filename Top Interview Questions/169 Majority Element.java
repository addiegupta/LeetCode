/*

LeetCode: 169. Majority Element

Link: https://leetcode.com/problems/majority-element/

Topics: Array,Divide and Conquer, Bit Manipulation

Acceptance: 52.6

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2 

 */
class Solution {
    public int majorityElement(int[] nums) {

        // Select first as majority element
        int ans  = nums[0],count=1;
        int n = nums.length;
        
        for(int i=1;i<n;i++){
            
            // Found ans
            if(count>n/2)return ans;
            
            // Count occurences of element, at end we will obtain majority element
            if(nums[i]==ans)count++;
            // Decrement for non majority element
            else count--;
            if(count==0){
                ans = nums[i];
                count++;
            }
        }
        return ans;
    }
}