/*

LeetCode: 538. Convert BST to Greater Tree

Easy

Link: https://leetcode.com/problems/convert-bst-to-greater-tree/

Topics: Tree

Acceptance: 52.0
Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to the original key plus sum of all keys greater than the original key in BST.

Example:

Input: The root of a Binary Search Tree like this:
              5
            /   \
           2     13

Output: The root of a Greater Tree like this:
             18
            /   \
          20     13
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

    public TreeNode convertBST(TreeNode root) {
        recurHelper(root,0);
        return root;
    }
    private int recurHelper(TreeNode node,int greaterVal){
        if(node == null)return greaterVal;
        
        // Add all greater values sum from right subtree to current node value
        greaterVal = recurHelper(node.right,greaterVal) + node.val;
        node.val = greaterVal;
        
        // Apply greaterValue addition to left subtree and send total greaterValue sum to parent 
        greaterVal = recurHelper(node.left,greaterVal);
        return greaterVal;
    }
}