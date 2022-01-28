/*

LeetCode: 701. Insert into a Binary Search Tree

Medium

Link: https://leetcode.com/problems/insert-into-a-binary-search-tree/

Topics: Tree, Binary Tree, Binary Search Tree

Acceptance: 74.7

 You are given the root node of a binary search tree (BST) and a value to insert into the tree. Return the root node of the BST after the insertion. It is guaranteed that the new value does not exist in the original BST.

Notice that there may exist multiple valid ways for the insertion, as long as the tree remains a BST after insertion. You can return any of them.

 

Example 1:


Input: root = [4,2,7,1,3], val = 5
Output: [4,2,7,1,3,5]
Explanation: Another accepted tree is:

Example 2:

Input: root = [40,20,60,10,30,50,70], val = 25
Output: [40,20,60,10,30,50,70,null,null,25]
Example 3:

Input: root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
Output: [4,2,7,1,3,5]
 

Constraints:

The number of nodes in the tree will be in the range [0, 104].
-108 <= Node.val <= 108
All the values Node.val are unique.
-108 <= val <= 108
It's guaranteed that val does not exist in the original BST.

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
    public TreeNode insertIntoBST(TreeNode root, int val) {
        //return initialShortSolution(root, val);
        return moreExplicitSolution(root, val);
    }
    
    // this solution is shorter and reassigns subtree at every point
    // even though only 1 insert is required
    // Time: O(h) h is height of tree (same O as other solution)
    // Space: O(1)
    private TreeNode initialShortSolution(TreeNode root, int val){
        if(root == null){
            return new TreeNode(val);
        }
        if(val > root.val){
            root.right = insertIntoBST(root.right, val);
        }
        else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }
    
    // Time: O(h) h is height of tree
    // Space: O(1)
    private TreeNode moreExplicitSolution(TreeNode root, int val){
        // base case empty tree
        if(root == null){
            return new TreeNode(val);
        }
        helper(root, val);
        return root;
    }
    
    private void helper(TreeNode root, int val){
        if(val > root.val){
            // found location, insert node
            if(root.right == null){
                root.right = new TreeNode(val);
                return;
            }
            helper(root.right, val);
        }
        else {
            // found location, insert node
            if(root.left == null){
                root.left = new TreeNode(val);
                return;
            }
            helper(root.left, val);
        }
    }
}
