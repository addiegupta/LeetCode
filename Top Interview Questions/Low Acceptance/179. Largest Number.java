/*

LeetCode: 179. Largest Number

Medium

Link: https://leetcode.com/problems/largest-number/

Topics: Sort

Acceptance: 26.0

Given a list of non negative integers, arrange them such that they form the largest number.

Example 1:

Input: [10,2]
Output: "210"
Example 2:

Input: [3,30,34,5,9]
Output: "9534330"
Note: The result may be very large, so you need to return a string instead of an integer.

 */
 class Solution {
    class DigitComparator implements Comparator<String>{
        
		// For the 2 values, check which order is higher value
		@Override
        public int compare(String a,String b){
            String order1 = a+b;
            String order2 = b+a;
            return order2.compareTo(order1);
        }
    }
    public String largestNumber(int[] nums) {
        if(nums==null || nums.length==0)return "";
       
		// Convert ints to Strings and sort using comparator
        List<String> list = new ArrayList();
        for(int i: nums) list.add(String.valueOf(i));
        Collections.sort(list,new DigitComparator());
		
		// Edge case, all are 0 values
        if(list.get(0).charAt(0)=='0')return "0";
        
        StringBuilder ans = new StringBuilder();
        for(String s: list)ans.append(s);
        return ans.toString();
    }
}