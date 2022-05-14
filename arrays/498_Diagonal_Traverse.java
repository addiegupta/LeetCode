/*

LeetCode: 498. Diagonal Traverse

Medium

Asked in: Creditas

Link: https://leetcode.com/problems/diagonal-traverse/

Topics: Arrays, Matrix, Simulation

Acceptance: 56

Given an m x n matrix mat, return an array of all the elements of the array in a diagonal order.

 

Example 1:


Input: mat = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,2,4,7,5,3,6,8,9]
Example 2:

Input: mat = [[1,2],[3,4]]
Output: [1,2,3,4]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n <= 104
1 <= m * n <= 104
-105 <= mat[i][j] <= 105 
 
*/

class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        //return initialUncleanSolution(mat);
        return lcCleanSolution(mat);
    }
    private int[] lcCleanSolution(int[][] mat){
        int m = mat.length;
        int n = mat[0].length;
        if(m == 1){
            return mat[0];
        }
        int d = 0;
        //going up, decrement row & increment column
        //going down, increment row & decrement column
        int[][] dirs = {{-1, 1}, {1, -1}};
        int[] ans = new int[m * n];
        int row = 0;
        int col = 0;
        for(int i = 0; i < m * n; i++){
            ans[i] = mat[row][col];
            // set row and col for next iteration
            row += dirs[d][0];
            col += dirs[d][1];
            // edge cases; adjust when going out of bounds
            // change direction as well
            if(row > m - 1){
                row = m - 1;
                col += 2;
                d = 1 - d;
            }
            if(col > n - 1){
                row += 2;
                col = n - 1;
                d = 1 - d;
            }
            if(row < 0){
                row = 0;
                d = 1 - d;
            }
            if(col < 0){
                col = 0;
                d = 1 - d;
            }
        }
        return ans;
    }
    private int[] initialUncleanSolution(int[][] mat){
        int m = mat.length;
        int n = mat[0].length;
        if(m == 1){
            return mat[0];
        }
        int startI = 0;
        int startJ = 0;
        int order = 0; // keeping square matrices
        boolean d = true;
        int[] ans = new int[m * n];
        int end = 0;
        
        while(startI + order < m && startJ + order < n){
            int dI = d ? startI + order : startI;
            int dJ = d ? startJ : startJ + order;
            int count = order + 1;
            while(count-- > 0){
                ans[end++] = mat[dI][dJ];
                dI = d ? dI - 1 : dI + 1;
                dJ = d ? dJ + 1 : dJ - 1;
            }
            d = !d;
            if(startI + order < m - 1 && startJ + order < n - 1){
                order = order + 1;
            } else if((startI + order < m - 1 || startJ + order < n - 1)){
                if(startJ + order < n - 1){
                    startJ = startJ + 1;
                } else{
                    startI = startI + 1;
                }
            } else if(startI < m - 1 && startJ < n - 1){
                order = order - 1;
                startI = startI + 1;
                startJ = startJ + 1;
            }else{
                break;
            }
        }
        return ans;
        
    }
