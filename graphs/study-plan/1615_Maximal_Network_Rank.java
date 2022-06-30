/*

LeetCode: 1615. Maximal Network Rank

Medium

Link: https://leetcode.com/problems/maximal-network-rank/

Topics: Graph

Acceptance: 57.3

There is an infrastructure of n cities with some number of roads connecting these cities. Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.

The network rank of two different cities is defined as the total number of directly connected roads to either city. If a road is directly connected to both cities, it is only counted once.

The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.

Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.

 

Example 1:



Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
Output: 4
Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The road between 0 and 1 is only counted once.
Example 2:



Input: n = 5, roads = [[0,1],[0,3],[1,2],[1,3],[2,3],[2,4]]
Output: 5
Explanation: There are 5 roads that are connected to cities 1 or 2.
Example 3:

Input: n = 8, roads = [[0,1],[1,2],[2,3],[2,4],[5,6],[5,7]]
Output: 5
Explanation: The network rank of 2 and 5 is 5. Notice that all the cities do not have to be connected.
 

Constraints:

2 <= n <= 100
0 <= roads.length <= n * (n - 1) / 2
roads[i].length == 2
0 <= ai, bi <= n-1
ai != bi
Each pair of cities has at most one road connecting them.

*/

class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        if(roads == null || roads.length == 0){
            return 0;
        }
        //return bruteForceSolution(n, roads);
        return optimisedSolution(n, roads);
    }
    private int optimisedSolution(int n, int[][] roads){
        // use adjacency matrix for O(1) access to check
        // if 2 cities are connected or not
        int[][] graph = new int[n][n];
        Map<Integer, Integer> degree = new HashMap();
        for(int i = 0 ; i < roads.length; i++){
            int src = roads[i][0];
            int dest = roads[i][1];
            graph[src][dest] = 1;
            graph[dest][src] = 1;
            degree.put(src, degree.getOrDefault(src, 0) + 1);
            degree.put(dest, degree.getOrDefault(dest, 0) + 1);
        }
        int ans = 0;
        // iterate through all pairs
        for(int i = 0 ; i < n; i++){
            if(!degree.containsKey(i))continue;
            int rank = degree.get(i);
            for(int j = i + 1 ; j < n; j++){
                if(!degree.containsKey(j))continue;
                // total rank is sum of degree of both
                // if connected, subtract 1 for common road
                int total = rank + degree.get(j);
                if(graph[i][j]==1){
                    total--;
                }
                ans = Math.max(total, ans);
            }
        }
        return ans;
        
    }
    private int bruteForceSolution(int n, int[][] roads) {
        Map<Integer, List<Integer>> graph = new HashMap();
        for(int i = 0 ; i < roads.length; i++){
            int src = roads[i][0];
            int dest = roads[i][1];
            
            if(!graph.containsKey(src)){
                graph.put(src, new ArrayList());
            }
            graph.get(src).add(dest);
            
            if(!graph.containsKey(dest)){
                graph.put(dest, new ArrayList());
            }
            graph.get(dest).add(src);
        }
        int ans = 0;
        for(int i = 0 ; i < n; i++){
            List<Integer> firstList = graph.get(i);
            if(firstList == null)continue;
            int firstRank = firstList.size();
            for(int j = i + 1 ; j < n; j++){
                if(!graph.containsKey(j))continue;
                int rank = firstRank + graph.get(j).size();
                if(firstList.contains(j)){
                    rank--;
                }
                ans = Math.max(rank, ans);
            }
        }
        return ans;
    }
}
