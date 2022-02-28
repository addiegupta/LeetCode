/*

LeetCode: 221. Maximal Square

Medium

Link: https://leetcode.com/problems/maximal-square/

Topics: Dynamic Programming, Array, Matrix

Acceptance: 43.1

Given an m x n binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

 

Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 4
Example 2:


Input: matrix = [["0","1"],["1","0"]]
Output: 1
Example 3:

Input: matrix = [["0"]]
Output: 0
 

Constraints:

m == matrix.length
n == matrix[i].length
1 <= m, n <= 300
matrix[i][j] is '0' or '1'.

 */

class Solution {
    public int maximalSquare(char[][] matrix) {
        if(matrix == null){
            return 0;
        }
        //return initialSquaredLogSolution(matrix);
        return cleanLcSquaredSolution(matrix);
    }
    
    // Time: O(n ^ 2) single pass through each element in matrix
    // Space: O(n ^ 2) dp matrix for every element; can be reduced to O(n) as only prev row is reqd
    private int cleanLcSquaredSolution(char[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
    
        int[][] dp = new int[m + 1][n + 1];
        int maxLength = 0;
        
        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(matrix[i - 1][j - 1] == '1'){
                    // dp[i][j] denotes the max length of square ending at i,j containing all 1s
                    // the min length from all 3(top left, left and top) will limit the max square containing 1s that can be formed till here
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                    maxLength = Math.max(maxLength, dp[i][j]);
                }
            }
        }
        return maxLength * maxLength;
    }
    
    // very similar approach to Matrix Block Sum https://leetcode.com/problems/matrix-block-sum/
    // Space: O(n ^ 2); dp matrix of same dimensions
    // Time: O(n ^ 2 * log(n)); using divide and conquer on 1 - n (log n time), k is found using binary search. for every k, n ^ 2 indices have to be checked
    private int initialSquaredLogSolution(char[][] matrix){
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] countDp = new int[m + 1][n + 1];
        // countDp at i, j stores the count of 1s of the matrix from top left 0,0 to bottom right i-1, j-1
        // keeping i - 1 to avoid boundary conditions for 0 value i,j
        for(int i = 1; i <= m ; i++){
            for(int j = 1; j <= n; j++){
                // value at (i - 1, j - 1) + sum of matrix to left and top of this value - overlapping sum added twice
                int isOne = matrix[i - 1][j - 1] == '1' ? 1 : 0;
                countDp[i][j] = isOne + countDp[i - 1][j] + countDp[i][j - 1] - countDp[i - 1][j - 1];
            }
        }
        // use binary search to find optimal length of maximal square
        int l = 1;
        int r = Math.min(m, n);
        int bestAns = 0;
        
        while(l <= r){
            int k = l + (r - l) / 2;
            boolean squareExists = false;
            
            for(int i = 0 ; i <= m - k; i++){
                for(int j = 0; j <= n - k; j++){
                    // get count of 1s in square from r1, c1 to r2, c2
                    // adding 1 as countDp is 1 based and not 0 based
                    int r1 = i + 1;
                    int r2 = i + k;
                    int c1 = j + 1;
                    int c2 = j + k;
                    int count = countDp[r2][c2] - countDp[r1 - 1][c2] - countDp[r2][c1 - 1] + countDp[r1 - 1][c1 - 1];
                    if(count == k * k){
                        squareExists = true;
                        bestAns = Math.max(bestAns, k * k);
                        break;
                    }
                }
            }
            
            // square of length k exists; check if we can do better; increase length
            if(squareExists){
                l = k + 1;
            } else{
                r = k - 1;
            }
        }
        return bestAns;
    }
}
