/*

LeetCode: 101. Symmetric Tree 

Easy

Link: https://leetcode.com/problems/symmetric-tree/

Acceptance: 43.5

Topics: Tree, Depth First Search, Breadth First Search

Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

    1
   / \
  2   2
 / \ / \
3  4 4  3
 

But the following [1,2,2,null,3,null,3] is not:

    1
   / \
  2   2
   \   \
   3    3
 

Note:
Bonus points if you could solve it both recursively and iteratively.
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
    private boolean recursiveSol(TreeNode left,TreeNode right){
        if(left==null && right==null)return true;
        if(left==null||right==null)return false;
        // Compare left and right nodes and if their children subtrees are mirror images of each other or not
		return left.val==right.val && recursiveSol(left.left,right.right) && recursiveSol(left.right,right.left);
    }
    private boolean iterativeSol(TreeNode root){
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root.left);
        q.add(root.right);
        TreeNode l,r;
        while(!q.isEmpty()){
            l=q.poll();
            r=q.poll();
            if(l==null && r==null)continue;
            if(l==null || r==null)return false;
            if(l.val!=r.val) return false;
            
            q.add(l.left);
            q.add(r.right);
            q.add(l.right);
            q.add(r.left);
        }
        return true;
    }
    public boolean isSymmetric(TreeNode root) {
        if(root==null)return true;
         return recursiveSol(root.left,root.right);
       // return iterativeSol(root);
    }
}