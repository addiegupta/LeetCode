/*

LeetCode: 695. Max Area of Island

Medium

Link: https://leetcode.com/problems/max-area-of-island/

Topics: Graph, Depth first Search, Breadth first search, Matrix, Array

Acceptance: 69.8

You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.

The area of an island is the number of cells with a value 1 in the island.

Return the maximum area of an island in grid. If there is no island, return 0.

 

Example 1:


Input: grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
Output: 6
Explanation: The answer is not 11, because the island must be connected 4-directionally.
Example 2:

Input: grid = [[0,0,0,0,0,0,0,0]]
Output: 0
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 50
grid[i][j] is either 0 or 1. 
 
*/
class Solution {
    int m;
    int n;
    public int maxAreaOfIsland(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int ans = 0;
        m = grid.length;
        n = grid[0].length;
        for(int a = 0; a < m; a++){
            for(int b = 0; b < n; b++){
                if(grid[a][b] == 1){
                    int area = dfs(grid, a, b);
                    ans = Math.max(ans, area);
                }
            }
        }
        return ans;
    }
    private int dfs(int[][] grid, int i, int j){
        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 0){
            return 0;
        }
        grid[i][j] = 0;
        int sum = 0;
        int [][] dirs = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
        
        for(int x = 0; x < 4; x++){
            int newI = i + dirs[x][0];
            int newJ = j + dirs[x][1];
            sum += dfs(grid, newI, newJ);
        }
        return sum + 1;
    }
}
