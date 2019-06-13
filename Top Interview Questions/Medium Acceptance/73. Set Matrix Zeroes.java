/*

LeetCode: 73. Set Matrix Zeroes

Medium

Link: https://leetcode.com/problems/set-matrix-zeroes/

Topics: Array

Acceptance: 39.9

Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

Example 1:

Input: 
[
  [1,1,1],
  [1,0,1],
  [1,1,1]
]
Output: 
[
  [1,0,1],
  [0,0,0],
  [1,0,1]
]
Example 2:

Input: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
Output: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
Follow up:

A straight forward solution using O(mn) space is probably a bad idea.
A simple improvement uses O(m + n) space, but still not the best solution.
Could you devise a constant space solution?
 */

class Solution {
    public void setZeroes(int[][] matrix) {
        // Store the state of column index 0 in boolean
        boolean colZero=false;
        int m = matrix.length,n=matrix[0].length;
        int i,j;
        for(i=0;i<m;i++){
            if(matrix[i][0]==0) colZero=true;
            
            // Use first row and first column as arrays for storing if current row/column is 0
            for(j=1;j<n;j++){
                 if(matrix[i][j]==0){
                    matrix[0][j]=0;
                    matrix[i][0]=0;
                }
            }
            
        }
        
        // Set rows and columns to 0
        for(i=1;i<m;i++){
            for(j=1;j<n;j++){
                if(matrix[0][j]==0 || matrix[i][0]==0)matrix[i][j]=0;
            }
        }
        // Set first row and column according to their initial state
        if(matrix[0][0]==0){
            for(i=0;i<n;i++){
                matrix[0][i]=0;
            }
        } 
        if(colZero){
            for(j=0;j<m;j++){
                matrix[j][0]=0;
            }
        }
        
    }
}