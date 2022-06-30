/*

LeetCode: 845. Longest Mountain in Array

Medium

Link: https://leetcode.com/problems/longest-mountain-in-array/

Topics: Arrays, Two Pointer, Dynamic Programming, Enumeration

Acceptance: 39.7

You may recall that an array arr is a mountain array if and only if:

arr.length >= 3
There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given an integer array arr, return the length of the longest subarray, which is a mountain. Return 0 if there is no mountain subarray.

 

Example 1:

Input: arr = [2,1,4,7,3,2,5]
Output: 5
Explanation: The largest mountain is [1,4,7,3,2] which has length 5.
Example 2:

Input: arr = [2,2,2]
Output: 0
Explanation: There is no mountain.
 

Constraints:

1 <= arr.length <= 104
0 <= arr[i] <= 104
 

Follow up:

Can you solve it using only one pass?
Can you solve it in O(1) space?

*/
class Solution {
    public int longestMountain(int[] arr) {
        if(arr == null || arr.length < 3){
            return 0;
        }
        // return initialUntidySolution(arr);
        return newCleanSolution(arr);
    }
    private int newCleanSolution(int[] arr) {
        int n = arr.length;
        int ans = 0;
        for(int i = 1 ; i < n; i++){
            int count = 1;
            boolean fullMountain = false;
            int j = i; 
            
            // increasing subarray
            while(j < n && arr[j] > arr[j - 1]){
                count++;
                j++;
            }
            
            // decreasing subarray
            while(i != j && j < n && arr[j] < arr[j - 1]){
                fullMountain = true;
                count++;
                j++;
            }
            
            if(fullMountain){
                ans = Math.max(ans, count);
                j--;
            }
            i = j;
        }
        return ans;
    }
    private int initialUntidySolution(int[] arr) {
        int n = arr.length;
        int start = 0;
        int ans = 0;
        boolean goUp = true;
        
        for(int i = 1; i < n; i++){
            int prev = arr[i - 1];
            int curr = arr[i];
            // coming down
            if(!goUp){
                // should end prev mountain
                if(prev <= curr){
                    // complete a mountain
                    int range = i - start; 
                    ans = Math.max(ans, range);
                    // start new mountain from prev index
                    start = i - 1;
                    goUp = true;
                }
                
            } else { // going up
                if(prev > curr){
                    // invalid, havent found mid yet, reset
                    if(start == i - 1){
                        start = i;
                    } else {
                        // found mid
                        goUp = false;
                    }
                }
            }
            // invalid, reset mountain
            if(prev == curr){
                start = i;
                goUp = true;
            }
        }
        if(!goUp){
            int range = n - start; 
            ans = Math.max(ans, range);
        }
        return ans;
    }
}
