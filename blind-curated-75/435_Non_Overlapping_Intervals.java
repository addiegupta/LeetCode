/*

LeetCode: 435. Non overlapping intervals

Medium

Link: https://leetcode.com/problems/non-overlapping-intervals/

Topics: Array, Sorting, Greedy, Dynamic Programming

Acceptance: 47.1

 *Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

 

Example 1:

Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
Output: 1
Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.
Example 2:

Input: intervals = [[1,2],[1,2],[1,2]]
Output: 2
Explanation: You need to remove two [1,2] to make the rest of the intervals non-overlapping.
Example 3:

Input: intervals = [[1,2],[2,3]]
Output: 0
Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 

Constraints:

1 <= intervals.length <= 105
intervals[i].length == 2
-5 * 104 <= starti < endi <= 5 * 104

*/

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals == null || intervals.length < 2){
            return 0;
        }
        
        // sort in ascending order of end times
        Arrays.sort(intervals, (a,b) -> a[1] - b[1]);
        
        int count = 0;
        int prevEnd = intervals[0][1];
        
        for(int i = 1; i < intervals.length; i++){
            // start of b is before end of a, then this interval is to be removed
            if(intervals[i][0] < prevEnd){
                count++; 
            } else{
		// no overlap, pick current interval as prev for next iterations
                prevEnd = intervals[i][1];
            }
        }
        return count;
    }
}
