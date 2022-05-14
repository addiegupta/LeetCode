/*

LeetCode: 378. Kth Smallest Element in a Sorted Matrix

Medium

Link: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/

Topics: Array, Binary Search, Sorting, Heap (Priority Queue), Matrix

Acceptance: 59.3

 Given an n x n matrix where each of the rows and columns is sorted in ascending order, return the kth smallest element in the matrix.

Note that it is the kth smallest element in the sorted order, not the kth distinct element.

You must find a solution with a memory complexity better than O(n2).

 

Example 1:

Input: matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8
Output: 13
Explanation: The elements in the matrix are [1,5,9,10,11,12,13,13,15], and the 8th smallest number is 13
Example 2:

Input: matrix = [[-5]], k = 1
Output: -5
 

Constraints:

n == matrix.length == matrix[i].length
1 <= n <= 300
-109 <= matrix[i][j] <= 109
All the rows and columns of matrix are guaranteed to be sorted in non-decreasing order.
1 <= k <= n2
 

Follow up:

Could you solve the problem with a constant memory (i.e., O(1) memory complexity)?
Could you solve the problem in O(n) time complexity? The solution may be too advanced for an interview but you may find reading this paper fun.
 
 */

class Solution {
    // Time: O(m * n * log(k)) iterate over every element, heap operations would take log k time
    // Space: O(k) at max k elements would be stored in max heap
    private int initialHeapSquaredSolution(int[][] matrix, int k){
        int n = matrix.length;
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                // offer every element to max heap
                // when size of heap exceeds k, pop element so that top of max heap always
                // remains the kth smallest element
                pq.offer(matrix[i][j]);
                if(pq.size() > k){
                    pq.poll();
                }
            }
        }
        return pq.peek();
    }
    // Time O(log(maxEle) * (m + n)): perform binary search on the range A[0][0] to A[m - 1][n - 1] i.e. min and max values of matrix; for every such iteration, count the number of elements smaller than curr
    // Space O(1): no additional memory required
    private int binarySearchLinearSolution(int[][] matrix, int k){
        int m = matrix.length;
        int n = matrix[0].length;
        int l = matrix[0][0];
        int r = matrix[m - 1][n - 1];
        while(l <= r){
            int mid = l + (r - l)/2;
            int count = 0;
            // starting from right most element count the number of elements larger than mid in every row
            // if [i][j] is greater than mid, then [i + 1][j] would also be greater since arrays are sorted
            // hence for every i, j is not reset
            int j = n - 1;
            for(int i = 0; i < m; i++){
                while(j>=0 && matrix[i][j] > mid){
                    j--;
                }
                // in this row, j + 1 elements are greater than mid
                count += j+1;
            }
            // adjust search space based on count, count should be k for getting answer
            if(count < k){
                l = mid + 1;
            } else{
                r = mid - 1;
            }
        }
        return l;
    }
    public int kthSmallest(int[][] matrix, int k) {
        //return initialHeapSquaredSolution(matrix, k);
        return binarySearchLinearSolution(matrix, k);
    }
}
