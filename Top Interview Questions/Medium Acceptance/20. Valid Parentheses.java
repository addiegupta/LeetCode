/*

LeetCode: 20. Valid Parentheses

Easy

Link: https://leetcode.com/problems/valid-parentheses/

Topics: Array, Two Pointers

Acceptance: 36.7

Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

An input string is valid if:

Open brackets must be closed by the same type of brackets.
Open brackets must be closed in the correct order.
Note that an empty string is also considered valid.

Example 1:

Input: "()"
Output: true
Example 2:

Input: "()[]{}"
Output: true
Example 3:

Input: "(]"
Output: false
Example 4:

Input: "([)]"
Output: false
Example 5:

Input: "{[]}"
Output: true

*/

class Solution {
    public boolean isValid(String s) {
        if(s==null || s.length()==0)return true;
        
        // Odd length check
        if(s.length()%2!=0)return false;
        
        // Store encountered open brackets
        Stack<Character> stk = new Stack();
        // Store open to close bracket mapping
        Map<Character,Character> map = new HashMap();
        map.put('{','}');
        map.put('[',']');
        map.put('(',')');

        char c;
        for(int i=0;i<s.length();i++){
            c = s.charAt(i);
            // Open bracket,push to stack
            if(map.containsKey(c))stk.push(c);
            // Closed bracket, check if stack is not empty and top is corresponding open bracket, only then pop and proceed 
            else if(stk.isEmpty() ||c!=map.get(stk.peek()) ) return false;
            else stk.pop();
        }
        // At end, open brackets might remain in stk; for valid expression, stack should be empty
        return stk.isEmpty();
    }
}