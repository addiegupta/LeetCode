/*

LeetCode: 297. Serialize and Deserialize Binary Tree

Hard

Link: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

Topics: Tree, Design

Acceptance: 41.2

Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"
Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.

Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

References: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/discuss/74260/Recursive-DFS-Iterative-DFS-and-BFS


 */
 
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */public class Codec {
	
	
	public String serialize(TreeNode root) {
        
		// BFS Method runtime 34ms
		// return iterativeBFSSerialize(root);
		
		// DFS Method runtime 1ms
		// This method returns wrong tree values for high int values in tree of order of 10^5
		StringBuilder sb = new StringBuilder();
		recursiveDFSSerialize(root,sb);
        return sb.toString();
		
    }
	// Serialises in preorder 
	private void recursiveDFSSerialize(TreeNode root, StringBuilder sb){
		if (root == null){
			// # is for null
            sb.append("#");
            return;
        }
		// Append current value to stringbuilder as a character value( limit is 2 bytes hence will function till 10^5)
        sb.append((char)(root.val + '0'));
		
		// Go left and right recursively for preorder traversal and hence serialization
        recursiveDFSSerialize(root.left,sb);
        recursiveDFSSerialize(root.right,sb);
	
	}
	private String iterativeBFSSerialize(TreeNode root){
		if(root==null)return "N ";
        StringBuilder sb = new StringBuilder("");
        
		// Store levels
        Queue<TreeNode> q = new LinkedList();
        
        q.add(root);
        while(!q.isEmpty()){
			// Serialize nodes in string
            root = q.poll();
            if(root==null)sb.append("N ");
            else { 
                sb.append(root.val+" ");
                q.add(root.left);
                q.add(root.right);
            }
        }
        return sb.toString();
	}

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
         
		// BFS method : Runtime 34ms
		// return iterativeBFSDeserialize(data);

		// DFS Method: Runtime 1 ms
		return recursiveDFSDeserialize(data.toCharArray());
        
	}
	private TreeNode iterativeBFSDeserialize(String data){
		// Split the string to obtain array of node values
		String[] vals = data.split(" ");
        if(vals[0].equals("N"))return null;
        int i=0;
        int n = vals.length;
		// Create root node and add to queue
        TreeNode root = new TreeNode(Integer.parseInt(vals[i++]));
        Queue<TreeNode> q = new LinkedList();
        q.add(root);
        TreeNode curr;
		
		// Queue will contain nodes level wise
        while(!q.isEmpty()){
            curr = q.poll();
			// Not a null node, add to tree
            if(!vals[i].equals("N")){
                curr.left = new TreeNode(Integer.parseInt(vals[i]));
                q.add(curr.left);
            }
            i++;
            if(i>=n)break;
            if(!vals[i].equals("N")){
                curr.right = new TreeNode(Integer.parseInt(vals[i]));
                q.add(curr.right);
            }
            i++;
          }
        return root;
	}
	int index =0;
	private TreeNode recursiveDFSDeserialize(char[] data){
		if (data[index] == '#'){
            index++;
            return null;
        }
        TreeNode root = new TreeNode(data[index++] - '0');
        root.left = recursiveDFSDeserialize(data);
        root.right = recursiveDFSDeserialize(data);
        return root;
	}
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));