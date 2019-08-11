/*

LeetCode: 437. Path Sum III

Easy (should be Medium)

Link: https://leetcode.com/problems/diameter-of-binary-tree/

Topics: Tree

Acceptance: 43.4

You are given a binary tree in which each node contains an integer value.

Find the number of paths that sum to a given value.

The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

Example:

root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

Return 3. The paths that sum to 8 are:

1.  5 -> 3
2.  5 -> 2 -> 1
3. -3 -> 11
 *//**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
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
    public int pathSum(TreeNode root, int sum) {
    
        if(root==null)return 0;

        // O(n^2) solution
        // pathSum starts traversal for node and its children
        // pathSumFrom starts a new path from node
        // return pathSumFrom(root,sum) + pathSum(root.left,sum) + pathSum(root.right,sum);

        // O(n) time O(n) space solution
        return mapPrefixSumSolution(root,sum);
    }
    private int pathSumFrom(TreeNode root,int sum){
        if(root == null)return 0;
        
        // if current val is on its own equal to sum then that is another path
        // continue path for left and right child
        return (root.val==sum ? 1 : 0 ) + pathSumFrom(root.left,sum-root.val) + pathSumFrom(root.right,sum-root.val);
    }
    
    private int mapPrefixSumSolution(TreeNode root,int sum){
        
        // map stores prefix sums
        Map<Integer,Integer> map = new HashMap();
        map.put(0,1);
        return backtrack(root,0,sum,map);
    
    }

    // Instead of counting current path sum, we subtract current node val from
    // previously encountered prefix sums hence making it efficient as a map is used
    private int backtrack(TreeNode root,int currSum,int sum,Map<Integer,Integer> map){
        if(root == null)return 0;
        
        // currSum holds entire sum from root of the tree till this node
        currSum+=root.val;

        // If prefix exists in map equal to excess of currSum to sum, then a possible path is present
        // Value of map gives number of such paths
        int res = map.getOrDefault(currSum-sum,0);
        
        // Put current prefix in map and go deep in tree
        map.put(currSum,map.getOrDefault(currSum,0)+1);
        res += backtrack(root.left,currSum,sum,map);
        res += backtrack(root.right,currSum,sum,map);
        
        // Remove prefix till here as we backtrack back to parent
        map.put(currSum,map.get(currSum)-1);
        
        return res;
    }
}