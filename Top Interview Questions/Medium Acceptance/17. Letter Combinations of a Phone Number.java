/*

LeetCode: 17. Letter Combinations of a Phone Number

Medium

Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/

Topics: String, Backtracking

Acceptance: 41.7

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
 */
class Solution {
  
  // A HashMap can also be used to convert the numbers to required letters
  private void recur(String digits,int i,StringBuilder curr,List<String> ans){

	// Reached end of string, add to ans
    if(i==digits.length()){
      ans.add(new String(curr));
      return;
    }
	
	// Numeric value obtained from char of string
    int num = digits.charAt(i)-'0';
	// Integer value of the first letter corresponding to the number num on keypad
    int c = (((num-2)*3)+'a');
	
	// 7 has 4 letters pqrs; hence 8 and 9 start is shifted
    if(num>7)c +=1;
    for(int j=0;j<3;j++){
		
		// Append a letter and backtrack
      curr.append((char)(c+j));
      recur(digits,i+1,curr,ans);
      curr.setLength(curr.length()-1);
    }
	// 7 and 9 have 4 letter each
    if(num==7||num==9){
      curr.append((char)(c+3));
      recur(digits,i+1,curr,ans);
      curr.setLength(curr.length()-1);
    }
  }
  public List<String> letterCombinations(String digits) {
    
    List<String> ans = new ArrayList();
    if(digits==null || digits.length()==0)return ans;
      recur(digits,0,new StringBuilder(),ans);
    return ans;
  }
}