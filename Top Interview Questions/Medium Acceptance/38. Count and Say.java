/*

LeetCode: 38. Count and Say

Easy

Link: https://leetcode.com/problems/count-and-say/

Topics: String

Acceptance: 40.6

The count-and-say sequence is the sequence of integers with the first five terms as following:

1.     1
2.     11
3.     21
4.     1211
5.     111221
1 is read off as "one 1" or 11.
11 is read off as "two 1s" or 21.
21 is read off as "one 2, then one 1" or 1211.

Given an integer n where 1 ≤ n ≤ 30, generate the nth term of the count-and-say sequence.

Note: Each term of the sequence of integers will be represented as a string.

 

Example 1:

Input: 1
Output: "1"
Example 2:

Input: 4
Output: "1211"

 */
 class Solution {
   public String countAndSay(int n) {
        
		// Starting string is 1
		String prev="1";
        StringBuilder next = new StringBuilder();
        int count=0,len;
        char num;
		
        for(int i=1;i<n;i++){
            next.setLength(0);
            len = prev.length();
            for(int j=0;j<len;j++){
                num = prev.charAt(j);
                count=1;
                while(j<len-1 && prev.charAt(j+1)==num){
                    count++;
                    j++;
                }
                next.append(String.valueOf(count)).append(num);
            }
            prev =next.toString();
        }
        return prev;
    }
}