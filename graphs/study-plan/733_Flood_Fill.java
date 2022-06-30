/*

LeetCode: 733. Flood Fill

Easy

Link: https://leetcode.com/problems/flood-fill/

Topics: Graph, Depth first Search, Breadth first search, Matrix, Array

Acceptance: 58.3

An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.

You are also given three integers sr, sc, and newColor. You should perform a flood fill on the image starting from the pixel image[sr][sc].

To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel, plus any pixels connected 4-directionally to those pixels (also with the same color), and so on. Replace the color of all of the aforementioned pixels with newColor.

Return the modified image after performing the flood fill.



Example 1:


Input: image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, newColor = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]
Explanation: From the center of the image with position (sr, sc) = (1, 1) (i.e., the red pixel), all pixels connected by a path of the same color as the starting pixel (i.e., the blue pixels) are colored with the new color.
Note the bottom corner is not colored 2, because it is not 4-directionally connected to the starting pixel.
Example 2:

Input: image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, newColor = 2
Output: [[2,2,2],[2,2,2]]


Constraints:

m == image.length
n == image[i].length
1 <= m, n <= 50
0 <= image[i][j], newColor < 216
0 <= sr < m
0 <= sc < n
 
*/

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image == null || image.length == 0){
            return image;
        }
        int m = image.length;
        int n = image[0].length;
        Queue<Pair<Integer, Integer>> q = new LinkedList();
        q.offer(new Pair(sr, sc));
        int startColor = image[sr][sc];
        if(startColor == newColor)return image;

        image[sr][sc] = newColor;
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        while(!q.isEmpty()){
            Pair<Integer, Integer> node = q.poll();
            int i = node.getKey();
            int j = node.getValue();
            for(int x = 0; x < 4; x++){
                int newI = i + dirs[x][0];
                int newJ = j + dirs[x][1];
                if(newI >=0 && newI < m && newJ >= 0 && newJ < n && image[newI][newJ] == startColor){
                    q.offer(new Pair(newI, newJ));
                    image[newI][newJ] = newColor;
                }
            }
        }
        return image;
    }
}
