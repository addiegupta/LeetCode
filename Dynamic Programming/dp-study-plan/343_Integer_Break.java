/*

LeetCode: 343. Integer Break

Medium

Link: https://leetcode.com/problems/integer-break/

Topics: Dynamic Programming, Math

Acceptance: 53.6

Given an integer n, break it into the sum of k positive integers, where k >= 2, and maximize the product of those integers.

Return the maximum product you can get.

 

Example 1:

Input: n = 2
Output: 1
Explanation: 2 = 1 + 1, 1 × 1 = 1.
Example 2:

Input: n = 10
Output: 36
Explanation: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36.
 

Constraints:

2 <= n <= 58
 
 */

class Solution {
    public int integerBreak(int n) {
        if(n <= 0){
            return 0;
        }
        //return initialSquaredSolution(n);
        return lcMathLinearSolution(n);
    }
    private int initialSquaredSolution(int n){
        int[] dp = new int[n + 1];
	// 1 cannot be split further to form factors
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
	    // for every i, compare with j (1 to i) if i - j can be used as a break number
	    // if i - j is to be used, dp[j] would denote the max product of the splits of j
            for(int j = 1; j < i; j++){
		// using max of j and dp[j] as for same cases like 2, j is 2 while dp[j] is 1
		// so in larger numbers if 2 is used as a number, it would contribute 2 to product
                int prod = Math.max(j, dp[j]) * (i - j);
                dp[i] = Math.max(dp[i], prod);
            } 
        }
        return dp[n];
    }
    private int lcMathLinearSolution(int n){
	// references: https://leetcode.com/problems/integer-break/discuss/80689/A-simple-explanation-of-the-math-part-and-a-O(n)-solution
	// basic idea is most factorisation can be done in terms of 2 and 3
	// count the numbers of 3 (use max count of 3s) and use them in product and count remaining sum after all 3 are exhausted
        if(n <= 2){
            return 1;
        }
        
        if(n == 3){
            return 2;
        }
        
        int product = 1;
        int num = n;
        
        while(num > 4){
            product *= 3;
            num -= 3;
        }
        
        product *= num;
        return product;
    }
}
