/*

LeetCode: 1129. Shortest Path with Alternating Colors

Medium

Link: https://leetcode.com/problems/shortest-path-with-alternating-colors/

Topics: Graph, Breadth first search

Acceptance: 41.8

You are given an integer n, the number of nodes in a directed graph where the nodes are labeled from 0 to n - 1. Each edge is red or blue in this graph, and there could be self-edges and parallel edges.

You are given two arrays redEdges and blueEdges where:

redEdges[i] = [ai, bi] indicates that there is a directed red edge from node ai to node bi in the graph, and
blueEdges[j] = [uj, vj] indicates that there is a directed blue edge from node uj to node vj in the graph.
Return an array answer of length n, where each answer[x] is the length of the shortest path from node 0 to node x such that the edge colors alternate along the path, or -1 if such a path does not exist.

 

Example 1:

Input: n = 3, redEdges = [[0,1],[1,2]], blueEdges = []
Output: [0,1,-1]
Example 2:

Input: n = 3, redEdges = [[0,1]], blueEdges = [[2,1]]
Output: [0,1,-1]
 

Constraints:

1 <= n <= 100
0 <= redEdges.length, blueEdges.length <= 400
redEdges[i].length == blueEdges[j].length == 2
0 <= ai, bi, uj, vj < n
 
*/
class Solution {
    // redGraph will have src nodes which will have a red edge to its neighbors
    Map<Integer, List<Integer>> redGraph;
    Map<Integer, List<Integer>> blueGraph;
    // visited using red edge, blue edge
    Set<Integer> redVisited;
    Set<Integer> blueVisited;
    int[] ans;
    
    public int[] shortestAlternatingPaths(int n, int[][] redEdges, int[][] blueEdges) {
        ans = new int[n];
        Arrays.fill(ans, -1);
        ans[0] = 0;
        redGraph = new HashMap();
        blueGraph = new HashMap();
        redVisited = new HashSet();
        blueVisited = new HashSet();
        for(int i = 0 ; i < redEdges.length; i++){
            int src = redEdges[i][0];
            int dest = redEdges[i][1];
            List nbrs = redGraph.getOrDefault(src, new ArrayList());
            nbrs.add(dest);
            redGraph.put(src, nbrs);
        }
        for(int i = 0 ; i < blueEdges.length; i++){
            int src = blueEdges[i][0];
            int dest = blueEdges[i][1];
            List nbrs = blueGraph.getOrDefault(src, new ArrayList());
            nbrs.add(dest);
            blueGraph.put(src, nbrs);
        }
        
        // cannot start at 0
        if(!redGraph.containsKey(0) && !blueGraph.containsKey(0)){
            return ans;
        }
        //initialTLEDfsSolution();
        newBfsSolution();
        return ans;
    }
    
    // shortest path should usually trigger bfs as the approach
    private void newBfsSolution(){
        // 0: node value
        // 1: nextRed (is next edge red?)
        // 2: pathLength
        Queue<int[]> q = new LinkedList();
        if(redGraph.containsKey(0)){
            q.offer(new int[]{0, 1, 0});
        }
        if(blueGraph.containsKey(0)){
            q.offer(new int[]{0, 0, 0});
        }
        while(!q.isEmpty()){
            int[] node = q.poll();
            int i = node[0];
            boolean nextRed = node[1] == 1;
            if(ans[i] == -1){
                ans[i] = node[2];
            } else {
                ans[i] = Math.min(ans[i], node[2]);
            }
            // next edge is red, prev edge should be blue
            // for tracking visited, keeping 2 separate sets as it may happen that a node is again visited but this time through a different color, leading to different solution sets
            if(nextRed){
                if(blueVisited.contains(i) && i != 0)continue;
                blueVisited.add(i);
                if(!redGraph.containsKey(i)) continue;
                List<Integer> nbrs = redGraph.get(i);
                for(int nbr : nbrs){
                    if(redVisited.contains(nbr))continue;
                    q.offer(new int[]{nbr, 0, node[2] + 1});
                }
                
            } else{
                if(redVisited.contains(i) && i != 0)continue;
                redVisited.add(i);
                if(!blueGraph.containsKey(i)) continue;
                List<Integer> nbrs = blueGraph.get(i);
                for(int nbr : nbrs){
                    if(blueVisited.contains(nbr))continue;
                    q.offer(new int[]{nbr, 1, node[2] + 1});
                }
            }
        }
    }
    private void initialTLEDfsSolution(){
        if(redGraph.containsKey(0)){
            blueVisited.add(0);
            dfs(0, true, 0);
            blueVisited.remove(0);
        }
        if(blueGraph.containsKey(0)){
            redVisited.add(0);
            dfs(0, false, 0);
            redVisited.remove(0);
        }
    }
    
    private void dfs(int i, boolean nextRed, int pathLength){
        if(ans[i] == -1){
            ans[i] = pathLength;
        } else {
            ans[i] = Math.min(ans[i], pathLength);
        }
        List<Integer> neighbors;
        if(nextRed){
            if(!redGraph.containsKey(i))return;
            neighbors = redGraph.get(i);
            for(int nbr : neighbors){
                if(redVisited.contains(nbr))continue;
                blueVisited.add(i);
                dfs(nbr, false, pathLength + 1);
                blueVisited.remove(i);
            }
        } else{
            if(!blueGraph.containsKey(i))return;
            neighbors = blueGraph.get(i);
            for(int nbr : neighbors){
                if(blueVisited.contains(nbr))continue;
                redVisited.add(i);
                dfs(nbr, true, pathLength + 1);
                redVisited.remove(i);
            }
        }
    }
}
