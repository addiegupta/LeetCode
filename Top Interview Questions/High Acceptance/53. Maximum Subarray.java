/*

LeetCode: 53. Maximum Subarray  

Easy

Link: https://leetcode.com/problems/maximum-subarray/

Acceptance: 43.6

Topics: Array, Divide and Conquer, Dynamic Programming

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

References:
    Divide and Conquer Method https://leetcode.com/problems/maximum-subarray/discuss/20225/My-Divide-and-Conquer-Solution-in-Java-under-instruction-of-CLRS(O(nlogn))

*/

class Solution {
	
    private int newCleanSolution(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int n = nums.length;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        for(int i = 0; i < n; i++){
            sum += nums[i];
            max = Math.max(max, sum);
            if(sum < 0){
                sum = 0;
            }
        }
        return max;
    }
    public int maxSubArray(int[] nums) {
	return newCleanSolution(nums);
	
	//
	//
        // globalMax and maxValue variables can be merged 
        int currMax=0,maxValue=Integer.MIN_VALUE;
        int globalMax=0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            if(nums[i]>maxValue)maxValue =nums[i];
            currMax+=nums[i];
            if(currMax>globalMax){
                globalMax = currMax;
            }
            if(currMax<0){
                currMax=0;
            }
        }
        return (globalMax==0)?maxValue:globalMax;
    }
}
