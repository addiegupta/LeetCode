/*

LeetCode: 215. Kth Largest Element in an Array

Medium

Link: https://leetcode.com/problems/kth-largest-element-in-an-array/

Topics: Divide and Conquer, Heap

Acceptance: 48.3

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

 */
class Solution {
    
    public int findKthLargest(int[] nums, int k) {
        if(num==null || nums.length==0)return 0;
        return sortSolution(nums,k);
        return minHeapSolution(nums,k);
        return quickSelectSolution(nums,k);
    }
    // O(n log n) runtime, O(1) memory
    private int sortSolution(int[] nums,int k){
        Arrays.sort(nums);
        return nums[nums.length-k];
    }
    // O(n log k) runtime, O(k) memory
    private int minHeapSolution(int[] nums,int k){
        PriorityQueue<Integer> pq = new PriorityQueue();
        int n = nums.length;
        for(int i=0;i<n;i++){
            pq.add(nums[i]);
            if(pq.size()>k)pq.poll();
        }
        return pq.peek();
    }
    
    // O(n)average O(n^2) worst  runtime, O(1) memory
    // Quick select is quicksort algorithm without sorting complete array but just finding correct positions of pivots
    // to find answer ( basically instead of sorting left and right halves, just scrap one half which is not needed)
    private int quickSelectSolution(int[] nums,int k){

        int n = nums.length;
        // index from start
        k = n-k;
        int l=0,h=n-1;
        int pivot;
        while(l<h){
            // get pivot
            pivot = partition(nums,l,h);
            // answer lies to right
            if(pivot<k) l = pivot+1;
            // answer lies to left
            else if(pivot>k) h = pivot-1;
            // answer found
            else return nums[pivot];
        }
        return nums[l];
    }
    private int partition(int[] nums,int l,int h) {
        // Place elements smaller than pivot to left and then place pivot
        int pivot = h;
        for(int i=l;i<h;i++){
            if(nums[i]<=nums[pivot]){
                swap(nums,l,i);
                l++;
            }
        }
        swap(nums,l,h);
        return l;
    }

    private void swap(int[] a,int i,int j){
        int temp = a[j];
        a[j]=a[i];
        a[i]=temp;
    }

}