/*

LeetCode: 84. Largest Rectangle in Histogram

Hard

Link: https://leetcode.com/problems/largest-rectangle-in-histogram/

Topics: Array, Stack, Monotonic Stack

Acceptance: 40.8

 Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

 

Example 1:


Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.
Example 2:


Input: heights = [2,4]
Output: 4
 

Constraints:

1 <= heights.length <= 105
0 <= heights[i] <= 104

 */

class Solution {
    private class Rect{
        int height;
        int ind;
        Rect(int height, int ind){
            this.height = height;
            this.ind = ind;
        }
    }

    public int largestRectangleArea(int[] heights) {
        if(heights == null || heights.length == 0){
            return 0;
        }
        return monotonicStackSolution(heights);
    }

    public int monotonicStackSolution(int[] heights) {
        int n = heights.length;
	// keep monotonic stack of decreasing heights (from top)
	// whenever a smaller height is encountered, previous larger heights cannot be used for rectangle hence keep popping them
        Deque<Rect> stack = new ArrayDeque<Rect>();
        int max = heights[0]    ;
        stack.push(new Rect(heights[0], 0));
        for(int i = 1; i <= n ; i++){
            // for i ==n is the case to compute areas for last building
            int height = i == n ? 0 : heights[i];
            // smaller building, remove all higher buildings and replace with this height for leftmost index obtained by popping
            if(height < stack.peek().height){
                Rect r = null;
                while(!stack.isEmpty() && stack.peek().height >= height){
                    r = stack.pop();
                    max = Math.max(max, r.height * (i - r.ind));
                }
                if(r != null){
                    stack.push(new Rect(height, r.ind));
                }
            }
            if(stack.peek().height != height){
                stack.push(new Rect(height, i));
            }
        }
        return max;
    }
}
