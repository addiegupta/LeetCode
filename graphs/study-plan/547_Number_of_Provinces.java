/*

LeetCode: 547. Number of Provinces

Medium

Link: https://leetcode.com/problems/number-of-provinces

Topics: Graph, Depth first Search, Breadth first search, Union Find

Acceptance: 62.6

 There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces.

 

Example 1:


Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Example 2:


Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
Output: 3
 

Constraints:

1 <= n <= 200
n == isConnected.length
n == isConnected[i].length
isConnected[i][j] is 1 or 0.
isConnected[i][i] == 1
isConnected[i][j] == isConnected[j][i]

*/
class Solution {
    int[] parent;
    private boolean union(int i, int j){
        int parentI = findParent(i);
        int parentJ = findParent(j);
        if(parentI == parentJ){
            return false;
        }
        parent[parentJ] = parentI;
        return true;
    }
    private int findParent(int i){
        if(parent[i] == i){
            return i;
        }
        return findParent(parent[i]);
    }
    public int findCircleNum(int[][] isConnected) {
        if(isConnected == null || isConnected.length == 0){
            return 0;
        }
        return unionFindSolution(isConnected);
        //return dfsSolution(isConnected);
    }
    
    private int dfsSolution(int[][] graph){
        int ans = 0;
        int n = graph.length;
        int[] visited = new int[n];
        for(int i = 0 ; i < n; i++){
            if(visited[i] != 1){
                dfs(graph, i, visited);
                ans++;
            }
        }
        return ans;
    }
    private void dfs(int[][] graph, int i, int[] visited){
        int n = graph.length;
        visited[i] = 1;
        for(int j = 0; j < n; j++){
            if(i != j && graph[i][j] == 1 && visited[j] == 0){
                dfs(graph, j, visited);
            }
        }
    }
    
    private int unionFindSolution(int[][] isConnected) {
        int n = isConnected.length;
        int ans = n;
        
        // initialise parent array
        parent =  new int[n];
        for(int i = 0 ; i < n; i++){
            parent[i] = i;
        }
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                if(isConnected[i][j] == 1){
                    boolean didCombine = union(i, j);
                    // components got connected, 1 less comopnent in ans
                    if(didCombine){
                        ans--;
                    }
                }
            }
        }
        return ans;
    }
}
