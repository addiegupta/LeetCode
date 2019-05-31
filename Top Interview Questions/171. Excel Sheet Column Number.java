/*

LeetCode: 171. Excel Sheet Column Number

Easy

Link: https://leetcode.com/problems/excel-sheet-column-number/

Topics: Math

Acceptance: 51.5


Given a column title as appear in an Excel sheet, return its corresponding column number.

For example:

    A -> 1
    B -> 2
    C -> 3
    ...
    Z -> 26
    AA -> 27
    AB -> 28 
    ...
Example 1:

Input: "A"
Output: 1
Example 2:

Input: "AB"
Output: 28
Example 3:

Input: "ZY"
Output: 701

*/

class Solution {
    public int titleToNumber(String s) {
        int ans=0;
        for(int i=0;i<s.length();i++){
            // In base 10 system, number is multiplied by 10 for left shift
            // Similarly this is base 26
            ans = ans * 26 + (s.charAt(i) - 'A' + 1);
        }
        return ans;
    }
}