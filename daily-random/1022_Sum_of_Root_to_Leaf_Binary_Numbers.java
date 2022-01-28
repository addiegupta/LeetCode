/*

LeetCode: 1022. Sum of Root To Leaf Binary Numbers

Easy

Link: https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/

Topics: Tree, Depth First Search, Binary Tree

Acceptance: 73

You are given the root of a binary tree where each node has a value 0 or 1. Each root-to-leaf path represents a binary number starting with the most significant bit.

For example, if the path is 0 -> 1 -> 1 -> 0 -> 1, then this could represent 01101 in binary, which is 13.
For all leaves in the tree, consider the numbers represented by the path from the root to that leaf. Return the sum of these numbers.

The test cases are generated so that the answer fits in a 32-bits integer.

 

Example 1:


Input: root = [1,0,1,0,1,0,1]
Output: 22
Explanation: (100) + (101) + (110) + (111) = 4 + 5 + 6 + 7 = 22
Example 2:

Input: root = [0]
Output: 0
 

Constraints:

The number of nodes in the tree is in the range [1, 1000].
Node.val is 0 or 1.

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
    public int sumRootToLeaf(TreeNode root) {
        //return helper(root, 0);
        return morrisTraversalSolution(root);
    }
    private int helper(TreeNode node, int sum){
        
        if(node == null){
            return 0;
        }
        
	// multiply by 2
        int newSum = sum << 1;
	// set bit of current val
        newSum |= node.val;
	// reached leaf, return current sum
        if (node.left == null && node.right == null){
            return newSum;
        }
        int left = helper(node.left, newSum);
        int right = helper(node.right, newSum);
        return left + right; 
    }
    // Morris traversal establishes temporary links between left subtrees right most node and current node 
    // so that current path need not be stored in recursion stack, hence saves space
    // Time O(n): still O(n) despite extra steps of finding and establishing links
    // Space O(1): recursion stack not required
    private int morrisTraversalSolution(TreeNode root){
        int rootToLeaf = 0;
        int currNum = 0;
        int steps;
        TreeNode current = root;
        while(current != null){
            
	    // no left subtree, hence no need to establish links between this node and any predecessor
	    // straightaway go to right subtree
            if(current.left == null){
		// left shift and use current bit value
                currNum = (currNum << 1) | current.val;
		// reached leaf, add num to sum
                if(current.right == null){
                    rootToLeaf += currNum;
                }
                current = current.right;
            } else{
		// find predecessor in left subtree
                TreeNode predecessor = current.left;
                steps = 1;
		// if predecessor.right is equal to current, that means temporary link has been found, break the link
		// and go to right subtree
                while(predecessor.right != current && predecessor.right != null){
                    predecessor = predecessor.right;
		    // count number of steps to backtrack later
                    steps++;
                }
		// reached end of subtree, establish temp link and use current value in num
                if(predecessor.right == null){
                    currNum = (currNum << 1) | current.val;
                    predecessor.right = current;
                    current = current.left;
                } else{ // entire left subtree traversed
	            // reached leaf node add to total sum
                    if(predecessor.left == null){
                        rootToLeaf += currNum;
                    }
		    // backtrack to where current was established
                    for(int i = 0; i < steps; i++){
                        currNum >>= 1;
                    }
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        return rootToLeaf;
    }
}
