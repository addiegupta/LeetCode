/*

LeetCode: 104. Maximum Depth of Binary Tree   

Link: https://leetcode.com/problems/maximum-depth-of-binary-tree/

Topics: Tree, Depth First Search

Acceptance: 60.5

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7
return its depth = 3.

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

// C++
class Solution {
public:
    int maxDepth(TreeNode* root) {
        if(root==NULL)return 0;
        
        int left = maxDepth(root->left)+1;
        int right = maxDepth(root->right)+1;
        return left>right? left:right;
    }
};

// Java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root==null)return 0;
        
        return Math.max(maxDepth(root.left),maxDepth(root.right))+1;
    }
}