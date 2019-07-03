/*

LeetCode: 124. Binary Tree Maximum Path Sum

Hard

Link: https://leetcode.com/problems/binary-tree-maximum-path-sum/

Topics: Tree, Depth First Search

Acceptance: 30.3

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6
Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
 
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int ans=Integer.MIN_VALUE;
    
    private int dfs(TreeNode root){
        if(root == null)return 0;
        
		// Get max path sum for left and right subtrees
		// if path sum is negative, we set value as 0 which essentially means that this subtree should not be considered for path
        int left = Math.max(0,dfs(root.left));
        int right = Math.max(0,dfs(root.right));
		// Set the largest possible combination as ans
        ans = Math.max(ans,left+right+root.val);
		
		//Tricky---------------------------------------------------
		// left or right subtrees are not considered individually  without root node as then path would be broken
		// this is the reason why ans global variable is used( as answer is not necessarily traced back to root)
        return Math.max(left,right) + root.val;
    }
    
    public int maxPathSum(TreeNode root) {
        ans = Integer.MIN_VALUE;
        dfs(root);
        return ans;
    }
}