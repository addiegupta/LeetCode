/*

LeetCode: 1569. Number of Ways to Reorder Array to Get Same BST

Hard

Link: https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/

Topics: Array, Math, Divide and Conquer, Dynamic Programming, Tree, Union Find, Binary Search Tree, Memoization, Combinatorics, Binary Tree

References: https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/discuss/819725/Java-Clean-code-uses-Yang-Hui'sPascal's-Triangle-With-Explanation

Acceptance: 50

 Given an array nums that represents a permutation of integers from 1 to n. We are going to construct a binary search tree (BST) by inserting the elements of nums in order into an initially empty BST. Find the number of different ways to reorder nums so that the constructed BST is identical to that formed from the original array nums.

For example, given nums = [2,1,3], we will have 2 as the root, 1 as a left child, and 3 as a right child. The array [2,3,1] also yields the same BST but [3,2,1] yields a different BST.
Return the number of ways to reorder nums such that the BST formed is identical to the original BST formed from nums.

Since the answer may be very large, return it modulo 109 + 7.

 

Example 1:


Input: nums = [2,1,3]
Output: 1
Explanation: We can reorder nums to be [2,3,1] which will yield the same BST. There are no other ways to reorder nums which will yield the same BST.
Example 2:


Input: nums = [3,4,5,1,2]
Output: 5
Explanation: The following 5 arrays will yield the same BST: 
[3,1,2,4,5]
[3,1,4,2,5]
[3,1,4,5,2]
[3,4,1,2,5]
[3,4,1,5,2]
Example 3:


Input: nums = [1,2,3]
Output: 0
Explanation: There are no other orderings of nums that will yield the same BST.
 

Constraints:

1 <= nums.length <= 1000
1 <= nums[i] <= nums.length
All integers in nums are distinct.
*/


class Solution {
    // 2 methods to calculate nCr: pascal triangle & standard nCr formula(using factorial)
    // why is nCr required??
    // at every node, the left subtree and right subtree nodes can be picked in any order
    // given that every parent comes before the child in the array order
    // so it does not matter if left comes first or right comes first as long as 
    // order in its subtree is maintained
    // e.g. left subtree is [1,2,3], right subtree is [4,5,6]
    // all possible combinations can be made as long as 1 comes before 2 and 2 before 3 etc.
    // e.g. [4,5,6,1,2,3], [1,4,2,5,6,3]
    // this count of combinations at every step is nCr where n is total of both subtrees, r is size of 1 subtree
    boolean usePascalTriangleMethod = false;
    
    // factorial values are stored, for factorial method (causes TLE, but overall is more straightforward to come up with(pascal triangle might not strike so soon))
    long[] facDp;
    
    // nCr combination values are stored in this, for pascal triangle
    long[][] combs;
    
    // need to mod as large values come up due to factorial
    long mod = (long)Math.pow(10, 9) + 7;
    
    private long factorial (int n){
        if(facDp[n] != 0){
          return facDp[n];  
        } 
        if(n <= 1){
            facDp[n] = n;
            return 1;
        }
        long val = (n * factorial(n-1)) % mod;
        facDp[n] = val;
        return val;
    }
    
    // 	      1
    //      1 2  1
    //    1 3   3  1
    //  1 4  6 4  6  1 
    //
    // pascal triangle has many special properties
    // 1 of them being pascal[i][j] represents iCj where C stands for combinations
    private void initPascalTriangle(int n){
        combs = new long[n][n];
        for(int i= 0; i < n;i++){
            combs[i][0] = 1;
            combs[i][i] = 1;
        }
        for(int i = 2; i< n; i++){
            for(int j = 1; j < i ;j++){
                combs[i][j] = (combs[i-1][j] + combs[i-1][j-1]) % mod;
            }
        }
    }
    
    private long helper(List<Integer> list){
        // base case, 1 combination possible
        if(list.size() <= 1){
            return 1;
        }
        List<Integer> leftSubtree = new LinkedList();
        List<Integer> rightSubtree = new LinkedList();
        int root = list.get(0);
        // smaller nodes form left subtree, greater form right subtree
        // as unique values in tree, equal value is ignored
        for(int num: list){
           if(num < root) {
               leftSubtree.add(num);
           } else if(num > root){
               rightSubtree.add(num);
           }
        }
        int size = list.size();
        int leftSize = leftSubtree.size();
        int rightSize = rightSubtree.size();
        long ways;
        if(usePascalTriangleMethod){
            // nCr
            ways = combs[size - 1][leftSize] % mod;
        } else{
            // nCr
            ways = (factorial(size - 1) / factorial(leftSize)) % mod;
            ways /= factorial(rightSize);
            ways = ways % mod;
        }
        // recursion
        return (((ways * helper(leftSubtree)) % mod) * helper(rightSubtree)) % mod;
    }
    public int numOfWays(int[] nums) {
        if(nums == null || nums.length <= 2){
            return 0;
        }
        if(usePascalTriangleMethod){
            initPascalTriangle(nums.length + 1);
        } else{
            facDp = new long[1001];
        }
        
        List<Integer> list = new LinkedList();
        for(int num: nums){
            list.add(num);
        }
        long ways = helper(list) - 1;
        return (int)(ways % mod);
    }
}
