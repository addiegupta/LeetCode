/*

LeetCode: 329. Longest Increasing Path in a Matrix

Hard

Link: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/

Topics: Depth First Search, Memoization, Topological Sort, Graph

Acceptance: 40.6

Given an integer matrix, find the length of the longest increasing path.

From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally or move outside of the boundary (i.e. wrap-around is not allowed).

Example 1:

Input: nums = 
[
  [9,9,4],
  [6,6,8],
  [2,1,1]
] 
Output: 4 
Explanation: The longest increasing path is [1, 2, 6, 9].
Example 2:

Input: nums = 
[
  [3,4,5],
  [3,2,6],
  [2,2,1]
] 
Output: 4 
Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
class Solution {

    // Used in DFS Traversal
    public int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
 

    public int longestIncreasingPath(int[][] matrix){
        if(matrix.length==0)return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        // Acts like a visited array; storing the max depth as per requirement ( increasing order)
        // for each node
        int[][] depth = new int[m][n];
        
        int ans = 1;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                // DFS from every node
                // If the node has been visited previously, then a value will be obtained directly without
                // having to repeat dfs because of return condition in dfs method
                ans = Math.max(ans,dfs(matrix,depth,i,j,Integer.MIN_VALUE));
            }
        }
        return ans;
    }


    // Goes deep for every nodes children, finding max depths possible
    private int dfs(int[][] matrix, int[][] depth,int i,int j,int val){
        // Out of bounds or smaller value than previous, cannot continue dfs, return
        if(i<0 || j<0 || i>=matrix.length || j>= matrix[i].length || matrix[i][j]<=val){
            return 0;
        } 
        
        // Value already computed, return it
        if(depth[i][j]!=0)return depth[i][j];
        
        //
        int bestDepth=1;
        int x,y;
        // Traverse all 4 directions
        for(int[] dir: dirs){
            x = i+dir[0];
            y = j +dir[1];
            bestDepth = Math.max(bestDepth,1 + dfs(matrix,depth,x,y,matrix[i][j]));
        }
        depth[i][j] = bestDepth;
        return depth[i][j];
    }
 }