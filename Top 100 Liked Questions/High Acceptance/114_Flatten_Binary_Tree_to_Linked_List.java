/*

LeetCode: 114. Flatten Binary Tree to Linked List

Medium

Link: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

Topics: Linked List, Stack, Tree, Depth First Search, Binary Tree

Acceptance: 57.8

Given the root of a binary tree, flatten the tree into a "linked list":

The "linked list" should use the same TreeNode class where the right child pointer points to the next node in the list and the left child pointer is always null.
The "linked list" should be in the same order as a pre-order traversal of the binary tree.


Example 1:


Input: root = [1,2,5,3,4,null,6]
Output: [1,null,2,null,3,null,4,null,5,null,6]
Example 2:

Input: root = []
Output: []
Example 3:

Input: root = [0]
Output: [0]


Constraints:

The number of nodes in the tree is in the range [0, 2000].
-100 <= Node.val <= 100


Follow up: Can you flatten the tree in-place (with O(1) extra space)?

 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private TreeNode flattenUtil(TreeNode root){
        if(root == null){
            return root;
        }
        TreeNode leftTail = flattenUtil(root.left);
        TreeNode rightTail = flattenUtil(root.right);
        if(root.left != null){
	    // connect right subtree to tail of left subtree to flatten the entire list
            leftTail.right = root.right;
            root.right = root.left;
	    // nullify left to flatten
            root.left = null;
        }
	// decide tail to return to top
        TreeNode tail;
        if(rightTail != null){
            tail = rightTail;
        } else if(leftTail != null){
            tail = leftTail;
        } else{
            tail = root;
        }
        return tail;
    }
    private void iterativeFlatten(TreeNode root){
        TreeNode trav = root;
        while(trav != null){
            if(trav.left != null){
                // find deepest child to connect trav's right to
                TreeNode pre = trav.left;
                while(pre.right != null){
                    pre = pre.right;
                }
                pre.right = trav.right;
                trav.right = trav.left;
                trav.left = null;
            }
            trav = trav.right;
        }
        
    }
    public void flatten(TreeNode root) {
        iterativeFlatten(root);
        //flattenUtil(root);
    }
}
