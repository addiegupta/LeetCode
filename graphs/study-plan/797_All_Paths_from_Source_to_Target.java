/*

LeetCode: 797. All Paths From Source to Target

Medium

Link: https://leetcode.com/problems/all-paths-from-source-to-target/

Topics: Graph, Depth first Search, Breadth first search, Backtracking

Acceptance: 80.9

 Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.

The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).

 

Example 1:


Input: graph = [[1,2],[3],[3],[]]
Output: [[0,1,3],[0,2,3]]
Explanation: There are two paths: 0 -> 1 -> 3 and 0 -> 2 -> 3.
Example 2:


Input: graph = [[4,3,1],[3,2,4],[3],[4],[]]
Output: [[0,4],[0,3,4],[0,1,3,4],[0,1,2,3,4],[0,1,4]]
 

Constraints:

n == graph.length
2 <= n <= 15
0 <= graph[i][j] < n
graph[i][j] != i (i.e., there will be no self-loops).
All the elements of graph[i] are unique.
The input graph is guaranteed to be a DAG.
 
*/
class Solution {
    List<List<Integer>> ans;
    int n;
    // no need to keep track of visited as acyclic graph
    // and same node can be visited multiple times to form different paths
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans = new ArrayList();
        if(graph == null || graph.length == 0){
           return ans; 
        }
        n = graph.length;
        List<Integer> path = new ArrayList();
        path.add(0);
        dfs(graph, 0, path);
        return ans;
    }
    private void dfs(int[][] graph, int node, List<Integer> path){
        // found end, add path to answer set
        if(node == n - 1){
            ans.add(new ArrayList(path));
            return;
        }
        // visit all nodes and backtrack
        for(int nbr: graph[node]){
            path.add(nbr);
            dfs(graph, nbr, path);
            path.remove(path.size() - 1);
        }
    }
}
