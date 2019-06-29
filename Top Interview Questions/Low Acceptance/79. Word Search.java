/*

LeetCode: 79. Word Search

Medium

Link: https://leetcode.com/problems/word-search/

Topics: Array, Backtracking

Acceptance: 31.5

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]

Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.


 */
class Solution {
    // Returns if current character encountered is an alphabet or not
    private boolean isAlphabet(char a){return (a>='A' && a <= 'Z') || (a>='a' && a<='z');}

    // Recursive function
    private boolean recur(char[][] board,String word,int i,int j,int x){
        // Word found
        if(x==word.length())return true;
        
        // Out of Bounds
        if(i<0 || j<0 || i==board.length || j==board[i].length)return false;
        
        // Either this position has already been visited in this recursion instance
        // or the letter is incorrect, so cannot continue search from here
        if(!isAlphabet(board[i][j]) || board[i][j] != word.charAt(x))return false;
        
        // Mark the position as visited, by incrementing 26
        // Now this character will not be detected as an alphabet by isAlphabet
        board[i][j] += 26;
        
        // 4 directions
        int row[] = {-1,0,1,0};
        int col[] = {0,1,0,-1};
        
        // Recur for all 4 directions
        boolean found = false;
        for(int k =0;k<4;k++){
            if(recur(board,word,i+row[k],j+col[k],x+1)){
                found = true;
                break;
            }
        } 

        // Mark as unvisited and backtrack
        board[i][j] -= 26;
        return found;
    }

    public boolean exist(char[][] board, String word) {
        // Starting at every position on the board, search for the given word
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                // If word found, return immediately
                if(recur(board,word,i,j,0))return true;
            }
        }
        return false;
    }
}