/*

LeetCode: 300. Longest Increasing Subsequence

Medium

Link: https://leetcode.com/problems/longest-increasing-subsequence/

Topics: Array, Dynamic Programming, Binary Search

Acceptance: 40.9

Given an unsorted array of integers, find the length of longest increasing subsequence.

Example:

Input: [10,9,2,5,3,7,101,18]
Output: 4 
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4. 
Note:

There may be more than one LIS combination, it is only necessary for you to return the length.
Your algorithm should run in O(n2) complexity.
Follow up: Could you improve it to O(n log n) time complexity?
 */
 

 class Solution {
    private int dpSolution(int[] nums){

        if(nums==null || nums.length==0)return 0;
        int n = nums.length;
        int ans=1;
        int[] dp = new int[n];
        dp[0]=1;
        for(int i=1;i<n;i++){
            dp[i]=1;
            for(int j=0;j<i;j++){
                // if jth element is less than ith, then LIS length is at least LIS till jth + ith element
                // OR current value of dp[i] if it is larger found via another subarray 
                if(nums[j]<nums[i])dp[i]=Math.max(dp[i],dp[j]+1);
            }
            ans = Math.max(dp[i],ans); 
        }
        return ans;
        
    }
    private int binarySearchSolution(int[] nums){
        int[] dp = new int[nums.length];
        int len=0;
        for(int num : nums){
            // Find index where current element should be inserted
            int i = binarySearch(dp,0,len,num);
            // Set the number at that index
            dp[i]=num;
            // len represents LIS length
            // As array is traversed ltr and dp array is sorted, hence this length LIS is sure to be found
            // dp array does not represent the LIS but it can be used to get LIS length
            if(i==len)len++;
        }
        return len;            
    }
    private int binarySearch(int[] a,int start,int end,int val){
        int mid;
        while(start<end){
            mid = start+(end-start)/2;
            if(a[mid]==val)return mid;
            else if(a[mid]<val)start=mid+1;
            else end = mid;
        }
        return start;
    }
    public int lengthOfLIS(int[] nums) {
        // return dpSolution(nums);// O (n^2) time and O(n) space
        return binarySearchSolution(nums);// O(n logn)time and O(n) space
    }
}