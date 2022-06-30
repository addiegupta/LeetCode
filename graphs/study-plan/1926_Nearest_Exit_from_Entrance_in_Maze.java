/*

LeetCode: 1926. Nearest Exit from Entrance in Maze

Medium

Link: https://leetcode.com/problems/nearest-exit-from-entrance-in-maze/

Topics: Graph, Breadth first search, Matrix, Array

Acceptance: 40.9

 You are given an m x n matrix maze (0-indexed) with empty cells (represented as '.') and walls (represented as '+'). You are also given the entrance of the maze, where entrance = [entrancerow, entrancecol] denotes the row and column of the cell you are initially standing at.

In one step, you can move one cell up, down, left, or right. You cannot step into a cell with a wall, and you cannot step outside the maze. Your goal is to find the nearest exit from the entrance. An exit is defined as an empty cell that is at the border of the maze. The entrance does not count as an exit.

Return the number of steps in the shortest path from the entrance to the nearest exit, or -1 if no such path exists.

 

Example 1:


Input: maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
Output: 1
Explanation: There are 3 exits in this maze at [1,0], [0,2], and [2,3].
Initially, you are at the entrance cell [1,2].
- You can reach [1,0] by moving 2 steps left.
- You can reach [0,2] by moving 1 step up.
It is impossible to reach [2,3] from the entrance.
Thus, the nearest exit is [0,2], which is 1 step away.
Example 2:


Input: maze = [["+","+","+"],[".",".","."],["+","+","+"]], entrance = [1,0]
Output: 2
Explanation: There is 1 exit in this maze at [1,2].
[1,0] does not count as an exit since it is the entrance cell.
Initially, you are at the entrance cell [1,0].
- You can reach [1,2] by moving 2 steps right.
Thus, the nearest exit is [1,2], which is 2 steps away.
Example 3:


Input: maze = [[".","+"]], entrance = [0,0]
Output: -1
Explanation: There are no exits in this maze.
 

Constraints:

maze.length == m
maze[i].length == n
1 <= m, n <= 100
maze[i][j] is either '.' or '+'.
entrance.length == 2
0 <= entrancerow < m
0 <= entrancecol < n
entrance will always be an empty cell.

*/

class Solution {
    public int nearestExit(char[][] maze, int[] entrance) {
        if(maze.length == 0){
            return 0;
        }
        int m = maze.length;
        int n = maze[0].length;
        int ans = 0;
        Queue<int[]> q = new LinkedList();
        int[][] visited = new int[m][n];
        q.offer(entrance);
        int[] row = {0, -1, 0, 1};
        int[] col = {-1, 0, 1, 0};
        
        while(!q.isEmpty()){
            int len = q.size();
            // go level by level to find length of shortest path
            while(len-- != 0){
                int[] node = q.poll();
                int i = node[0];
                int j = node[1];
                if(visited[i][j] == 1){
                    continue;
                }
                // on boundary && not same as entrance, exit found
                if((i == 0 || j == 0 || i == m - 1 || j == n -1) && !(i == entrance[0] && j == entrance[1])){
                    return ans;
                }
                visited[i][j] = 1;
                // visit neighbors
                for(int x = 0 ; x < 4; x++){
                    int newI = i + row[x];
                    int newJ = j + col[x];
                    if(newI >=0 && newJ >=0 && newI < m && newJ < n && maze[newI][newJ] == '.' && visited[newI][newJ] != 1){
                        q.offer(new int[]{newI, newJ});
                    }
                }
            }
            ans++;
        }
        // no path found
        return -1;
    }
}
