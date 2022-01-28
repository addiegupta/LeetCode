/*

LeetCode: 941. Valid Mountain Array

Easy

Link: https://leetcode.com/problems/valid-mountain-array/

Topics: Array

Acceptance: 32


Given an array of integers arr, return true if and only if it is a valid mountain array.

Recall that arr is a mountain array if and only if:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]

 

Example 1:

Input: arr = [2,1]
Output: false
Example 2:

Input: arr = [3,5,5]
Output: false
Example 3:

Input: arr = [0,3,2,1]
Output: true
 

Constraints:

1 <= arr.length <= 104
0 <= arr[i] <= 104

*/


class Solution {
    private boolean initialUntidySolution(int[] arr){
        int n = arr.length;
        boolean climbing = true;
        int peak = -1;
        for(int i = 0 ; i < n-1 ;i++){
            // has to be strictly increasing or decreasing
            int curr = arr[i];
            int next = arr[i+1];
            if(curr == next || (curr < next && !climbing)){
                return false;
            }
            if(curr > next && climbing){
                climbing = false;
                peak = i;
            }
        }
        if(peak == 0 || peak == n-1 || climbing){
            return false;
        }
        return true;
        
    }
    private boolean cleanLcSolution(int[] arr){
        int n = arr.length;
        int i = 0;
        while(i < n-1 && arr[i] < arr[i + 1]){
            i++;
        }
        // peak cannot be first/last element
        if(i == 0 || i == n - 1){
            return false;
        }
        while(i < n - 1 && arr[i] > arr[i+1]){
            i++;
        }
        return i==n-1;
    }
    public boolean validMountainArray(int[] arr) {
        if(arr == null || arr.length == 0){
            return true;
        }
        //return initialUntidySolution(arr);
        return cleanLcSolution(arr);
    }
}
