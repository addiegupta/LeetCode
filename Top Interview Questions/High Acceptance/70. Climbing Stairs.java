/*

LeetCode: 70. Climbing Stairs

Easy

Link: https://leetcode.com/problems/climbing-stairs

Topics: Dynamic Programming

Acceptance: 44.2

You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

 */
class Solution {
    public int climbStairs(int n) {
        int curr,prev1=1,prev2=1;
        for(int i=1;i<n;i++){
            // There are 2 ways to reach this step; take 1 step from previous or 2 steps from 2 steps below
			curr=prev1+prev2;
            prev2=prev1;
            prev1=curr;
        }
        return prev1;
    }
}