/*

LeetCode: 1091. Shortest Path in Binary Matrix

Medium

Link: https://leetcode.com/problems/shortest-path-in-binary-matrix/

Topics: Graph, Breadth first search, Matrix, Array

Acceptance: 42.7

Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.

A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

All the visited cells of the path are 0.
All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
The length of a clear path is the number of visited cells of this path.

 

Example 1:


Input: grid = [[0,1],[1,0]]
Output: 2
Example 2:


Input: grid = [[0,0,0],[1,1,0],[1,1,0]]
Output: 4
Example 3:

Input: grid = [[1,0,0],[1,1,0],[1,1,0]]
Output: -1
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1

*/
class Solution {
    int n;
    public int shortestPathBinaryMatrix(int[][] grid) {
        if(grid == null || grid.length == 0 || grid[0][0] != 0 || grid[grid.length - 1][grid.length - 1] != 0){
            return -1;
        }
        n = grid.length;
        return bfs(grid);
    }
    private int bfs(int[][] grid){
        int[][] path = new int[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0 ; j < n; j++){
                path[i][j] = Integer.MAX_VALUE;
            }
        }
        path[0][0] = 1;
        
        int[] rows = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] cols = {-1, 0, 1, -1, 1, -1, 0, 1};
        Queue<Pair<Integer, Integer>> q = new LinkedList();
        q.add(new Pair(0,0));
        while(!q.isEmpty()){
            Pair<Integer, Integer> node = q.poll();
            int i = node.getKey();
            int j = node.getValue();
	    //found last node, since using bfs this would be the first time this has been reached
            if(i == n - 1 && j == n - 1){
                return path[i][j];
            }
            if(grid[i][j] != 0)continue;
            grid[i][j] = 2;
            for(int x = 0; x < 8; x++){
                int newI = i + rows[x];
                int newJ = j + cols[x];
                if(newI < 0 || newJ < 0 || newI >= n || newJ >= n || grid[newI][newJ] != 0 || path[newI][newJ] <= (path[i][j] + 1)){
                    continue;
                }
                q.add(new Pair(newI, newJ));
                path[newI][newJ] = path[i][j] + 1;
            }
        }
        return -1;
    }
}
