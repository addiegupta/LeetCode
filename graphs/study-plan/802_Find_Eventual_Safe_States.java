/*

LeetCode: 802. Find Eventual Safe States

Medium

Link: https://leetcode.com/problems/find-eventual-safe-states/submissions/

Topics: Graph, Depth first Search, Breadth first search, Topological Sort

Acceptance: 52.7

There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].

A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node.

Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.

 

Example 1:

Illustration of graph
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
Example 2:

Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
Output: [4]
Explanation:
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
 

Constraints:

n == graph.length
1 <= n <= 104
0 <= graph[i].length <= n
0 <= graph[i][j] <= n - 1
graph[i] is sorted in a strictly increasing order.
The graph may contain self-loops.
The number of edges in the graph will be in the range [1, 4 * 104]. 
 
*/
class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        if(graph == null || graph.length == 0){
            return new ArrayList();
        }
        int n = graph.length;
        Set<Integer> ans = new HashSet();
        Set<Integer> visited = new HashSet();
        List<Integer> ansList = new ArrayList();
	// start dfs from every node, if a cycle is found, then all nodes in current path are not safe nodes
	// else add to ans set
        for(int i = 0 ; i < n; i++){
            // adding separately to list rather than converting set to list to maintain sort order
            if(dfs(graph, i, visited)){
                ansList.add(i);
            }
        }
        return ansList;
    }
    
    // returns boolean: node i is a safe node
    private boolean dfs(int[][] graph, int i, Set<Integer> visited, Set<Integer> ans){
        // already visited, either a cycle is present OR this node has already been computed
        if(visited.contains(i)){
            return ans.contains(i);
        }
        visited.add(i);
        for(int j = 0 ; j < graph[i].length; j++){
            if(!dfs(graph, graph[i][j], visited)){
                return false;
            }
        }
        // since reached here, no cycle is present, add to ans
        ans.add(i);
        return true;
    }
}
