/*

LeetCode: 334. Increasing Triplet Subsequence

Medium

Link: https://leetcode.com/problems/increasing-triplet-subsequence/

Topics: Array

Acceptance: 39.6



Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.

Formally the function should:

Return true if there exists i, j, k 
such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
Note: Your algorithm should run in O(n) time complexity and O(1) space complexity.

Example 1:

Input: [1,2,3,4,5]
Output: true
Example 2:

Input: [5,4,3,2,1]
Output: false

 */
class Solution {
    public boolean increasingTriplet(int[] nums) {
        int small=Integer.MAX_VALUE,big=Integer.MAX_VALUE;

        //for [1, 3, 0, 5] we will eventually arrive at big = 3 and small = 0 so big may come after small
        // However, the solution still works, because big only gets updated when there exists a small that comes before it.
        for(int n : nums){
            // Set small to this element if it is lesser
            if(n<=small)small=n;
            // Set big to this element if it is lesser than big; it is larger than 'small' as it did not execute the previous if statement
            else if(n<=big)big=n;
            // Number found bigger than big. return true
            else return true;
        }       
        return false;
    }
}