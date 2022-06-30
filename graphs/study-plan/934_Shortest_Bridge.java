/*

LeetCode: 934. Shortest Bridge

Medium

Link: https://leetcode.com/problems/shortest-bridge/

Topics: Graph, Depth first Search, Breadth first search, Matrix, Array

Acceptance: 52.5

 You are given an n x n binary matrix grid where 1 represents land and 0 represents water.

An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two islands in grid.

You may change 0's to 1's to connect the two islands to form one island.

Return the smallest number of 0's you must flip to connect the two islands.

 

Example 1:

Input: grid = [[0,1],[1,0]]
Output: 1
Example 2:

Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
Output: 2
Example 3:

Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
Output: 1
 

Constraints:

n == grid.length == grid[i].length
2 <= n <= 100
grid[i][j] is either 0 or 1.
There are exactly two islands in grid.
 
*/
class Solution {
    int[] rows = {0, 1, 0, -1};
    int[] cols = {1, 0, -1, 0};
    int n;
    public int shortestBridge(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        n = grid.length;
        return bfsSolution(grid);
    }
    class Node {
        int i;
        int j;
        Node(int i, int j){
            this.i = i;
            this.j = j;
        }
    }
    private int bfsSolution(int[][] grid) {
        Queue<Node> q = new LinkedList();
        Queue<Node> bridgeQ = new LinkedList();
        int[][] visited = new int[n][n];
        // find first island, add its points to bridgeQ
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n ;j++){
                // found first island
                if(grid[i][j] == 1){
                    q.add(new Node(i, j));
                    while(!q.isEmpty()){
                        Node node = q.poll();
                        int a = node.i;
                        int b = node.j;
                        if(visited[a][b] == 1)continue;
                        bridgeQ.offer(new Node(a, b));
                        visited[a][b] = 1;
                        for(int x = 0; x < 4; x++){
                            int newI = a + rows[x];
                            int newJ = b + cols[x];
                            if(newI >= 0 && newJ >= 0 && newI < n && newJ < n && grid[newI][newJ] == 1){
                                q.offer(new Node(newI, newJ));
                            }
                        }
                    }
                    break;
                }
            }
            if(!bridgeQ.isEmpty())break;
        }
        int ans = 0;
        while(!bridgeQ.isEmpty()){
            // go level by level
            int len = bridgeQ.size();
            while(len-- != 0){
                Node node = bridgeQ.poll();
                int i = node.i;
                int j = node.j;
                if(grid[i][j] == 0 && visited[i][j] != 0){
                    continue;
                }
                visited[i][j] = 1;
                for(int x = 0; x < 4; x++){
                    int newI = i + rows[x];
                    int newJ = j + cols[x];
                    if(newI >= 0 && newJ >= 0 && newI < n && newJ < n){
                        if(grid[newI][newJ] == 1){
                            if(visited[newI][newJ] == 0){
                                return ans;
                            }
                        }
                        else{
                            bridgeQ.offer(new Node(newI, newJ)); 
                        }
                    }
                }
            }
            ans++;
        }
        return ans;
    }
}
