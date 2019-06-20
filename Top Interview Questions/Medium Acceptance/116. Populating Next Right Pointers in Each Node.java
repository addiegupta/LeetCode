/*

LeetCode: 116. Populating Next Right Pointers in Each Node

Medium

Link: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

Topics: Tree, Depth First Search

Acceptance: 38.1

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.

 

Example:



Input: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":null,"right":null,"val":4},"next":null,"right":{"$id":"4","left":null,"next":null,"right":null,"val":5},"val":2},"next":null,"right":{"$id":"5","left":{"$id":"6","left":null,"next":null,"right":null,"val":6},"next":null,"right":{"$id":"7","left":null,"next":null,"right":null,"val":7},"val":3},"val":1}

Output: {"$id":"1","left":{"$id":"2","left":{"$id":"3","left":null,"next":{"$id":"4","left":null,"next":{"$id":"5","left":null,"next":{"$id":"6","left":null,"next":null,"right":null,"val":7},"right":null,"val":6},"right":null,"val":5},"right":null,"val":4},"next":{"$id":"7","left":{"$ref":"5"},"next":null,"right":{"$ref":"6"},"val":3},"right":{"$ref":"4"},"val":2},"next":null,"right":{"$ref":"7"},"val":1}
    
Explanation: Given the above perfect binary tree (Figure A), your function should populate each next pointer to point to its next right node, just like in Figure B.
 

Note:

You may only use constant extra space.
Recursive approach is fine, implicit stack space does not count as extra space for this problem.

*/

/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val,Node _left,Node _right,Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/
class Solution {
    
    public Node connect(Node root) {
        if(root==null)return null;
        
        // Iterative Solution; Does not require recursive stack space
        iterativeSolution(root);
        
        // Recursive Solution; Requires recursive stack space
        // recursiveConnect(root.left,root.right);
        return root;
    }    

    // Requires extra space in the form of its DFS recursive stack
    private void recursiveConnect(Node left,Node right){
        if(left == null || right ==null)return;
        left.next=right;
        // Connect all nodes on the level within reach of this recurrence
        recursiveConnect(left.left,left.right);
        recursiveConnect(left.right,right.left);
        recursiveConnect(right.left,right.right);
    }
    private void iterativeSolution(Node root){
        // Iteratively connect nodes in a level order traversal

        // Stores the reference to next level
        Node next;
        while(root!=null && root.left!= null){
            // Store next level ref
            next = root.left;
            // Connect nodes on next level
            // root references nodes on higher level while 
            // connections are made on next level
            while(root!=null){
                root.left.next=root.right;
                if(root.next!=null)root.right.next=root.next.left;
                else root.right.next=null;
                root=root.next;
            }
            root=next;
        }
    }
}