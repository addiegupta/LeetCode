/*

LeetCode: 227. Basic Calculator II

Medium

Link: https://leetcode.com/problems/basic-calculator-ii/

Topics: String

Acceptance: 34.1

Implement a basic calculator to evaluate a simple expression string.

The expression string contains only non-negative integers, +, -, *, / operators and empty spaces . The integer division should truncate toward zero.

Example 1:

Input: "3+2*2"
Output: 7
Example 2:

Input: " 3/2 "
Output: 1
Example 3:

Input: " 3+5 / 2 "
Output: 5
Note:

You may assume that the given expression is always valid.
Do not use the eval built-in library function.
 */


class Solution {
    public int calculate(String s) {
        int len;
        if(s==null || (len = s.length())==0)return 0;
        
        Stack<Integer> stk = new Stack<Integer>();
        int num=0;
        char sign = '+';
        char c;
        for(int i=0;i<len;i++){
            c=s.charAt(i);
            if(Character.isDigit(c)){
                num = num*10 + c-'0';
            }
            if(!Character.isDigit(c) && c!=' ' || i == len-1){
                switch(sign){
                    case '+':   stk.push(num);
                                break;
                    case '-':   stk.push(-num);
                                break;
                    case '/':   stk.push(stk.pop()/num);
                                break;
                    case '*':   stk.push(stk.pop()*num);
                                break;
                }
                sign = c;
                num=0;
            }
        }
        for(int i:stk) num += i;
        return num;  
    }
}