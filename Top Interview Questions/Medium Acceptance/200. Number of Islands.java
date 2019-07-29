/*

LeetCode: 200. Number of Islands

Medium

Link: https://leetcode.com/problems/number-of-islands/

Topics: Depth first search, breadth first search,, union find

Acceptance: 42.2


Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:

Input:
11110
11010
11000
00000

Output: 1
Example 2:

Input:
11000
11000
00100
00011

Output: 3

*/

class Solution {
    
    // On visiting an island, mark it as visited by drowning it (setting the value to 0 )
    public int numIslands(char[][] grid) {
        if(grid.length==0 || grid[0].length == 0)return 0;
        int ans = 0;
        int m = grid.length;
        int n = grid[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                
                // Found new island
                if(grid[i][j]=='1'){
                    // Increment count
                    ans++;
                    // Drown entire island
                    visitIsland(grid,i,j);
                }
            }
        }
        return ans;
    }
    private void visitIsland(char[][] grid,int i,int j){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j]=='0')return;
        
        // Drown
        grid[i][j] ='0';
        
        // Drown all directions
        int[] row = {0,1,0,-1};
        int[] col = {-1,0,1,0};
        for(int x=0;x<4;x++)visitIsland(grid,i+row[x],j+col[x]);
    }
}