/*

LeetCode: 239. Sliding Window Maximum

Hard

Link: https://leetcode.com/problems/sliding-window-maximum/

Topics: Sliding Window, Heap

Acceptance: 38.7

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return the max sliding window.

Example:

Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7] 
Explanation: 

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
Note: 
You may assume k is always valid, 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
Could you solve it in linear time?
 */

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        // Edge case
        if(nums==null || nums.length ==0)return nums;

        // Use a deque to store the elements that might become a max in the present/future
        Deque<Integer> dq = new LinkedList();

        int n = nums.length;
        int[] ans = new int[n-k+1];
        int x = 0;
        for(int i=0;i<n;i++){
            // Beyond first window, remove the element from deque that is being removed from sliding window
            if(i>=k && dq.peekFirst()==nums[i-k])
                dq.pollFirst();
        
            // Remove elements from rear that are smaller than current value
            // as they are useless
            while(!dq.isEmpty() && dq.peekLast()<nums[i])dq.pollLast();
            
            // Add current value at end of deque
            // Front to Rear deque contains elements in descending order
            dq.addLast(nums[i]);
            
            // Add max value of this window to ans array
            if(i>=k-1) ans[x++] = dq.peekFirst();
        }
        return ans;
    }
}