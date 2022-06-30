/*

LeetCode: 1020. Number of Enclaves

Medium

Link: https://leetcode.com/problems/number-of-enclaves/

Topics: Graph, Depth first Search, Breadth first search, Matrix, Array, Union Find

Acceptance: 62.9

 You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.

A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.

Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.

 

Example 1:


Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
Output: 3
Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
Example 2:


Input: grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
Output: 0
Explanation: All 1s are either on the boundary or can reach the boundary.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 500
grid[i][j] is either 0 or 1.

*/

class Solution {
    int m;
    int n;
    // Time: O(m * n) DFS
    // Space: O(m * n) DFS
    public int numEnclaves(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        int ans = 0;
        m = grid.length;
        n = grid[0].length;
        for(int a = 0; a < m; a++){
            for(int b = 0; b < n; b++){
                if(grid[a][b] == 1){
                    int count = dfs(grid, a, b);
                    if(count != -1) ans += count;
                }
            }
        }
        return ans;
    }
    private int dfs(int[][] grid, int i, int j){
        // walked off the boundary, this path in invalid for enclaves, return -1
        if(i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == 3){
            return -1;
        } 
        
        if(grid[i][j] != 1){
            return 0;
        }
        // mark as visited
        grid[i][j] = 2;
        int ans = 1;
        
        int [][] dirs = {{0,1}, {0, -1}, {1, 0}, {-1, 0}};
        
        for(int x = 0; x < 4; x++){
            int newI = i + dirs[x][0];
            int newJ = j + dirs[x][1];
            int enclaves = dfs(grid, newI, newJ);
            if(enclaves == -1){
                // mark as invalid for obtaining enclaves
                grid[i][j] = 3;
                return -1;
            } else{
                ans += enclaves;
            }
        }
        
        return ans;
    }
}
