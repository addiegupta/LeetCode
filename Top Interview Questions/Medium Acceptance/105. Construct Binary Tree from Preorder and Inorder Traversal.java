/*

LeetCode: 105. Construct Binary Tree from Preorder and Inorder Traversal

Medium

Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

Topics: Array, Tree, Depth First Search

Acceptance: 41.3

Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

For example, given

preorder = [3,9,20,15,7]
inorder = [9,3,15,20,7]
Return the following binary tree:

    3
   / \
  9  20
    /  \
   15   7
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

    // Used as a global count for elements that have been processed in preorder array
    private int preI;

    // Stores array element->inde KV mapping for inorder array
    Map<Integer,Integer> inorderMap = new HashMap();
    private TreeNode recursiveSol(int[] preorder,int[] inorder,int iStart,int iEnd,int n){
        // End of tree creation
        if(iStart>iEnd || preI>=n)return null;
        // get new element from preorder array 
        TreeNode root = new TreeNode(preorder[preI]);

        // find index of same element in inorder map 
        // to the left of this in inorder will be left subtree and right on right
        int inI = inorderMap.get(preorder[preI]);
    
        // Increment preorder index for next recursion
        preI++;
        // First go left deep then right
        // range of inorder is specified for left and right
        root.left = recursiveSol(preorder,inorder,iStart,inI-1,n);
        root.right = recursiveSol(preorder,inorder,inI+1,iEnd,n);
        return root;
    }
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;

        // Create inorder hashmap
        for(int i=0;i<n;i++)inorderMap.put(inorder[i],i);
        // Index for preorder array
        preI=0;
        return recursiveSol(preorder,inorder,0,n-1,n);
        // return iterativeSolution(preorder,inorder);
    }

    private TreeNode iterativeSolution(int[] preorder, int[] inorder) {
        // deal with edge case(s)
        if (preorder.length == 0) {
            return null;
        }
        
        // build a map of the indices of the values as they appear in the inorder array
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        
        // initialize the stack of tree nodes
        Stack<TreeNode> stack = new Stack<>();
        int value = preorder[0];
        TreeNode root = new TreeNode(value);
        stack.push(root);
        
        // for all remaining values...
        for (int i = 1; i < preorder.length; i ++) {
            // create a node
            value = preorder[i];
            TreeNode node = new TreeNode(value);
            
            if (map.get(value) < map.get(stack.peek().val)) {
                // the new node is on the left of the last node,
                // so it must be its left child (that's the way preorder works)
                stack.peek().left = node;
            } else {
                // the new node is on the right of the last node,
                // so it must be the right child of either the last node
                // or one of the last node's ancestors.
                // pop the stack until we either run out of ancestors
                // or the node at the top of the stack is to the right of the new node
                TreeNode parent = null;
                while(!stack.isEmpty() && map.get(value) > map.get(stack.peek().val)) {
                    parent = stack.pop();
                }
                parent.right = node;
            }
            stack.push(node);
        }
        
        return root;
    }
}

