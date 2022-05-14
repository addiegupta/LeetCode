/*

LeetCode: 74. Search a 2D Matrix

Medium

Link: https://leetcode.com/problems/search-a-2d-matrix/

Topics: Array, Binary Search, Matrix

Acceptance: 44.4

Write an efficient algorithm that searches for a value target in an m x n integer matrix matrix. This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.


Example 1:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true
Example 2:


Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false


Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 100
-104 <= matrix[i][j], target <= 104

 
 */

class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0){
            return false;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        int range = m * n;
        int lo = 0;
        int hi = range - 1;
        while(lo <= hi){
	    // treat 2d matrix as single 1d matrix joined row to row
	    // apply binary search on range 0 to m * n and get row col to use 2d matrix
            int mid = lo + (hi - lo) / 2;
            int row = mid / n;
            int col = mid % n;
            int num = matrix[row][col];
            if(num == target){
                return true;
            } else if (num > target){
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return false;
    }
}
