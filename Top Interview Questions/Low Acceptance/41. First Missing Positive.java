/*

LeetCode: 41. First Missing Positive

Hard

Link: https://leetcode.com/problems/first-missing-positive/

Topics: Array

Acceptance: 29.3

Given an unsorted integer array, find the smallest missing positive integer.

Example 1:

Input: [1,2,0]
Output: 3
Example 2:

Input: [3,4,-1,1]
Output: 2
Example 3:

Input: [7,8,9,11,12]
Output: 1
Note:

Your algorithm should run in O(n) time and uses constant extra space.

*/
class Solution {
    public int firstMissingPositive(int[] nums) {
        
        // O(n) time O(n) space
        // return firstSolution(nums);
        
        // O(n) time O(1) space
        return improvedSpaceSolution(nums);
        
    }
    
    private int firstSolution(int[] nums){
    
        int n = nums.length;
        
        // Count of positives
        int positives = n;
        for(int i=0;i<n;i++){
            if(nums[i]<=0)positives--;
        }
        
        // Create an array with count of elements equal to count of positives
        boolean[] seen = new boolean[positives];
        for(int i=0;i<n;i++){
            // Setting value to 1 => element for this index is not missing
            if(nums[i]>0 && nums[i]<=positives){
                seen[nums[i]-1]=true;
            }
        }
        // Find the first missing number
        for(int i=0;i<positives;i++){
            if(!seen[i])return i+1;
        }
        
        // All elements present, first missing positive number is greater than size of array
        return positives+1;
    }
    
    // Swap the elements within the array to save on space
    private int improvedSpaceSolution(int[] A){
        int n = A.length;
        int i=0;
        while(i<n){
            // Positive element within range of n and is not at its designated index e.g. value 2 is not at index 1
            if(A[i]>0 && A[i] <= n && A[A[i]-1]!= A[i]){
                swap(A,i,A[i]-1);
            }
            else i++;
        }
        i=0;
        // Find first element not at its correct position
        while(i<n && A[i]==i+1)i++;
        return i+1;
    }
    private void swap(int[] A,int i,int j){
        
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}