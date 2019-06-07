/*

LeetCode: 36. Valid Sudoku

Medium

Link: https://leetcode.com/problems/valid-sudoku/

Topics: Hash Table

Acceptance: 43.2

Determine if a 9x9 Sudoku board is valid. Only the filled cells need to be validated according to the following rules:

Each row must contain the digits 1-9 without repetition.
Each column must contain the digits 1-9 without repetition.
Each of the 9 3x3 sub-boxes of the grid must contain the digits 1-9 without repetition.

A partially filled sudoku which is valid.

The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

Example 1:

Input:
[
  ["5","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: true
Example 2:

Input:
[
  ["8","3",".",".","7",".",".",".","."],
  ["6",".",".","1","9","5",".",".","."],
  [".","9","8",".",".",".",".","6","."],
  ["8",".",".",".","6",".",".",".","3"],
  ["4",".",".","8",".","3",".",".","1"],
  ["7",".",".",".","2",".",".",".","6"],
  [".","6",".",".",".",".","2","8","."],
  [".",".",".","4","1","9",".",".","5"],
  [".",".",".",".","8",".",".","7","9"]
]
Output: false
Explanation: Same as Example 1, except with the 5 in the top left corner being 
    modified to 8. Since there are two 8's in the top left 3x3 sub-box, it is invalid.
Note:

A Sudoku board (partially filled) could be valid but is not necessarily solvable.
Only the filled cells need to be validated according to the mentioned rules.
The given board contain only digits 1-9 and the character '.'.
The given board size is always 9x9.

 */
class Solution {
    private boolean bitManipulationMethod(char[][] board){
        int row=0,col=0,grid=0,n=9;
        int rowNum,colNum,gridNum;
        
        for(int i=0;i<n;i++){
          
            // Encode the grid numbers in bits  
            row=0;
            col=0;
        
            for(int j=0;j<n;j++){
                if(board[i][j]!='.'){   
                    rowNum = board[i][j]-'0';
                    if( ((row>>rowNum) & 1)==1)return false;
                    row |= (1<<rowNum);
                }
                if(board[j][i]!='.'){
                    colNum = board[j][i]-'0';
                    if( ((col>>colNum) & 1)==1)return false;
                    col |= (1<<colNum);
                }
            }
        }
        for(int k=0;k<n;k++){
            grid=0;
            for(int i=3*(k/3);i<(3*(k/3))+3;i++){
                for(int j=3*(k%3);j<(3*(k%3))+3;j++){
                    if(board[i][j]!='.'){   
                        gridNum = board[i][j]-'0';
                        if( ((grid>>gridNum) &1)==1)return false;
                        grid |= (1<<gridNum);
                    }
                }    
            }
        }
        return true;
    }
    public boolean isValidSudoku(char[][] board) {
        // Similarly hash sets can be used in place of bit method
        return bitManipulationMethod(board);
    }
}