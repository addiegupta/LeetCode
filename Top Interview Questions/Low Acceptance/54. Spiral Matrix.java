/*

LeetCode: 54. Spiral Matrix

Medium

Link: https://leetcode.com/problems/spiral-matrix/

Topics: Array

Acceptance: 30.7

Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]
 */
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> ans = new ArrayList();
        if(matrix==null || matrix.length==0)return ans;
        
        // Directions: right,down,left then up
        int row[] = {0,1,0,-1};
        int col[] = {1,0,-1,0};
        
        // Movement variables
        int i=0,j=0,newI,newJ,nav=0;
        
        // Start and end limits
        int rStart=0,rEnd=matrix.length-1,cStart=0,cEnd=matrix[0].length-1;
        
        // Spiral traverse
        while(rStart<=rEnd && cStart<=cEnd){
            ans.add(matrix[i][j]);

            // Position for next iteration
            newI = i+row[nav];
            newJ = j+col[nav];

            // Next iteration position is out of bounds (i.e. out of matrix OR been traversed before)
            if(newI<rStart || newI>rEnd || newJ<cStart || newJ>cEnd){
                // Change direction
                nav=(nav+1)%4;
                // Adjust row/column limits
                switch(nav){
                    case 0: cStart++;
                            break;
                    case 1:rStart++;
                            break;
                    case 2: cEnd--;
                            break;
                    case 3: rEnd--;
                            break;
                }
            }
            // Update position for next iteration
            i = i+row[nav];
            j = j+col[nav];
        }
        return ans;
    }
}