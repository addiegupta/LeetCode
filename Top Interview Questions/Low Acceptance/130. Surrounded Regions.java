/*

LeetCode: 130. Surrounded Regions

Medium

Link: https://leetcode.com/problems/surrounded-regions/

Topics: Depth first search, Breadth first search, Union Find

Acceptance: 23.5

Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.

A region is captured by flipping all 'O's into 'X's in that surrounded region.

Example:

X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:

X X X X
X X X X
X X X X
X O X X
Explanation:

Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.

 */
class Solution {
    // Only those islands are not to be converted that are not on the border as all others will be surrounded completely
    public void solve(char[][] board) {

        if(board == null || board.length == 0)return;
        int m = board.length;
        int n = board[0].length;
        int i=0,j=0;
        
        // Mark all islands present on the border of the board
        for(i=0;i<m;i++){
            if(board[i][0]=='O')markRegion(board,i,0);
            if(n>1 && board[i][n-1]=='O')markRegion(board,i,n-1);
        }
        for(j=0;j<n;j++){
            if(board[0][j]=='O')markRegion(board,0,j);
            if(m>1 && board[m-1][j]=='O')markRegion(board,m-1,j);
        }
        
        // All the islands that were marked are to be set as island, others are converted to X
        for(i=0;i<m;i++){
            for(j=0;j<n;j++){
                if(board[i][j]=='O')board[i][j]='X';
                if(board[i][j]=='#')board[i][j]='O';
            }
        }
    }
    int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
    private void markRegion(char[][] board,int i,int j){
        if(i<0 || j<0 || i>= board.length || j>=board[i].length || board[i][j]!='O')return;
        // Marked to later convert back to island
        board[i][j]='#';
        // Traverse other places
        for(int x=0;x<4;x++)markRegion(board,i+dirs[x][0],j+dirs[x][1]);
    }    
}