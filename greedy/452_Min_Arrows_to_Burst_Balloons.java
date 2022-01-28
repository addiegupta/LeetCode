/*

LeetCode: 452. Minimum Number of Arrows to Burst Balloons

Medium

Link: https://leetcode.com/problems/minimum-number-of-arrows-to-burst-balloons/

Topics: Array, Greedy, Sorting

Acceptance: 50.9

 There are some spherical balloons taped onto a flat wall that represents the XY-plane. The balloons are represented as a 2D integer array points where points[i] = [xstart, xend] denotes a balloon whose horizontal diameter stretches between xstart and xend. You do not know the exact y-coordinates of the balloons.

Arrows can be shot up directly vertically (in the positive y-direction) from different points along the x-axis. A balloon with xstart and xend is burst by an arrow shot at x if xstart <= x <= xend. There is no limit to the number of arrows that can be shot. A shot arrow keeps traveling up infinitely, bursting any balloons in its path.

Given the array points, return the minimum number of arrows that must be shot to burst all balloons.

 

Example 1:

Input: points = [[10,16],[2,8],[1,6],[7,12]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 6, bursting the balloons [2,8] and [1,6].
- Shoot an arrow at x = 11, bursting the balloons [10,16] and [7,12].
Example 2:

Input: points = [[1,2],[3,4],[5,6],[7,8]]
Output: 4
Explanation: One arrow needs to be shot for each balloon for a total of 4 arrows.
Example 3:

Input: points = [[1,2],[2,3],[3,4],[4,5]]
Output: 2
Explanation: The balloons can be burst by 2 arrows:
- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
 

Constraints:

1 <= points.length <= 105
points[i].length == 2
-231 <= xstart < xend <= 231 - 1
 
*/

// similar to other interval problems like merge overlapping intervals, insert interval
// approach is greedy, initial step to sort intervals based on end time and then try to minimise
// number of arrows
class Solution {
    public int findMinArrowShots(int[][] points) {
        if(points == null || points.length == 0){
            return 0;
        }
        
	// sort by end of interval
	// using a[1] - b[1] can cause overflow in edge cases, hence use Integer.compare
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));
        
        int n = points.length;
	// n arrows for n balloons, will decrement when overlapping balloon is encountered
        int count = n;
	// prev interval will only be changed when an arrow is not able to pierce anymore balloons
	// idea is that if start of new interval is greater than end of prev interval
	// then arrow that burst prev balloon will not burst this one, hence new arrow will be needed
	// and we can set curr interval as prev for further iterations
	// else, we can just consider the curr interval as being burst by current arrow
        int prevEnd = points[0][1];
        
        for(int i = 1; i < n; i++){
            // start of new interval is included in prev interval, no extra arrow required to burst this balloon	
            if(points[i][0] <= prevEnd){
                count--;
            } else{
		// no overlap, need new arrow hence choose new interval as prev
                prevEnd = points[i][1];
            }
        }
        return count;
    }
}
