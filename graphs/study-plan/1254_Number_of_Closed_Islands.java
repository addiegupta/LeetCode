/*

LeetCode: 1254. Number of Closed Islands

Medium

Link: https://leetcode.com/problems/number-of-closed-islands/

Topics: Graph, Depth first Search, Breadth first search, Matrix, Array, Union Find

Acceptance: 63.4

Similar to: Number of Enclaves

Given a 2D grid consists of 0s (land) and 1s (water).  An island is a maximal 4-directionally connected group of 0s and a closed island is an island totally (all left, top, right, bottom) surrounded by 1s.

Return the number of closed islands.

 

Example 1:



Input: grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,0,1],[1,1,1,1,1,1,1,0]]
Output: 2
Explanation: 
Islands in gray are closed because they are completely surrounded by water (group of 1s).
Example 2:



Input: grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
Output: 1
Example 3:

Input: grid = [[1,1,1,1,1,1,1],
               [1,0,0,0,0,0,1],
               [1,0,1,1,1,0,1],
               [1,0,1,0,1,0,1],
               [1,0,1,1,1,0,1],
               [1,0,0,0,0,0,1],
               [1,1,1,1,1,1,1]]
Output: 2
 

Constraints:

1 <= grid.length, grid[0].length <= 100
0 <= grid[i][j] <=1
 
*/

class Solution {
    int m;
    int n;
    public int closedIsland(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int ans = 0;
        m = grid.length;
        n = grid[0].length;
        for(int a = 0; a < m; a++){
            for(int b = 0; b < n; b++){
                if(grid[a][b] == 0){
                    boolean isClosed = dfs(grid, a, b);
                    if(isClosed){
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    private boolean dfs(int[][] grid, int i, int j){
        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 3){
            return false;
        } 
        if(grid[i][j] != 0){
            return true;
        }
        // mark as visited
        grid[i][j] = 2;
        
        int [][] dirs = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
        
        for(int x = 0; x < 4; x++){
            int newI = i + dirs[x][0];
            int newJ = j + dirs[x][1];
            if(!dfs(grid, newI, newJ)){
                // mark as invalid for being closed, if this is reached by future edges
                grid[i][j] = 3;
                return false;
            }
        }
        
        return true;
    }
}
