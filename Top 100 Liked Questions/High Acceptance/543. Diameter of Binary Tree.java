/*

LeetCode: 543. Diameter of Binary Tree

Easy

Link: https://leetcode.com/problems/diameter-of-binary-tree/

Topics: Tree

Acceptance: 47.2

Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest path between any two nodes in a tree. This path may or may not pass through the root.

Example:
Given a binary tree 
          1
         / \
        2   3
       / \     
      4   5    
Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by the number of edges between them.
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
    int ans=1;
    public int diameterOfBinaryTree(TreeNode root) {
        goDeep(root);
        // The number of edges is required and not nodes
        return ans-1;
    }
    private int goDeep(TreeNode root){
        if(root==null)return 0;

        int left = goDeep(root.left);
        int right = goDeep(root.right);
        // Global answer
        ans = Math.max(ans,1+left+right);
        
        // Value that can be used by parent
        return Math.max(left,right)+1;
    }
}