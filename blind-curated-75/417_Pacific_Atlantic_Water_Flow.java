/*

LeetCode: 417. Pacific Atlantic Water Flow

Medium

Link: https://leetcode.com/problems/pacific-atlantic-water-flow/

Topics: Array, Depth First Search, Breadth First Search, Matrix

Acceptance: 55

 There is an m x n rectangular island that borders both the Pacific Ocean and Atlantic Ocean. The Pacific Ocean touches the island's left and top edges, and the Atlantic Ocean touches the island's right and bottom edges.

The island is partitioned into a grid of square cells. You are given an m x n integer matrix heights where heights[r][c] represents the height above sea level of the cell at coordinate (r, c).

The island receives a lot of rain, and the rain water can flow to neighboring cells directly north, south, east, and west if the neighboring cell's height is less than or equal to the current cell's height. Water can flow from any cell adjacent to an ocean into the ocean.

Return a 2D list of grid coordinates result where result[i] = [ri, ci] denotes that rain water can flow from cell (ri, ci) to both the Pacific and Atlantic oceans.

 

Example 1:


Input: heights = [[1,2,2,3,5],[3,2,3,4,4],[2,4,5,3,1],[6,7,1,4,5],[5,1,1,2,4]]
Output: [[0,4],[1,3],[1,4],[2,2],[3,0],[3,1],[4,0]]
Example 2:

Input: heights = [[2,1],[1,2]]
Output: [[0,0],[0,1],[1,0],[1,1]]
 

Constraints:

m == heights.length
n == heights[r].length
1 <= m, n <= 200
0 <= heights[r][c] <= 105
 
*/
// approach: instead of starting from a node and then checking if path exists from there to edges, taking inverse approach
// 		 i.e. start at edge and mark all the nodes which are reachable from edge (increasing or equal value path rather than
// 		 decreasing or equal value path)
// implemented both bfs and dfs solutions
class Solution {
    // dimensions of grid
    int m,n;
	// go North South East West
        int[] row = {0,0,1,-1};
        int[] col = {1,-1,0,0};
    
	// switch for bfs, dfs solutions
	boolean useBFS = false;
    
    // class to store coordinates
    public class Point{
        int x,y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
	// checks bounds of point within grid
        public boolean isValid(){
            return x >= 0 && x < m && y >=0 && y < n;
        }
	// return new point by moving x and y from existing point
        public Point createNewPoint(int moveX, int moveY){
            return new Point(x + moveX, y + moveY);
        }
    }
    
    // approach: instead of starting from a node and then checking if path exists from there to edges, taking inverse approach
    // 		 i.e. start at edge and mark all the nodes which are reachable from edge (increasing or equal value path rather than
    // 		 decreasing or equal value path)
    private void markNodesBFS(Queue<Point> q, boolean[][] match, int[][] grid){
        
        while(!q.isEmpty()){
            Point point = q.poll();
	    
	    // already marked as valid node, skip
            if(match[point.x][point.y]){
                continue;
            }
	    // mark as valid node
            match[point.x][point.y] = true;
            
	    // BFS
            for(int i=0;i<4;i++){
                Point newPoint = point.createNewPoint(row[i], col[i]);
		// 1. isValid: check bounds
		// 2. if matched already, no need to repeat for this node
		// 3. value of neighbor should be greater or equal for a valid path
                if(newPoint.isValid() && !match[newPoint.x][newPoint.y] && grid[newPoint.x][newPoint.y] >= grid[point.x][point.y] ){
                    q.offer(newPoint);
                }
            }
        }
        
    }
    private void dfs(Point point, int prevHeight, boolean[][] match, int[][] grid){
	    if(!point.isValid() || match[point.x][point.y] || grid[point.x][point.y] < prevHeight){
		    return;
	    }
	    match[point.x][point.y] = true;
	    for(int i = 0; i < 4; i++){
		    Point newPoint = point.createNewPoint(row[i], col[i]);
		    dfs(newPoint, grid[point.x][point.y], match, grid);
	    }
    }
    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        List<List<Integer>> ans = new ArrayList();
        if(heights == null || heights.length == 0 || heights[0].length==0){
            return ans;
        }
        m = heights.length;
        n = heights[0].length;
            
        boolean[][] pac = new boolean[m][n];
        boolean[][] atl = new boolean[m][n];
        Queue<Point> pacQ = new LinkedList();
        Queue<Point> atlQ = new LinkedList();
        
	// pacific edges top and left
	// atlantic edges bottom and right
        for(int i=0;i<m;i++){
	    if(useBFS){
            	pacQ.offer(new Point(i,0));
            	atlQ.offer(new Point(i,n-1));
	    }else{
		dfs(new Point(i,0), 0, heights, pac);
		dfs(new Point(i,n-1), 0, heights, atl);
	    }
        }
        for(int i=0;i<n;i++){
	    if(useBFS){
           	 pacQ.offer(new Point(0, i));
           	 atlQ.offer(new Point(m-1, i));
	    }else{
		 dfs(new Point(0, i), 0, heights, pac);
		 dfs(new Point(m-1, i), 0, heights, atl);
	    }
        }
	if(useBFS){

       		 markNodesBFS(pacQ, pac, heights);
       		 
       		 markNodesBFS(atlQ, atl, heights);
	}         
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
		// ans should contain nodes that will reach both pacific and atlantic
                if(pac[i][j] && atl[i][j]){
                    List list = new ArrayList();
                    list.add(i);
                    list.add(j);
                    ans.add(list);
                }
            }
        }
        return ans;
    }
}
