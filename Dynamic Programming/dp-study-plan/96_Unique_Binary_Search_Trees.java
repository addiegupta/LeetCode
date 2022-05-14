/*

LeetCode: 93 Unique Binary Search Trees

Medium

Link: https://leetcode.com/problems/unique-binary-search-trees/

Topics: Dynamic Programming, Math, Tree, Binary Search Tree

Acceptance: 58.1

Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.

 

Example 1:


Input: n = 3
Output: 5
Example 2:

Input: n = 1
Output: 1
 

Constraints:

1 <= n <= 19 

 */

class Solution {
    public int numTrees(int n) {
        if(n <= 0){
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            for(int j = 0; j < i; j++){
                // if j is num of nodes on left
                // i - j - 1 is num of nodes on right
                // then, we can get the number of unique BSTs formed using this distribution of left and right
                // left can range from 0 to i - 1
                // for every structure on left, we can have 1 combination each with right structures hence multiplying
		// the value of node is irrelevant since all numbers are unique
		// and the answer does not require unique, it needs STRUCTURALLY unique
                int left = dp[j];
                int right = dp[i - j - 1];
                dp[i] += (left * right);
            }
        }
        return dp[n];
    }
}
