/*

LeetCode: 28. Implement strStr()

Easy

Link: https://leetcode.com/problems/implement-strstr/submissions/

Topics: String, Two Pointer

Acceptance: 32.5

Implement strStr().

Return the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.

Example 1:

Input: haystack = "hello", needle = "ll"
Output: 2
Example 2:

Input: haystack = "aaaaa", needle = "bba"
Output: -1
Clarification:

What should we return when needle is an empty string? This is a great question to ask during an interview.

For the purpose of this problem, we will return 0 when needle is an empty string. This is consistent to C's strstr() and Java's indexOf().

 */
class Solution {
    public int strStr(String haystack, String needle) {
        // Edge cases
        if(needle== null || needle.length()==0)return 0;
        if(haystack==null || haystack.length()==0)return -1;
        if(haystack.length() < needle.length())return -1;
        
        // 1)--------------------------------------------------------------------------------
        // KMP Method; Compute Longest Prefix which is also a suffix array and then find pattern
        // O(m+n) time; O(n) space
        return KMPPatternSearch(haystack,needle);
        
        // 2) -----------------------------------------------------------------------------------
        // Z Search; Compute Z Array which contains length of longest substring starting from index i which is also prefix of string
        // The pattern and text are concatenated as pattern + '$' + text so that pattern becomes prefix of the string
        // Then Z array is computed, then the lenght of pattern value is searched for in Z array
        // O(m+n) time ; O(m+n) space
        // return ZAlgorithmSearch(haystack,needle);
        
        // 3) ----------------------------------------------------------------------
        // Rabin Karp Algorithm; Works by creating a rolling hash function
        // Create a hash value for the pattern using prime number multiplication with numeric value for characters
        // then compare each substring hash with pattern hash; rolling hash helps in efficient calculation of hash every substring
        // O(mn) time ; O(n)space (O(mn) because in worst case every substring might have same hash value as pattern)
        // Useful in plagiarism check and for finding multiple substrings
        // return RabinKarpAlgorithm(haystack,needle);
    }
    
    private int KMPPatternSearch(String txt,String pat){
        
        // Find the 'Longest Prefix which is also suffix till index i' (LPS) array
        int[] lps = computeKMPLPS(pat);
        
        int m = txt.length(), n = pat.length();
        int i=0,j=0;
        
        // j==n -> pattern found hence break
        while(i<m && j<n){
            // Chars matching, increment both pointers
            if(txt.charAt(i) == pat.charAt(j)){
                i++;
                j++;
            }
            // Char mismatch; need to go back on the pattern iterator using lps array
            else{
                // part of the pattern was matching
                // Shift j to index where prefix till j has a known suffix length for current i
                // and then try again for this i
                if(j!=0){
                    j = lps[j-1];
                }
                // No chars matching, simply increment text iterator
                else{
                    i++;
                }
            }
        }
        // found pattern
        if(j==n)return i-j;
        
        // not found
        return -1;
    }
    // Compute the LPS array for KMP Search Algorithm
    private int[] computeKMPLPS(String pat){
        
        // Start at 1 as no prefix for index 0
        // i is for the index
        // j is for prefix
        int i=1,j=0;
        int n = pat.length();
        int[] lps = new int[n];
        while(i<n){
            // Characters matching 
            if(pat.charAt(i)==pat.charAt(j)){
                // number of characters in matching prefix is j (which is an index) + 1
                lps[i] = j + 1;
                // Increment both iterators
                i++;
                j++;
            }
            // Mismatch, go back in j till prefix matching
            else{
                // midway of prefix matching; 
                // Do not match lps[0...lps[j-1]] again as they have been matched due to LPS property
                // Then try again for this i
                if(j!=0){
                    j = lps[j-1];
                }
                // No matching characters
                else{
                    // Longest prefix size for this index is 0 as no matching chars 
                    lps[i] = 0;
                    i++;
                }
            }
        }
        return lps;
    }
        private int ZAlgorithmSearch(String txt, String pat){
        
        // Concatenate the pattern and text with a character in between which is not present in any of the string
        String total = new StringBuilder().append(pat).append('$').append(txt).toString();
        
        int z[] = computeZArray(total);
        int n = total.length();
        int m = pat.length();
        for(int i=0;i<n;i++){
            // If z value is equal to pattern length, then pattern is present in the text
            if(z[i]==m)return i-m-1;
        }
        // Not found
        return -1;
    }
    private int[] computeZArray(String s){
        int n = s.length();
        int[] z = new int[n];
    
        // Z window markers
        // Z window consists of characters the z value for which has been computed before 
        int left=0, right=0;
        for(int k=1;k<n;k++){
            
            // This position is not a part of the z window, hence value needs to be computed for this
            if(right<k){
                // Start new z window from this position
                left = right = k;
                // Extend window till characters match with the prefix 
                while(right<n && s.charAt(right) == s.charAt(right - left)){
                    right++;
                }
                // Set z value and decrement right as it has crossed window due to while loop last increment
                z[k] = right - left;
                right--;
            }
            // This position is inside z window, value may be obtained from previous values in z array
            else{
                int k1 = k - left;
                // Value does not stretch out of z window bounds, just copy it
                if(z[k1] <= right-k){
                    z[k] = z[k1];
                }
                // Value goes out of bounds of z window and hence matching needs to be done
                else{
                    left = k;
                    while(right<n && s.charAt(right) == s.charAt(right-left)){
                        right++;
                    }
                    z[k] = right -left;
                    right--;
                }
            }
        }
        return z;
    }
    
    private final int prime = 101;
    private int RabinKarpAlgorithm(String txt,String pat){
        int m = pat.length();
        int n = txt.length();
        int patHash = getHash(pat,m);
        int txtHash = getHash(txt,m);
        for(int i=0;i<=n-m;i++){
            if(patHash == txtHash && compareStrings(txt,i,pat)) return i;
            if(i<n-m) txtHash = rollOverHashValue(txtHash,i,m,txt);
        }
        return -1;
    }
    // Create hash value for substring
    private int getHash(String s,int end){
        int hash = 0;
        for(int i=0;i<end;i++){
            hash += s.charAt(i) * Math.pow(prime,i);
        }
        return hash;
    }
    // Roll over hash value by removing earliest character, dividing by prime to bring the power by 1 of each element
    // and finally adding new character hash
    private int rollOverHashValue(int hash,int i,int n,String s){
        hash -= s.charAt(i);
        hash/= prime;
        hash += s.charAt(i+n) * Math.pow(prime,n-1);
        return hash;
    }
    // Check if strings are same
    private boolean compareStrings(String txt, int i,String pat){
        int j=0;
        while(j<pat.length()){
            if(txt.charAt(i)!=pat.charAt(j))return false;
            i++;
            j++;
        }
        return true;
    }
}