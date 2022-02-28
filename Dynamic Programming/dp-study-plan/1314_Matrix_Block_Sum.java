/*

LeetCode: 1314. Matrix Block Sum

Medium

Link: https://leetcode.com/problems/matrix-block-sum/

Topics: Dynamic Programming, Array, Matrix, Prefix Sum

Acceptance: 75

 Given a m x n matrix mat and an integer k, return a matrix answer where each answer[i][j] is the sum of all elements mat[r][c] for:

i - k <= r <= i + k,
j - k <= c <= j + k, and
(r, c) is a valid position in the matrix.
 

Example 1:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 1
Output: [[12,21,16],[27,45,33],[24,39,28]]
Example 2:

Input: mat = [[1,2,3],[4,5,6],[7,8,9]], k = 2
Output: [[45,45,45],[45,45,45],[45,45,45]]
 

Constraints:

m == mat.length
n == mat[i].length
1 <= m, n, k <= 100
1 <= mat[i][j] <= 100

 */

class Solution {
    int m,n;
    int range;
    public int[][] matrixBlockSum(int[][] mat, int k) {
        if(mat == null || mat.length == 0){
            return null;
        }
        m = mat.length;
        n = mat[0].length;
        range = k;
        //return initialUntidySolution(mat, k);
        return cleanLcSolution(mat, k);
    }
    private int[][] cleanLcSolution(int[][] mat, int k){
        int[][] sum = new int[m + 1][n + 1];
        int[][] ans = new int[m][n];
        // construct sum matrix which contains sum of submatrix from 0,0 to i,j at every i,j
        // keeping from 1 to n+1 instead of 0 to n so that -1 in i does not become negative
        for(int i = 1; i <= m ; i++){
            for(int j = 1; j <=n; j++){
                // new value in matrix for i,j + sums of submatrix 1 row less and 1 col less - the overlapping matrix that got added twice
                sum[i][j] = mat[i - 1][j - 1] + sum[i - 1][j] + sum[i][j - 1] - sum[i-1][j-1];
            }
        }
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // get the starting and end row and col for required matrix at i, j
                // min, max for balancing boundary conditions
                int r1 = Math.max(0, i - k);
                int r2 = Math.min(m - 1, i + k);
                int c1 = Math.max(0, j - k);
                int c2 = Math.min(n - 1, j + k);
                // add 1 to each since sum matrix starts at 1, not 0
                r1++;
                r2++;
                c1++;
                c2++;
                // entire grid from 0,0 to r2,c2 - non required portions to left and top + sum that got subtracted twice
                ans[i][j] = sum[r2][c2] - sum[r2][c1 - 1] - sum[r1 - 1][c2] + sum[r1 - 1][c1 - 1];
            }
        }
        return ans;
    }
    private int[][] initialUntidySolution(int[][] mat, int k) {
        int[][] cols = new int[m][n];
        int[][] rows = new int[m][n];
        int[][] ans = new int[m][n];
        
        // construct cols and rows prefix sum matrix
        // each cols/rows [i][j] contains the sum of 
        // the required row/col for ans[i][j] i.e. row[i][j] will have sum for i-k to i+k
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                if(i == 0){
                    int sum = 0;
                    for(int l = 0; l <= k; l++){
                        sum += getVal(mat, l, j);
                    }
                    cols[i][j] = sum;
                }
                else{
                    int sum = cols[i - 1][j];
                    sum += adjustSum(mat, i, j , true);
                    cols[i][j] = sum;
                }
                
                if(j == 0){
                    int sum = 0;
                    for(int l = 0; l <= k; l++){
                        sum += getVal(mat, i, l);
                    }
                    rows[i][j] = sum;
                } else{
                    int sum = rows[i][j - 1];
                    sum += adjustSum(mat, i, j, false);
                    rows[i][j] = sum;
                }
            }
        }
        
        // construct ans matrix
        int sum = 0;
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                // construct starting grid sum
                if(i == 0 && j == 0){
                    for(int l = 0; l <= k; l++){
                        sum += getVal(cols, i, l);
                    }
                } else if (j == 0){
                    // use value from 1 row above
                    sum = ans[i - 1][0];
                    sum += adjustSum(rows, i, j , true);
                }
                else{
                    sum += adjustSum(cols, i, j , false);
                }
                ans[i][j] = sum;
            }
        }
        return ans;
    }
    // remove 1 col/row and add 1 col/row to get new grid
    private int adjustSum(int[][] matrix, int i, int j, boolean rowChange){
        int sum = 0;
        if(rowChange){
            sum -= getVal(matrix, i - range - 1, j);
            sum += getVal(matrix, i + range, j);
        } else{
            sum -= getVal(matrix, i, j - range - 1);
            sum += getVal(matrix, i, j + range);
        }
        return sum;
    }
    private int getVal(int[][] matrix, int i, int j){
        if(i < 0 || j < 0 || i >= m || j >=n){
            return 0;
        }
        return matrix[i][j];
    }
}
