/*

LeetCode: 746. Min Cost Climbing Stairs

Easy

Link: https://leetcode.com/problems/minimum-falling-path-sum/

Topics: Dynamic Programming, Array, Matrix

Acceptance: 66.9

Given an n x n array of integers matrix, return the minimum sum of any falling path through matrix.

A falling path starts at any element in the first row and chooses the element in the next row that is either directly below or diagonally left/right. Specifically, the next element from position (row, col) will be (row + 1, col - 1), (row + 1, col), or (row + 1, col + 1).

 

Example 1:


Input: matrix = [[2,1,3],[6,5,4],[7,8,9]]
Output: 13
Explanation: There are two falling paths with a minimum sum as shown.
Example 2:


Input: matrix = [[-19,57],[-40,-5]]
Output: -59
Explanation: The falling path with a minimum sum is shown.
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 100
-100 <= matrix[i][j] <= 100
 
 */
class Solution {
    public int minFallingPathSum(int[][] matrix) {
        if(matrix == null || matrix.length == 0){
            return 0;
        }

        int n = matrix.length;
	// cannot use one array only as sum could be from diagonals as well, would need previous row
        int[] prev = new int[n];
        int[] next = new int[n];
        int[] temp;
        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
		// boundary conditions, get possible locations for fall, then pick minimum and add to current matrix value
		// at end, last row would have been reached, pick min in entire row for ans
                int a = j == 0 ? prev[j] : prev[j - 1];
                int b = prev[j];
                int c = j == n - 1 ? prev[j] : prev[j + 1];
                int min = Math.min(a, Math.min(b, c));
                next[j] = matrix[i][j] + min;
                if(i == n - 1){
                    ans = Math.min(ans, next[j]);
                }
            }
	    // use next as prev for next iteration
	    // setting next to prev as garbage values, could have initialised empty array as well but reusing memory
            temp = next;
            next = prev;
            prev = temp;
        }
        return ans;
    }
}
