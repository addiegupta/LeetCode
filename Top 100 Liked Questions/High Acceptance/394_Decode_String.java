/*

LeetCode: 394. Decode String

Medium

Link: https://leetcode.com/problems/decode-string/

Topics: String, Stack, Recursion

References: https://www.youtube.com/watch?v=qB0zZpBJlh8&ab_channel=NeetCode

Acceptance: 56.3

Given an encoded string, return its decoded string.

The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

You may assume that the input string is always valid; there are no extra white spaces, square brackets are well-formed, etc.

Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there will not be input like 3a or 2[4].

 

Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
 

Constraints:

1 <= s.length <= 30
s consists of lowercase English letters, digits, and square brackets '[]'.
s is guaranteed to be a valid input.
All the integers in s are in the range [1, 300].

*/

class Solution {
    int i = 0;
    
    private String decodeUtil(String s, int count){
        StringBuilder ans = new StringBuilder();
        int n = s.length();
        while(i < n){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                int num = 0;
                while(Character.isDigit(s.charAt(i))){
                    num = num * 10 + (s.charAt(i) - '0');
                    i++; 
                }
                
                // increment to skip expected '['
                i += 1;
                String a = decodeUtil(s, num);
                ans.append(a);
            } else if(c == ']'){
                String temp = ans.toString();
                for(int j = 0; j < count - 1; j++){
                    ans.append(temp);
                }
                return ans.toString();
            }
            else{
                ans.append(c);
            }
            i++;
        }
        return ans.toString();
    }
    public String decodeString(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        return decodeUtil(s, 1);
	//return lcStackSolution(s);
    }
    private String lcStackSolution(String s){
        String res = "";
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            }
            else if (s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder (resStack.pop());
                int repeatTimes = countStack.pop();
                for (int i = 0; i < repeatTimes; i++) {
                    temp.append(res);
                }
                res = temp.toString();
                idx++;
            }
            else {
                res += s.charAt(idx++);
            }
        }
        return res;
    }
}
