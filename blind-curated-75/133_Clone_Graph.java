/*

LeetCode: 133. Clone Graph

Medium

Link: https://leetcode.com/problems/clone-graph/

Topics: Hash Table, DFS, BFS, Graph

Acceptance: 44.6

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a value (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}
 

Test case format:

For simplicity, each node's value is the same as the node's index (1-indexed). For example, the first node with val == 1, the second node with val == 2, and so on. The graph is represented in the test case using an adjacency list.

An adjacency list is a collection of unordered lists used to represent a finite graph. Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1. You must return the copy of the given node as a reference to the cloned graph.

 

Example 1:


Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
Example 2:


Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list. The graph consists of only one node with val = 1 and it does not have any neighbors.
Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.
 

Constraints:

The number of nodes in the graph is in the range [0, 100].
1 <= Node.val <= 100
Node.val is unique for each node.
There are no repeated edges and no self-loops in the graph.
The Graph is connected and all nodes can be visited starting from the given node.
 */

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    HashMap<Integer, Node> cloneNodes = new HashMap<>();
    private Node bfs(Node node){

        // queue for bfs
        Queue<Node> q = new LinkedList();
        q.offer(node);
        
        while(!q.isEmpty()){
            Node point = q.poll();
            
            // already visited
            if(cloneNodes.containsKey(point.val))continue;
            
	    // Create the clone node for current node being visited
            ArrayList<Node> cloneNeighbors = new ArrayList<>();
            Node cloneNode = new Node(point.val, cloneNeighbors); 
            
	    // add neighbors to queue
            for(Node neighbor: point.neighbors){
                if(cloneNodes.containsKey(neighbor.val)){
                    // add to list of neightbors: neighbor node to new node and vice versa
                    Node cloneNeighborNode = cloneNodes.get(neighbor.val);
                    cloneNeighbors.add(cloneNeighborNode);
                    cloneNeighborNode.neighbors.add(cloneNode);
                }else{
                    // still not visited, add original node to queue
                    q.offer(neighbor);
                }
            }
            cloneNodes.put(cloneNode.val, cloneNode);
        }
        return cloneNodes.get(node.val);
    }
    private Node dfs(Node node){
        if(cloneNodes.containsKey(node.val)){
	    // already clones, return clone of original node from map
            return cloneNodes.get(node.val);
        }
	// create clone node & add to map
        Node cloneNode = new Node(node.val);
        cloneNodes.put(node.val, cloneNode);
	// for all neighbors of original, add clone neighbors to newly created clone node
        for(Node neighbor: node.neighbors){
            cloneNode.neighbors.add(dfs(neighbor));
        }
        return cloneNode;
    }
    public Node cloneGraph(Node node) {
        if(node == null)return null;
	
	// dfs code is much shorter and easier to understand
	// dfs is also well suited since all nodes need to be traversed
	// return bfs(node);
	return dfs(node);
    }
}

