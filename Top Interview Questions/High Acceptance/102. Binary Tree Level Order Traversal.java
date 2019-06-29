/*

LeetCode: 102. Binary Tree Level Order Traversal

Medium

Link: https://leetcode.com/problems/binary-tree-level-order-traversal/

Topics: Tree, Breadth First Search

Acceptance: 48.9

Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]


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
    public List<List<Integer>> levelOrder(TreeNode root) {

        List<List<Integer>> ans = new ArrayList<>();
        if(root==null)return ans;
        
        // Queue to store current level
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        
        // Size stores the size of level
        int size;
        TreeNode temp;
        while(!q.isEmpty()){
            List<Integer> level = new LinkedList();
            // Iterate only for current level
            size = q.size();
            for(int i=0;i<size;i++){
                temp = q.poll();
                level.add(temp.val);
                if(temp.left!=null)q.add(temp.left);
                if(temp.right!=null)q.add(temp.right);
            }
            ans.add(level);
        }
        return ans;
    }
}