/*

LeetCode: 785. Is Graph Bipartite?

Medium

Link: https://leetcode.com/problems/is-graph-bipartite/

Topics: Graph, Depth first Search, Breadth first search, Union Find

Acceptance: 51.9

There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:

There are no self-edges (graph[u] does not contain u).
There are no parallel edges (graph[u] does not contain duplicate values).
If v is in graph[u], then u is in graph[v] (the graph is undirected).
The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.

Return true if and only if it is bipartite.

 

Example 1:


Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
Output: false
Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
Example 2:


Input: graph = [[1,3],[0,2],[1,3],[0,2]]
Output: true
Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 

Constraints:

graph.length == n
1 <= n <= 100
0 <= graph[u].length < n
0 <= graph[u][i] <= n - 1
graph[u] does not contain u.
All the values of graph[u] are unique.
If graph[u] contains v, then graph[v] contains u.

*/

class Solution {
    // bipartite graph must have each adjacent node colored differently
    // there should only be 2 colors, hence each adjacent node should have alternating colors
    public boolean isBipartite(int[][] graph) {
        if(graph == null || graph.length == 0){
            return true;
        }
        int n = graph.length;
        // -1 : colored black
        // 0 : not colored yet
        // 1 : colored white
        int[] color = new int[n];
        for(int i = 0; i < n; i++){
            if(color[i] != 0){
                continue;
            }
            Queue<int[]> q = new LinkedList();
            // this node has not yet been colored, start bfs from here using color 1
            // it wont matter to start with 1 or -1 as this undirected graph
            // and this node and its immediate neighbors havent been reached yet (BFS)
            q.offer(new int[]{i, 1});
            while(!q.isEmpty()){
                int[] node = q.poll();
                int x = node[0];
                if(color[x] != 0){
                    continue;
                }
                color[x] = node[1];
                for(int j = 0 ; j < graph[x].length; j++){
                    int y = graph[x][j];
                    // adjacent nodes have same color (1 & 1 or -1 & -1)
                    if(color[x] * color[y] == 1){
                        return false;
                    }
                    // not yet visited, add to bfs queue
                    if(color[y] == 0){
                        q.offer(new int[]{y, -color[x]});
                    } 
                }
            }
        }
        return true;
    }
}
