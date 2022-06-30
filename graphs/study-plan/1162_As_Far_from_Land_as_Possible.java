/*

LeetCode: 1162. As Far from Land as Possible

Medium

Link: https://leetcode.com/problems/as-far-from-land-as-possible/

Topics: Graph, Breadth first search, Matrix, Array, Dynamic Programming

Acceptance: 48.0

Given an n x n grid containing only values 0 and 1, where 0 represents water and 1 represents land, find a water cell such that its distance to the nearest land cell is maximized, and return the distance. If no land or water exists in the grid, return -1.

The distance used in this problem is the Manhattan distance: the distance between two cells (x0, y0) and (x1, y1) is |x0 - x1| + |y0 - y1|.

 

Example 1:


Input: grid = [[1,0,1],[0,0,0],[1,0,1]]
Output: 2
Explanation: The cell (1, 1) is as far as possible from all the land with distance 2.
Example 2:


Input: grid = [[1,0,0],[0,0,0],[0,0,0]]
Output: 4
Explanation: The cell (2, 2) is as far as possible from all the land with distance 4.
 

Constraints:

n == grid.length
n == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1

*/

class Solution {
    int n;
    int[][] visited;
    int[][] dist;
    int[] rows = {-1, 0, 1, 0};
    int[] cols = {0, -1, 0, 1};
    int ans = -1;
    
    class Node {
        int x;
        int y; 
        int dist;
        Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public int maxDistance(int[][] grid) {
        if(grid == null || grid.length == 0){
            return 0;
        }
        n = grid.length;
        dist = new int[n][n];
        visited = new int[n][n];
        // initialise distance to inifinity
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
        
        //return initialUnnecessaryBFSSolution(grid);
        //return improvedBfsSolution(grid);
        return bestDpSolution(grid);
    }
    // Time: O(n^2)
    // Space: O(n^2)
    private int bestDpSolution(int[][] grid) {
        ans = 0;
        // using Integer.MAX_VALUE causes wrong answer due to overflow for some cases
        int MAX = 201;
        // calculate min distances from land from top and left
        // later, same would be done from bottom and right to cover all distances
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                // skip land
                if(grid[i][j] == 1){
                    continue;
                }
                // initialise distance to infinity
                grid[i][j] = MAX;
                // 
                if(i - 1 >= 0){
                    grid[i][j] = Math.min(grid[i][j], grid[i-1][j] + 1);
                }
                if(j - 1 >= 0){
                    grid[i][j] = Math.min(grid[i][j], grid[i][j-1] + 1);
                }
            }
        }
        for(int i = n - 1 ; i >= 0; i--){
            for(int j = n - 1 ; j >= 0; j--){
                if(grid[i][j] == 1){
                    continue;
                }
                if(i + 1 < n){
                    grid[i][j] = Math.min(grid[i][j], grid[i+1][j] + 1);
                }
                if(j + 1 < n){
                    grid[i][j] = Math.min(grid[i][j], grid[i][j+1] + 1);
                }
                ans = Math.max(ans, grid[i][j]);
            }
        }
        // value if MAX, no path exists return -1
        return ans == MAX ? -1 : ans - 1; 
    }
    // Time: O(n^2)
    // Space: O(n^2)
    private int improvedBfsSolution(int[][] grid) {
        Queue<Node> q = new LinkedList();
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                // multisource bfs, start from all land
                if(grid[i][j] == 1){
                    q.offer(new Node(i, j, 0));
                }
            }
        }
        int ans = -1;
        while(!q.isEmpty()){
            Node node = q.poll();
            int i = node.x;
            int j = node.y;
            int length = node.dist;
            // check for bounds
            // skip if land which is not a starting point
            // skip if already lesser distance present i.e. already visited && this path would lead to incorrect value, as closer land already present for this point
            if(i < 0 || j < 0 || i >= n || j >= n || (grid[i][j] == 1 && length > 0) ||  dist[i][j] <= length){
            continue;
            }
            dist[i][j] = length;
            for(int x = 0 ; x < 4; x++){
                int newI = i + rows[x];
                int newJ = j + cols[x];
                q.offer(new Node(newI, newJ, length + 1));
            }
        }
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                if(dist[i][j] != 0 && dist[i][j] != Integer.MAX_VALUE){
                    ans = Math.max(ans, dist[i][j]);
                }
            }
        }
        
        return ans;
    }
    
    // Time: O(n^4)? potentially doing bfs from every node and then clearing visited
    // Space: O(n^2)
    private int initialUnnecessaryBFSSolution(int[][] grid) {
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                // start from every land, do a BFS and then clear visited matrix
                // for future iterations
                if(grid[i][j] == 1){
                    visit(grid, i , j);
                    clearVisited();
                }
            }
        }
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                if(grid[i][j] == 0){
                    ans = Math.max(ans, dist[i][j]);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
    private void clearVisited(){
        for(int i = 0 ; i < n; i++){
            for(int j = 0 ; j < n; j++){
                visited[i][j] = 0;
            }
        }
    }
    private void visit(int[][] grid, int i, int j){
        Queue<Node> q = new LinkedList();
        q.offer(new Node(i, j, 0));
        while(!q.isEmpty()){
            Node node = q.poll();
            int a = node.x;
            int b = node.y;
            int length = node.dist;
            if(a < 0 || b < 0 || a >= n || b >= n || (grid[a][b] == 1 && length > 0) || visited[a][b] == 1 || dist[a][b] <= length){
            continue;
        }
            dist[a][b] = length;
            visited[a][b] = 1;
            for(int x = 0 ; x < 4; x++){
                int newI = a + rows[x];
                int newJ = b + cols[x];
                q.offer(new Node(newI, newJ, length + 1));
            }
        }
    }
}
