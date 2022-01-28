/*

LeetCode: 1291. Sequential Digits

Medium

Link: https://leetcode.com/problems/sequential-digits/

Topics: Enumeration

Acceptance: 60

An integer has sequential digits if and only if each digit in the number is one more than the previous digit.

Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

 

Example 1:

Input: low = 100, high = 300
Output: [123,234]
Example 2:

Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
 

Constraints:

10 <= low <= high <= 10^9 
 
*/

class Solution {
    List<Integer> ans;
    private void helper(int curr, int low, int high){
        if(curr > high){
            return;
        }
        if(curr >= low){
            ans.add(curr);
        }
        int lastDigit = curr%10;
        if(lastDigit == 9){
            return;
        }
        helper(curr*10 + lastDigit + 1, low, high);
        
    }
    public List<Integer> sequentialDigits(int low, int high) {
        ans = new ArrayList();
	// start at 1 to 9
        for(int i = 1; i < 9 ;i++){
            helper(i, low, high);
        }
        Collections.sort(ans);
        return ans;
    }
}
