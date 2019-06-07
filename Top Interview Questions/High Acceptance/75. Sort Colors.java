/*

LeetCode: 75. Sort Colors

Medium

Link: https://leetcode.com/problems/sort-colors/

Topics: Array,Two Pointers, Sort

Acceptance: 42.2

Given an array with n objects colored red, white or blue, sort them in-place so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

Note: You are not suppose to use the library's sort function for this problem.

Example:

Input: [2,0,2,1,1,0]
Output: [0,0,1,1,2,2]
Follow up:

A rather straight forward solution is a two-pass algorithm using counting sort.
First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
Could you come up with a one-pass algorithm using only constant space?

 */
 
class Solution {
    public void sortColors(int[] A) {
        int n = A.length;    
        int second=n-1, zero=0;
        for (int i=0; i<=second; i++) {
 
			// Sweep all 2s to right and 0s to left
			while (A[i]==2 && i<second) swap(i,second--,A);
            while (A[i]==0 && i>zero) swap(i,zero++,A);
        }
    }
    private void swap(int i,int j,int[] A){
        int temp = A[i];
        A[i]=A[j];
        A[j]=temp;
    }
}