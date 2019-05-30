/*

LeetCode: 94. Binary Tree Inorder Traversal   

Link: https://leetcode.com/problems/binary-tree-inorder-traversal/

Topics: Hash Table,Stack,Tree

Acceptance: 56.6

Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]
Follow up: Recursive solution is trivial, could you do it iteratively?

*/

// Java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) { 
        List<Integer> ans = new ArrayList<>();
        if(root==null)return ans;
        Stack<TreeNode> stk = new Stack<>();
        TreeNode temp;
        stk.push(root);
        while(!stk.isEmpty()){
            root = stk.pop();
            if(root.left==null){
                ans.add(root.val);
                if(root.right!=null) stk.push(root.right);
            }
            else{
                temp = root.left;
                root.left = null;
                stk.push(root);
                stk.push(temp);
            }
        }    
        return ans;
    }
}

/*

// C++
class Solution {
public:

    vector<int> inorderTraversal(TreeNode* root) { 
        vector<int> ans;
        if(root==NULL)return ans;
        stack<TreeNode*> stk;
        TreeNode* temp;
        stk.push(root);
        while(!stk.empty()){
            root = stk.top();
            stk.pop();
            if(root->left==NULL){
                ans.push_back(root->val);
                if(root->right!=NULL) stk.push(root->right);
            }
            else{
                temp = root->left;
                root->left = NULL;
                stk.push(root);
                stk.push(temp);
            }
        }    
        return ans;
    }
};
*/
