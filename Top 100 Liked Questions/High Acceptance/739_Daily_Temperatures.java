/*

LeetCode: 739. Daily Temperatures

Medium

Link: https://leetcode.com/problems/daily-temperatures/

Topics: Array, Stack, Monotonic Stack

Acceptance: 67.1

Given an array of integers temperatures represents the daily temperatures, return an array answer such that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature. If there is no future day for which this is possible, keep answer[i] == 0 instead.



Example 1:

Input: temperatures = [73,74,75,71,69,72,76,73]
Output: [1,1,4,2,1,1,0,0]
Example 2:

Input: temperatures = [30,40,50,60]
Output: [1,1,1,0]
Example 3:

Input: temperatures = [30,60,90]
Output: [1,1,0]


Constraints:

1 <= temperatures.length <= 105
30 <= temperatures[i] <= 100
 
 */
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        if(temperatures == null || temperatures.length == 0){
            return temperatures;
        }
        //return initialSolution(temperatures);
        return lcMonotonicStackSolution(temperatures);
        //return lcIngeniousSolution(temperatures);
    }
    // Time O(n)
    // Space O(n)
    private int[] lcMonotonicStackSolution(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        // recommended over using Stack
        Deque<Integer> stack = new ArrayDeque();
        for(int i = 0; i < n; i++){
            int temp = temperatures[i];
            // for all old temp values less than current value, update their ans value
            while(!stack.isEmpty() && temperatures[stack.peek()] < temp){
                int prev = stack.pop();
                ans[prev] = i - prev;
            }
            stack.push(i);
        }
        return ans;
    }
    
    // Time O(n)
    // Space O(1)
    // makes use of the ans array to make future decisions
    private int[] lcIngeniousSolution(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        int hottest = 0;
        for(int i = n - 1; i >= 0; i--){
            int temp = temperatures[i];
            // no hotter temperature available to the right i.e. ans[i] would be 0
            if(temp >= hottest){
                hottest = temp;
                continue;
            }
            // ans[i + days] would tell us how many more days till a hotter temperature
            // if e.g. temp at i + days is 71 and temp at i is 72
            // any temperature higher than 72 would have to be higher than 71 as well necessarily
            // hence we can jump the number of days that are given by ans
            // this step might be repeated a few times but still time is O(n)
            int days = 1;
            while(temperatures[i + days] <= temp){
                days += ans[i + days];
            }
            ans[i] = days;
        }
        return ans;
    }
    // Time O(n) but slower than lc solution
    // Space O(1)
    private int[] initialSolution(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        // store smallest index of every temperature occurence
        // since temperature range is till 100, will use fixed space
        // index: temperature, value: index in temperatures array
        int[] map = new int[101];
        for(int i = n - 1; i >= 0; i--){
            int temp = temperatures[i];
            // look for higher temperatures
            for(int j = temp + 1; j <= 100; j++){
                if(map[j] > 0){
                    int diff = map[j] - i;
                    if(ans[i] == 0 || diff < ans[i]){
                        ans[i] = diff;
                    }
                }
            }
            map[temp] = i;
        }
        return ans;
    }
}

