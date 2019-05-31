/*

LeetCode: 230. Kth Smallest Element in a BST

Medium

Link: https://leetcode.com/problems/kth-smallest-element-in-a-bst/

Topics: Binary Search, Tree

Acceptance: 51.5

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]

Hints:

Try to utilize the property of a BST.

Try in-order traversal. (Credits to @chan13)

What if you could modify the BST node's structure?

The optimal runtime complexity is O(height of BST).

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
    class CustomInt{
        public int x;
    }
    private boolean findKth(TreeNode root,int k,CustomInt i,CustomInt ans){
        if(root==null)return false;
        if(findKth(root.left,k,i,ans)) return true;
        i.x++;
        if(i.x==k){
            ans.x = root.val;
            return true;
        }
        return findKth(root.right,k,i,ans);
    }
    public int kthSmallest(TreeNode root, int k) {
        CustomInt ans = new CustomInt();
        CustomInt count = new CustomInt();
        count.x=0;
        findKth(root,k,count,ans);
        return ans.x;
    }
}
