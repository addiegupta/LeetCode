/*

LeetCode: 4. Median of Two Sorted Arrays

Hard

Link: https://leetcode.com/problems/median-of-two-sorted-arrays/

Topics: Array, Binary Search, Divide and Conquer

Acceptance: 26.9

There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

You may assume nums1 and nums2 cannot be both empty.

Example 1:

nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:

nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5

 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        
        // Solve for nums1 array being shorter in length
        int m = nums1.length,n=nums2.length;
        if(m>n)return findMedianSortedArrays(nums2,nums1);
        
        
        // The idea is to create 2 partitions: 1 containing left elements of both arrays and the other containing right elements 
        // such that both partitions have equal number of elements (in odd case, low partition has 1 element extra) 
        // If the partition is not such that the elements satisfy median requirement, then binary search is used to move the partition 
        // left/right in array 1 and opposite direction in array 2 so that count property remains satisfied
        int partA,partB,start=0,end=nums1.length;
        
        // Low variables: Border element in the lower partition
        // Hi variables: Border element in the higher partition
        int lowA,lowB,hiA,hiB;
        
        while(start<=end){
            
            // Create the partition
            partA = start + (end-start)/2;
            partB = (m+n+1)/2 - partA;
            
            // Handle edge out of bounds cases of array by setting to min/max
            lowA = partA==0 ? Integer.MIN_VALUE : nums1[partA-1];
            lowB = partB==0 ? Integer.MIN_VALUE : nums2[partB-1];
            hiA = partA==m ? Integer.MAX_VALUE : nums1[partA];
            hiB = partB==n ? Integer.MAX_VALUE : nums2[partB];
            
            // Border element of left partition in A is less than border element in right partition of B
            // i.e. median will be formed around here
            // Similarly for border element of left of B and right of A
            if(lowA<=hiB && lowB<=hiA){
                // In case of even number of elements, choose 2 elements
                // else the max in the left border will be median
                return (m+n)%2==0 ? ((double)Math.max(lowA,lowB) + (double)Math.min(hiA,hiB))/2 : Math.max(lowA,lowB);
            }
            // Elements from A are too large, move partition towards left in A, right in B
            else if(lowA>hiB){
                end = partA-1;
            }
            // Elements from A are too small, move partition towards right in A, left in B
            else start = partA+1;
        }
        
        // Only reached when arrays are not sorted
        return -1;
    }
}