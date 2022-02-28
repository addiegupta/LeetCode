/*

LeetCode: 120. Triangle

Medium

Link: https://leetcode.com/problems/triangle/

Topics: Dynamic Programming, Array

Acceptance: 50

Given a triangle array, return the minimum path sum from top to bottom.

For each step, you may move to an adjacent number of the row below. More formally, if you are on index i on the current row, you may move to either index i or index i + 1 on the next row.

 

Example 1:

Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
Output: 11
Explanation: The triangle looks like:
   2
  3 4
 6 5 7
4 1 8 3
The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).
Example 2:

Input: triangle = [[-10]]
Output: -10
 

Constraints:

1 <= triangle.length <= 200
triangle[0].length == 1
triangle[i].length == triangle[i - 1].length + 1
-104 <= triangle[i][j] <= 104
 

Follow up: Could you do this using only O(n) extra space, where n is the total number of rows in the triangle? 
 
 */

// very similar to 931, Minimum Falling Path Sum
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle == null){
            return 0;
        }

        int n = triangle.size();
	// use 2 arrays of size n rather than entire matrix since only 2 rows are relevant, old data is not used again of top rows
        int[] prev = new int[n];
        int[] next = new int[n];
        int[] temp;
        int ans = Integer.MAX_VALUE;
        for(int i = 0 ; i < n ; i++){
            for(int j = 0; j <= i; j++){
		// get min of the 2 possible parents which can create path, to obtain best possible path sum for current node
                int a = j == 0 ? prev[j] : prev[j - 1];
                // for j == 0, j - 1 does not exist; need this corner case to avoid separately storing first row in prev
                int b = (j == i && j != 0) ? prev[j - 1] : prev[j];
                int min = Math.min(a, b);

                next[j] = triangle.get(i).get(j) + min;
                
                // for last row, compute min sum paths
                if(i == n - 1){
                    ans = Math.min(ans, next[j]);
                }
            }
	    
            // swap prev and next; next would have garbage values
            temp = prev;
            prev = next;
            next = temp;
        }
        return ans;
    }
}
