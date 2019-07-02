/*

LeetCode: 189. Rotate Array

Easy

Link: https://leetcode.com/problems/rotate-array/

Topics: Array

Acceptance: 30.5

Given an array, rotate the array to the right by k steps, where k is non-negative.

Example 1:

Input: [1,2,3,4,5,6,7] and k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: [-1,-100,3,99] and k = 2
Output: [3,99,-1,-100]
Explanation: 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
Note:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?

 */
 class Solution {
	 public void rotate(int[] nums, int k) {
        if(nums==null || nums.length ==0)return ;
        // cyclicReplaceMethod(nums,k);
        reverseMethod(nums,k);
    }
	// Place an element in its right position, save the original element in new position as temp and continue cycle
    private void cyclicReplaceMethod(int[] nums,int k){
        int n = nums.length;
        k%=n;
        int count =0;
        int curr,prev,temp,next;
        for(int i=0;count<n;i++){
            curr = i;
            prev = nums[i];
            do{
                next = (curr+k)%n;
                temp = nums[next];
                nums[next] = prev;
                prev = temp;
                curr = next;
                count++;
            }while(i!=curr);
        }
    }
	// Reverse entire array, then reverse first k elements and finally reverse the remaining elements at end
    private void reverseMethod(int[] nums, int k){
        reverse(nums,0,nums.length-1);
        reverse(nums,0,k-1);
        reverse(nums,k,nums.length-1);
    }
    private void reverse(int[] nums,int l,int h){
        int temp;
        while(l<h){
            temp = nums[h];
            nums[h]=nums[l];
            nums[l] = temp;
            l++;
            h--;
        }
    }
    
}