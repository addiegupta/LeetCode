/*

LeetCode: 37. Sudoku Solver

Hard

Link: https://leetcode.com/problems/sudoku-solver/

Topics: Arrays, Backtracking, Matrix

Acceptance: 54.6

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

Each of the digits 1-9 must occur exactly once in each row.
Each of the digits 1-9 must occur exactly once in each column.
Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
The '.' character indicates empty cells.

 

Example 1:


Input: board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
Output: [["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
Explanation: The input board is shown above and the only valid solution is shown below:


 

Constraints:

board.length == 9
board[i].length == 9
board[i][j] is a digit or '.'.
It is guaranteed that the input board has only one solution.
 
*/

class Solution {
    private int getSubgrid(int i, int j){
        return (i/3 * 3) + j/3;
    }
    int n;
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0){
            return;
        }
        n = 9;
        List<Set<Character>> rowSets = new ArrayList();
        List<Set<Character>> colSets = new ArrayList();
        List<Set<Character>> subgridSets = new ArrayList();
        for(int i = 0 ; i < n; i++){
            rowSets.add(new HashSet());
            colSets.add(new HashSet());
            subgridSets.add(new HashSet());
        }
        
        // get index to start backtracking process from
        int startI = -1;
        int startJ = -1;
        // fill up sets with prexisting values in grid
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j  < n; j++){
                char num = board[i][j];
                if(board[i][j] != '.'){
                    rowSets.get(i).add(num);
                    colSets.get(j).add(num);
                    subgridSets.get(getSubgrid(i, j)).add(num);
                } else if(startI == -1){
                    startI = i;
                    startJ = j;
                }
            }
        }
        backtrack(board, rowSets, colSets, subgridSets, startI, startJ);
    }
    private boolean backtrack(char[][] grid, List<Set<Character>> rowSets, List<Set<Character>> colSets, List<Set<Character>> subgridSets, int i, int j){
        // covered entire grid, solution found
        if(i == n){
            return true;
        }
        // out of bounds of row, move to next row
        if(j == n){
            return backtrack(grid, rowSets, colSets, subgridSets, i + 1, 0);
        }
        // already filled, move to next cell
        if(grid[i][j] != '.'){
            return backtrack(grid, rowSets, colSets, subgridSets, i, j + 1);
        }
        // backtrack by filling and trying every number
        for (int x = 1; x <= n ; x++){
            char c = (char)(x +'0');
            boolean inRow = rowSets.get(i).contains(c);
            boolean inCol = colSets.get(j).contains(c);
            boolean inSubgrid = subgridSets.get(getSubgrid(i, j)).contains(c);
            // not suitable number, already present in same row, col or subgrid
            if(inRow || inCol || inSubgrid){
                continue;
            }
            int subgrid = getSubgrid(i, j);
            rowSets.get(i).add(c);
            colSets.get(j).add(c);
            subgridSets.get(subgrid).add(c);
            grid[i][j] = c;
            if(backtrack(grid, rowSets, colSets, subgridSets, i, j + 1)){
                return true;
            }
            grid[i][j] = '.';
            rowSets.get(i).remove(c);
            colSets.get(j).remove(c);
            subgridSets.get(subgrid).remove(c);
        }
        // no solution found for current state, return false
        return false;
    }
}
