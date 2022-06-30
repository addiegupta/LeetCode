/*

LeetCode: 1971. Find if Path Exists in Graph

Easy

Link: https://leetcode.com/problems/find-if-path-exists-in-graph/

Topics: Graph, Depth first Search, Breadth first search, Union Find

Acceptance: 49.7

 There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.

You want to determine if there is a valid path that exists from vertex source to vertex destination.

Given edges and the integers n, source, and destination, return true if there is a valid path from source to destination, or false otherwise.

 

Example 1:


Input: n = 3, edges = [[0,1],[1,2],[2,0]], source = 0, destination = 2
Output: true
Explanation: There are two paths from vertex 0 to vertex 2:
- 0 → 1 → 2
- 0 → 2
Example 2:


Input: n = 6, edges = [[0,1],[0,2],[3,5],[5,4],[4,3]], source = 0, destination = 5
Output: false
Explanation: There is no path from vertex 0 to vertex 5.
 

Constraints:

1 <= n <= 2 * 105
0 <= edges.length <= 2 * 105
edges[i].length == 2
0 <= ui, vi <= n - 1
ui != vi
0 <= source, destination <= n - 1
There are no duplicate edges.
There are no self edges.
 
*/

class Solution {
    // parent[i] denotes parent node for ith node; used in union find solution
    int[] parent;
    public boolean validPath(int n, int[][] edges, int start, int end) {
        if(edges == null || edges.length == 0){
            return n == 1;
        }
        //return bfsSolution(n, edges, start, end);
        return unionFindSolution(n, edges, start, end);
    }
    // much better than bfs as dont have to construct entire graph
    // Time: O(E log V): for each edge, each union is log V (parent paths would roughly be like trees with height log V)
    // Space: O(V)
    private boolean unionFindSolution(int n, int[][] edges, int start, int end) {
        parent = new int[n];
        for(int i = 0 ; i < n; i++){
            parent[i] = i;
        }
        
        // join the edges by combining groups
        for(int[] edge: edges){
            unionGroups(edge[0], edge[1]);
        }
        // src and dest should belong in same group
        return findParent(start) == findParent(end);
    }
    private void unionGroups(int node1, int node2){
        int parent1 = findParent(node1);
        int parent2 = findParent(node2);
        parent[parent2] = parent1;
    }
    private int findParent(int node){
        if(parent[node] == node){
            return node;
        }
        parent[node] = findParent(parent[node]);
        return parent[node];
    }
    // Time: O(V + E) for constructing entire graph
    // Space: O(V + E) for entire graph
    private boolean bfsSolution(int n, int[][] edges, int start, int end) {
        int len = edges.length;
        
        // construct graph out adjacency matrix
        Map<Integer, List<Integer>> graph = new HashMap();
        for(int i = 0; i < len; i++){
            int src = edges[i][0];
            int dest = edges[i][1];
            if(!graph.containsKey(src)){
                graph.put(src, new ArrayList());
            }
            graph.get(src).add(dest);
            if(!graph.containsKey(dest)){
                graph.put(dest, new ArrayList());
            }
            graph.get(dest).add(src);
        }
        
        //bfs
        Queue<Integer> q = new LinkedList();
        Set<Integer> visited = new HashSet();
        q.add(start);
        while(!q.isEmpty()){
            int node = q.poll();
            if(visited.contains(node)){
                continue;
            }
            visited.add(node);
            if(!graph.containsKey(node)){
                continue;
            }
            for(int neighbor : graph.get(node)){
                // found end, path exists
                if(neighbor == end){
                    return true;
                }
                q.add(neighbor);
            }
        }
        // no path found
        return false;
    }
}
