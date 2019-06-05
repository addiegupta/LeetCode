/*

LeetCode: 108. Convert Sorted Array to Binary Search Tree   

Easy

Link: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

Acceptance: 50.8

Topics: Tree, Depth First Search

Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example:

Given the sorted array: [-10,-3,0,5,9],

One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

      0
     / \
   -3   9
   /   /
 -10  5

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
	// A divide and conquer recursive approach 
	// Select mid of array as current node, create left subtree from left part and right from right part of array
	private TreeNode createTree(int[] nums,int start,int end){
		
		if(start>end)return null;
		
		int mid = start + (end-start)/2;
		TreeNode root = new TreeNode(nums[mid]);
		root.left=createTree(nums,start,mid-1);
		root.right = createTree(nums,mid+1,end);
		
		return root;
	}
    public TreeNode sortedArrayToBST(int[] nums) {
		
		return createTree(nums,0,nums.length-1);
        
    }
}