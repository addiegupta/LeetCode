/*

LeetCode: 1466. Reorder Routes to Make All Paths Lead to the City Zero

Medium

Link: https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/

Topics: Graph, Depth first Search, Breadth first search

Acceptance: 61

 There are n cities numbered from 0 to n - 1 and n - 1 roads such that there is only one way to travel between two different cities (this network form a tree). Last year, The ministry of transport decided to orient the roads in one direction because they are too narrow.

Roads are represented by connections where connections[i] = [ai, bi] represents a road from city ai to city bi.

This year, there will be a big event in the capital (city 0), and many people want to travel to this city.

Your task consists of reorienting some roads such that each city can visit the city 0. Return the minimum number of edges changed.

It's guaranteed that each city can reach city 0 after reorder.

 

Example 1:


Input: n = 6, connections = [[0,1],[1,3],[2,3],[4,0],[4,5]]
Output: 3
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 2:


Input: n = 5, connections = [[1,0],[1,2],[3,2],[3,4]]
Output: 2
Explanation: Change the direction of edges show in red such that each node can reach the node 0 (capital).
Example 3:

Input: n = 3, connections = [[1,0],[2,0]]
Output: 0
 

Constraints:

2 <= n <= 5 * 104
connections.length == n - 1
connections[i].length == 2
0 <= ai, bi <= n - 1
ai != bi
 
*/

class Solution {
    // do a dfs from 0
    // treat edges as undirected to traverse entire graph
    // for any outgoing edge, it needs to be replaced with incoming hence increment counter
    // if using negative value to differentiate between the 2 types of edges doesnt strike,
    // could have also used 2 separate graphs to maintain incoming and outgoing edges
    public int minReorder(int n, int[][] connections) {
        if(n == 0 || connections.length == 0){
            return 0;
        }
        int len = connections.length;
        List<List<Integer>> graph = new ArrayList();
        for(int i = 0 ; i < n; i++){
            graph.add(new ArrayList());
        }
        for(int i = 0 ; i < len; i++){
            int src = connections[i][0];
            int dest = connections[i][1];
            // positive value: outgoing edge
            // negative value: incoming edge
            graph.get(src).add(dest);
            graph.get(dest).add(-src);
        }
        boolean[] visited = new boolean[n];
        int ans = dfs(0, graph, visited);
        return ans;
    }
    private int dfs(int i,List<List<Integer>> graph, boolean[] visited) {
        int count = 0;
        visited[i] = true;
        List<Integer> nbrs = graph.get(i);
        for(int j : nbrs) {
            if(visited[Math.abs(j)])continue;
            // outgoing edge present from current node, need to reverse it
            if(j > 0){
                count++;
            }
            count += dfs(Math.abs(j), graph, visited);
        }
        return count;
    }
}
