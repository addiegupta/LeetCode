/*

LeetCode: 542. 01 Matrix

Medium

Link: https://leetcode.com/problems/01-matrix/

Topics: Array, Dynamic Programming, Breadth First Search, Matrix

Acceptance: 43.7

Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.



Example 1:


Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
Output: [[0,0,0],[0,1,0],[0,0,0]]
Example 2:


Input: mat = [[0,0,0],[0,1,0],[1,1,1]]
Output: [[0,0,0],[0,1,0],[1,2,1]]


Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
mat[i][j] is either 0 or 1.
There is at least one 0 in mat.

 */

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        if(mat == null || mat.length == 0){
            return null;
        }
        //return bfsSolution(mat);
        return dpSolution(mat);
    }
    // From top left, start placing min distance at every value
    // this would result in covering of best distances only from top and left of every node
    // repeat dp steps from bottom right to cover bottom and right neighbors of every node
    // Time: O(mn) Space: O(mn)
    private int[][] dpSolution(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] ans = new int[m][n];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                ans[i][j] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 0){
                    ans[i][j] = 0;
                    continue;
                }
                int top = i == 0 ? Integer.MAX_VALUE : ans[i - 1][j];
                int left = j == 0 ? Integer.MAX_VALUE : ans[i][j - 1];
                if(!(top == Integer.MAX_VALUE && left == Integer.MAX_VALUE)){
                    ans[i][j] = Math.min(top, left) + 1;
                }
            }
        }
        for(int i = m - 1; i >=0; i--){
            for(int j = n - 1; j >=0; j--){
                int down = i == m - 1 ? Integer.MAX_VALUE : ans[i + 1][j];
                int right = j == n - 1 ? Integer.MAX_VALUE : ans[i][j + 1];
                if(!(down == Integer.MAX_VALUE && right == Integer.MAX_VALUE)){
                    ans[i][j] = Math.min(ans[i][j], Math.min(down, right) + 1);
                }
            }
        }
        return ans;
    }

    // MultiSource BFS: add all start points i.e. all 0s to queue
    // from all these sources, use BFS to cover entire matrix, updating distance level by level
    // Time: O(mn), Space: O(mn)
    private int[][] bfsSolution(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        Queue<Pair<Integer, Integer>> q = new LinkedList();
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(mat[i][j] == 0){
                    q.offer(new Pair(i, j));
                } else{
		            // init with infinite distance from a 0
                    mat[i][j] = Integer.MAX_VALUE;
                }

            }
        }
        int[][] dirs = {{-1,0}, {0, -1}, {0, 1}, {1, 0}};
        while(!q.isEmpty()){
           Pair<Integer, Integer> loc = q.poll();
           for(int x = 0; x < dirs.length; x++){
	           int currI = loc.getKey();
	           int currJ = loc.getValue();
               int i = currI + dirs[x][0];
               int j = currJ + dirs[x][1];
	           // bounds; already better distance available, no need to visit
               if(i < 0 || i>=m || j < 0 || j >= n || mat[i][j] <= mat[currI][currJ] + 1){
                   continue;
               }
               q.offer(new Pair(i, j));
               mat[i][j] = mat[currI][currJ] + 1;
           }
        }
        return mat;
    }
}

