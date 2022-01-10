/*

LeetCode: 1010. Pairs of Songs With Total Durations Divisible by 60

Medium

Link: https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

Topics: Array, Hash Table, Counting

Acceptance: 53

You are given a list of songs where the ith song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.



Example 1:

Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: time = [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.


Constraints:

1 <= time.length <= 6 * 104
1 <= time[i] <= 500

*/
class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        if(time == null || time.length == 0){
            return 0;
        }
        int[] map = new int[60];
        int ans = 0;
        int n = time.length;
        for(int i = 0; i < n; i++){
            int remainder = time[i] % 60;
            if(remainder == 0){
                // pairing with other numbers having 0 remainder
                ans += map[0];
            } else {
                // e.g. 20 will be paired with 40
                ans += map[60 - remainder];
            }
            map[remainder]++;
        }
        return ans;
    }
}
