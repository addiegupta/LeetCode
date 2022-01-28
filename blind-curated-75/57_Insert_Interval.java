/*

LeetCode: 57. Insert Interval

Medium

Link: https://leetcode.com/problems/insert-interval/

Topics: Array, Greedy

Acceptance: 37

You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.

Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).

Return intervals after the insertion.

 

Example 1:

Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]
Example 2:

Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 

Constraints:

0 <= intervals.length <= 104
intervals[i].length == 2
0 <= starti <= endi <= 105
intervals is sorted by starti in ascending order.
newInterval.length == 2
0 <= start <= end <= 105

*/

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

	// both are O(n) time and O(n) space (counting output array, else O(1))
	//return initialUntidySolution(intervals, newInterval);
	return cleanLcSolution(intervals, newInterval);
    }

    // BUD Optimisation U: doing unnecessary work in this algo
    // not taking full advantage of fact that initial array has no overlaps
    // i.e. after all merging is done for newInterval, there cannot be any more overlaps
    private int[][] initialUntidySolution(int[][] intervals, int[] newInterval) {

        // for empty initial list case, simply return new list with newInterval
        if(intervals == null || intervals.length == 0){
            int[][] ans = new int[1][];
            ans[0] = newInterval;
            return ans;
        }
        
        int n = intervals.length;
        // pos will determine where newInterval is to be inserted
        int pos;
        for(pos = 0; pos < n; pos++){
            int prevStart = pos == 0 ? Integer.MIN_VALUE : intervals[pos-1][0];
            int nextStart = intervals[pos][0];
            if( prevStart <= newInterval[0] && newInterval[0] <= nextStart ){
                break;
            }
        }
        List<int[]> ans = new ArrayList();
        
        int[] prevInterval = intervals[0];
        int i = 1;
        // need to insert at start, iteration on intervals will begin at 0
        if(pos == 0){
            prevInterval = newInterval;
            pos = -1;
            i = 0;
        }
        ans.add(prevInterval);
        while(i <= n){
            int[] interval;
            if(i == pos){
                interval = newInterval;
                pos = -1;
            } else if(i==n){
                break;
            } else{
                interval = intervals[i];
                i++;
            }
            // overlap detected, adjust prevInterval end accordingly
            if(prevInterval[1] >= interval[0]){
                prevInterval[1] = Math.max(prevInterval[1], interval[1]);
            } else{
                // no overlap, add prevInterval to ans list and set curr as prev
                prevInterval = interval;
                ans.add(interval);
            }
        }
        return ans.toArray(new int[ans.size()][]);
    }
    private int[][] cleanLcSolution(int[][] intervals, int[] newInterval) {
        int i = 0;
        int n = intervals.length;
        List<int []> ans = new ArrayList();
        
        // since intervals has no overlaps already, add all intervals less than newInterval
        // to ans. end of prev is less than start of new interval
        while (i < n && intervals[i][1] < newInterval[0]){
            ans.add(intervals[i]);
            i++;
        }
        // position of new interval is found
        // need to merge overlaps for next few intervals till applicable
        int start = newInterval[0];
        int end = newInterval[1];
        // overlap detected, adjust start and end to maximise the interval
        // i.e. min of start and max of end for overlapping intervals
        while(i < n && end >= intervals[i][0]){
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        int[] mergedNewInterval = {start, end};
        ans.add(mergedNewInterval);
        // add all remaining intervals, there are no overlaps in them as per input constraint
        while(i < n){
            ans.add(intervals[i]);
            i++;
        }
        
	    return ans.toArray(new int[ans.size()][]);
    }
}
