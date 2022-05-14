/*

LeetCode: 91. Decode Ways

Medium

Link: https://leetcode.com/problems/decode-ways/

Topics: String, Dynamic Programming

Acceptance: 22.6

A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

class Solution {
    public int numDecodings(String s) {

	return new2022CleanIterativeSolution(s);
        return iterativeSolution(s);
        // Non traditional
        // return recursiveSolution(s);
    }
    private int new2022CleanIterativeSolution(String s){
  	// leading 0s would lead to no formation of decoding
        if(s == null || s.length() == 0 || s.charAt(0) == '0'){
            return 0;
        }
        int n = s.length();
        char prevChar = s.charAt(0);
        // these 3 variables replace an entire dp array to store the num of ways upto ith character
        int prev = 1; // first digit would form 1 way
        int prevPrev = 1;// if first 2 digit can be used as a letter that would also form 1 way
        int next = 0;
        
        for(int i = 1; i < n; i++){
            char c = s.charAt(i);
            // can form 2 digit letter, add num of ways up until there
            if(prevChar == '1' || (prevChar == '2' && c - '0' <= 6)){
                next += prevPrev;
            }    
            // can form single digit letter, add num of ways up until here
            if(c != '0'){
                next += prev;
            }
            // update dp variables for next iteration
            prevPrev = prev;
            prev = next;
            next = 0;
            prevChar = c;
        }
        return prev;
    }

    // Iterative DP Solution
    // Instead of creating an array with size equal to length of string
    // and containing number of ways possible at each position, only 2 variables are used to 
    // save the previous 2 values as only they are required
    private int iterativeSolution(String s){

        if(s==null || s.length()==0)return 0;
        
        // Value denotes the number of ways decoding can be done
        // dp1: prev value
        // dp2: prev to prev value
        // dp:  curr value
        int dp1,dp2,dp;
        
        int n = s.length();
        // Single number used, double number used to form a letter
        int single,duble;

        // base init
        dp2=1;
        // 0 does not denote a letter
        dp1= s.charAt(0) == '0'?0:1;
        // base case requirement
        dp=dp1;

        for(int i=2;i<=n;i++){
            dp=0;
            
            // single number and double number used to form a letter
            // note: previous indices are used
            single = Integer.valueOf(s.substring(i-1,i));
            duble = Integer.valueOf(s.substring(i-2,i));
            
            // If previous 2 numbers can be both used to form letter, total number of decodings possible
            // via both cases is added
            // If valid, accumulate number of possible decodings for single number
            if(single>=1){
                dp +=dp1;
            }
            // if valid accumulate number of possible decodings for double number
            if(duble>=10 && duble<=26){
                dp += dp2;
            }   

            // Move forward, discarding previous dp values
            dp2=dp1;
            dp1=dp;
        }
        return dp;
    }

    // Named in this way just to denote that this is used by recursive solution
    int[] recursiveDp;
    private int recursiveSolution(String s){

        if(s==null || s.length()==0)return 0;
        recursiveDp = new int[s.length()];
        Arrays.fill(recursiveDp,-1);
        
        // recursively form dp array
        return recur(s,0);
    }
    // Returns the total number of decodings possible of substring starting at index 'pos'
    private int recur(String s,int pos){
        // Out of bounds
        if(pos>=s.length()) return 1;
        
        // Calculated earlier, return value
        if(recursiveDp[pos]>-1)return recursiveDp[pos];


        int totalWaysFromHere = 0;

        // Using single number
        if(pos+1 <=s.length()){
            // Accumulate single number letter decoding count by recurring for remaining string starting from pos+1 index
            String firstWay = s.substring(pos,pos+1);
            if(isValid(firstWay)) totalWaysFromHere += recur(s,pos+1);
        }

        if(pos+2 <= s.length()){

            // Accumulate double number letter decoding count by recurring for remaining string starting from pos+2 index
            String secondWay = s.substring(pos,pos+2);  

            if(isValid(secondWay)) totalWaysFromHere += recur(s,pos+2);

        }

        // Save value in array and return
        recursiveDp[pos] = totalWaysFromHere;
        return recursiveDp[pos];
    }

    private boolean isValid(String s){
        // Out of bounds or 0 value which is not valid
        if(s.length()==0 || s.charAt(0)=='0')return false;

        // should be in alphabet range
        int value = Integer.parseInt(s);
        return value >=1 && value<= 26;
    }
}
