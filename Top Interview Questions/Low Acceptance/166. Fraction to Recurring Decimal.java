/*

LeetCode: 166. Fraction to Recurring Decimal

Medium

Link: https://leetcode.com/problems/fraction-to-recurring-decimal/

Topics: Hash Table, Math

Acceptance: 19.7

Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

If the fractional part is repeating, enclose the repeating part in parentheses.

Example 1:

Input: numerator = 1, denominator = 2
Output: "0.5"
Example 2:

Input: numerator = 2, denominator = 1
Output: "2"
Example 3:

Input: numerator = 2, denominator = 3
Output: "0.(6)"

 */
class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        
        StringBuilder ans = new StringBuilder();
        
        if(numerator==0)return "0";
        
        // One number is negative
        if((numerator>0) ^ (denominator>0)){
            ans.append("-");
        }
        // Convert to long to avoid overflow
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        
        // Floor value
        ans.append(num/den);
        num%=den;
        // No decimal part, return
        if(num==0)return ans.toString();
        
        // fractional part
        ans.append('.');
        
        // When a numerator is seen, add it to hashmap with its position in string as value
        // When it is seen again, it means repeating part has been found
        Map<Long,Integer> map = new HashMap();
        map.put(num,ans.length());
        
        while(num!=0){
            
            // Get next digit for decimal
            num *= 10;
            ans.append(num/den);
            num %= den;
            // Repeating part found
            if(map.containsKey(num)){
                int index = map.get(num);
                ans.insert(index,'(');
                ans.append(')');
                break;
            }
            else{   
                map.put(num,ans.length());
            }
        }
        return ans.toString();
    }
}