/*

LeetCode: 103. Binary Tree Zigzag Level Order Traversal

Medium

Link: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/

Topics: Stack, Tree, Breadth First Search

Acceptance: 41.9

Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],
    3
   / \
  9  20
    /  \
   15   7
return its zigzag level order traversal as:
[
  [3],
  [20,9],
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
    private void iterativeBFSSolution(TreeNode root,List<List<Integer>> ans){
		if(root==null)return;
		
		// Store nodes in queue
		Queue<TreeNode> q = new LinkedList();
		q.add(root);
		// For zigzag
		boolean ltr = false;
		int cnt;
		TreeNode node;
		while(!q.isEmpty()){
			// New list for storing nodes of current level
			List<Integer> level = new ArrayList();
			cnt = q.size();
			// As elements of next level will be added to queue, loop only for elements of this level
			for(int i=0;i<cnt;i++){
				node = q.poll();
				// Add at start or back of list for zigzag
				if(ltr){
					level.add(0,node.val);
				}	
				else{
					level.add(node.val);
				}
				// Add next level children to queue if not null
				if(node.left!=null) q.add(node.left);
				if(node.right!=null)q.add(node.right);
			}
			ans.add(level);
			// Reverese direction for zigzag
			ltr = !ltr;
		}
	}
	
	private void recursiveDFSSolution(TreeNode root,List<List<Integer>> ans,int level){
		if(root==null)return;
		
		// Create new list if this level is encountered for the first time
		if(level>=ans.size()) ans.add(new LinkedList());
		
		// Get ref to currlevel list
		List<Integer> currLevel = ans.get(level);
		
		// Add element to front or back of list depending on number of level for zigzag traversal
		if(level%2==0) currLevel.add(root.val);
		else currLevel.add(0,root.val);
		
		// Recur for next level
		recursiveDFSSolution(root.left,ans,level+1);
		recursiveDFSSolution(root.right,ans,level+1);
		
	}
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> ans = new ArrayList();
		//iterativeBFSSolution(root,ans);
		recursiveDFSSolution(root,ans,0);
		return ans;
    }
}