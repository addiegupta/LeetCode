/*

LeetCode: 240. Search a 2D Matrix II

Medium

Link: https://leetcode.com/problems/search-a-2d-matrix-ii/

Topics: Binary Search, Divide and Conquer

Acceptance: 41.2

Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
Example:

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.

 */
class Solution {
    
    public boolean searchMatrix(int[][] matrix, int target) {
        if( matrix == null || matrix.length==0 || matrix[0].length==0)return false;
        
        int m = matrix.length,n = matrix[0].length;
        
        // Traversal starts at top right corner
        // If value is greater than target, go left(all values in this column will also be greater due to matrix property)
        // If value is less than target, go down(all values in this row will also be small )
        // This way, at every point, one row/column is removed => Time complexity is O(m+n)
        int i = 0,j=n-1;
        while(j>=0 && i<m){
            if(matrix[i][j]==target)return true;
            else if(matrix[i][j]>target)j--;
            else i++;
        }
        return false;
        
    }
}