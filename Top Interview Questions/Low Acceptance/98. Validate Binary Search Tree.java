/*

LeetCode: 98. Validate Binary Search Tree

Medium

Link: https://leetcode.com/problems/validate-binary-search-tree/

Topics: Tree, Depth First Search

Acceptance: 25.9

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true
Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.

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
       private boolean dfs(TreeNode root,long min,long max){
        if(root == null)return true;
        // Out of range
        if(root.val<=min || root.val>=max)return false;
        
        // Recur; for left max value should be this value, for going right, min value should be this value
        return dfs(root.left,min,root.val) && dfs(root.right,root.val,max);
    }
    public boolean isValidBST(TreeNode root){
        if(root == null)return true;
        // Long max and min has been used as Int min and max was interfering with equality statement
        // when node value was min or max
        return dfs(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }
}