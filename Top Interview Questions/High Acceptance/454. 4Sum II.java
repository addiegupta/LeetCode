/*

LeetCode: 454. 4Sum II

Medium

Link: https://leetcode.com/problems/4sum-ii/

Topics: Hash Table, Binary Search

Acceptance: 50.7

Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i] + B[j] + C[k] + D[l] is zero.

To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.

Example:

Input:
A = [ 1, 2]
B = [-2,-1]
C = [-1, 2]
D = [ 0, 2]

Output:
2

Explanation:
The two tuples are:
1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
 
 // Time O(n^2) Space O(n^2)
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        int n = A.length;
        int sum=0;
        int ans=0;
        HashMap<Integer,Integer> map = new HashMap<>();
        
		// Store 2 element sums in map
		for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                sum=A[i]+B[j];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        
		// Compare 2 element sums of remaining 2 arrays with map
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                sum =0-(C[i]+D[j]);
                ans+=map.getOrDefault(sum,0);
            }
        }
        
        return ans;
    }
}