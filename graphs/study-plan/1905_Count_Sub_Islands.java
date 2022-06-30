/*

LeetCode: 1905. Count Sub Islands

Medium

Link: https://leetcode.com/problems/count-sub-islands/

Topics: Graph, Depth first Search, Breadth first search, Matrix, Array, Union Find

Acceptance: 66

You are given two m x n binary matrices grid1 and grid2 containing only 0's (representing water) and 1's (representing land). An island is a group of 1's connected 4-directionally (horizontal or vertical). Any cells outside of the grid are considered water cells.

An island in grid2 is considered a sub-island if there is an island in grid1 that contains all the cells that make up this island in grid2.

Return the number of islands in grid2 that are considered sub-islands.

 

Example 1:


Input: grid1 = [[1,1,1,0,0],[0,1,1,1,1],[0,0,0,0,0],[1,0,0,0,0],[1,1,0,1,1]], grid2 = [[1,1,1,0,0],[0,0,1,1,1],[0,1,0,0,0],[1,0,1,1,0],[0,1,0,1,0]]
Output: 3
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are three sub-islands.
Example 2:


Input: grid1 = [[1,0,1,0,1],[1,1,1,1,1],[0,0,0,0,0],[1,1,1,1,1],[1,0,1,0,1]], grid2 = [[0,0,0,0,0],[1,1,1,1,1],[0,1,0,1,0],[0,1,0,1,0],[1,0,0,0,1]]
Output: 2 
Explanation: In the picture above, the grid on the left is grid1 and the grid on the right is grid2.
The 1s colored red in grid2 are those considered to be part of a sub-island. There are two sub-islands.
 

Constraints:

m == grid1.length == grid2.length
n == grid1[i].length == grid2[i].length
1 <= m, n <= 500
grid1[i][j] and grid2[i][j] are either 0 or 1.

*/
class Solution {
    int m, n;
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        if(grid1 == null || grid1.length == 0 || grid2 == null || grid2.length == 0 ){
            return 0;
        }
        int ans = 0;
        m = grid1.length;
        n = grid1[0].length;
        for(int i = 0 ; i < m; i++){
            for(int j = 0; j < n; j++){
                if(grid2[i][j] == 1){
                    boolean isSubIsland = dfs(grid1, grid2, i, j);
                    if(isSubIsland){
                        ans++;
                    }
                }
            }
        }
        return ans;
    }
    private boolean dfs(int[][] grid1, int[][] grid2, int i, int j){
        // out of bounds or water, required conditions met, return true
        if(i < 0 || j < 0 || i >= m || j >= n || grid2[i][j] == 0){
            return true;
        }
        // island bit of grid2 not present in grid1, cannot be considered subIsland
        if(grid1[i][j] != 1){
            return false;
        }
        grid2[i][j] = 0;
        int[] row = {0,1,0,-1};
        int[] col = {1,0,-1,0};
        int neighbors = 0;
        for(int x = 0 ; x < 4; x++){
            boolean isSubIsland = dfs(grid1, grid2, i + row[x], j + col[x]);
            if(isSubIsland){
                neighbors ++;
            }
        }
        return neighbors == 4;
    }
}
