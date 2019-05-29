/*

LeetCode: 412. Fizz Buzz   

Link: https://leetcode.com/problems/fizz-buzz/

Acceptance: 59.5

Write a program that outputs the string representation of numbers from 1 to n.

But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. For numbers which are multiples of both three and five output “FizzBuzz”.

Example:

n = 15,

Return:
[
    "1",
    "2",
    "Fizz",
    "4",
    "Buzz",
    "Fizz",
    "7",
    "8",
    "Fizz",
    "Buzz",
    "11",
    "Fizz",
    "13",
    "14",
    "FizzBuzz"
]

*/

// C++
class Solution {
public:
    vector<string> fizzBuzz(int n) {
        vector<string> ans(n,"");
        for(int i=1;i<=n;i++){
            if(i%15==0) ans[i-1]="FizzBuzz";
            else if(i%3==0) ans[i-1]="Fizz";
            else if(i%5==0) ans[i-1]="Buzz";
            else ans[i-1]=to_string(i);
        }    
        return ans;
    }
};

// Java
class Solution {
    public List<String> fizzBuzz(int n) {
    List<String> ans = new ArrayList<>(n);    
    for(int i=1;i<=n;i++){
        if(i%15==0) ans.add("FizzBuzz");
        else if(i%3==0) ans.add("Fizz");
        else if(i%5==0) ans.add("Buzz");
        else ans.add(String.valueOf(i));
    }    
    return ans;

    }
}