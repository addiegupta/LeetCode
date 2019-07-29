/*

LeetCode: 56. Merge Intervals

Medium

Link: https://leetcode.com/problems/merge-intervals/submissions/

Topics: Array, Sort

Acceptance: 36.1

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considered overlapping.
NOTE: input types have been changed on April 15, 2019. Please reset to default code definition to get new method signature.

*/

class Solution {
    public int[][] merge(int[][] intervals) {
        
        // No overlaps possible
        if(intervals.length<=1)return intervals;
        
        // Sort the intervals based on their start time
        Arrays.sort(intervals,(i1,i2)-> Integer.compare(i1[0],i2[0]));
        
        // Store merged intervals in List
        List<int[]> ans = new ArrayList();

        // Keep track of a previous interval which will be extended based on overlaps
        int[] prev = intervals[0];
        ans.add(prev);

        for(int[] interval: intervals){
            
            // overlap; extend previous interval if applicable
            if(interval[0]<=prev[1]){
                prev[1] = Math.max(interval[1],prev[1]);
            }
            // No overlap, add this interval to ans list
            else {
                prev = interval;
                ans.add(prev);
            }
        }
        // Convert to array to return
        return ans.toArray(new int[ans.size()][]);
    }
}