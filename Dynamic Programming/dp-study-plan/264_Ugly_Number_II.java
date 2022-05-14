/*

LeetCode: 264. Ugly Number II

Medium

Link: https://leetcode.com/problems/ugly-number-ii/

Topics: Hash Table, Math, Dynamic Programming, Heap (Priority Queue)

Acceptance: 45

 An ugly number is a positive integer whose prime factors are limited to 2, 3, and 5.

Given an integer n, return the nth ugly number.


Example 1:

Input: n = 10
Output: 12
Explanation: [1, 2, 3, 4, 5, 6, 8, 9, 10, 12] is the sequence of the first 10 ugly numbers.
Example 2:

Input: n = 1
Output: 1
Explanation: 1 has no prime factors, therefore all of its prime factors are limited to 2, 3, and 5.
 

Constraints:

1 <= n <= 1690
 
 */
class Solution {
    public int nthUglyNumber(int n) {
        if(n < 1){
            return 0;
        }
        //return initialTLE(n);
        //return newInefficientSolution(n);
        return cleanLcSolution(n);
    }
    
    private int cleanLcSolution(int n){
        int[] ugly = new int[n + 1];
        ugly[0] = 1;
        // keep track of index of ugly number array and 3 ugly numbers each multiples of 2,3,5 respectively
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;
        for(int i = 1; i <= n; i++){
            // use min out of the 3 prime multiples as we have to get nth number, need to maintain order
            int min = Math.min(factor2, Math.min(factor3, factor5));
            ugly[i] = min;
            // pick multiples of 2, 3, 5 for next iteration
            // duplicates will be handled as this is not if-else tree, instead 3 separate if statements are used
            // in case of duplicate, multiple factors will be updated here
            if(min == factor2){
                factor2 = ugly[++index2] * 2;
            } 
            if(min == factor3){
                factor3 = ugly[++index3] * 3;
                
            }
            if(min == factor5){
                factor5 = ugly[++index5] * 5;
            }
        }
        return ugly[n - 1];
    }
    
    // keep 3 separate lists for creating multiples of 2, 3 and 5
    private int newInefficientSolution(int n){
        List<Queue<Integer>> lists = new ArrayList();
        Set<Integer> seen = new HashSet();
        int[] primes = {2, 3, 5};
        for(int x = 0; x < primes.length; x++){
            lists.add(new LinkedList());
            lists.get(x).offer(primes[x]);
        }
        int count = n - 1;
        int min = 1, minIndex = 0;
        while(count > 0){
            min = Integer.MAX_VALUE;
            // get the min head of the 3 lists
            for(int x = 0; x < primes.length; x++){
                // in some edge cases, list was empty
                if(lists.get(x).isEmpty())continue;
                int num = lists.get(x).peek();
                if(num < min){
                    min = num;
                    minIndex = x;
                }
            }
            
            lists.get(minIndex).poll();
            // skip duplicates
            if(seen.contains(min)){
                continue;
            }
            seen.add(min);
            
            for(int x = 0; x < primes.length; x++){
                // more than required ugly numbers, skip adding to list
                if(lists.get(x).size() > count) continue;
                int prod = min * primes[x];
                if(!seen.contains(prod)){
                    lists.get(x).add(prod);
                }
            }
            count --;
        }
        return min;
    }
    
    // for every number, check if its ugly; if it is, increment count and
    // repeat till the nth ugly number
    // causes TLE as every number is iterated over, need more efficient
    private int initialTLE(int n){
        Set<Integer> set = new HashSet();
        int[] primes = {2, 3, 5};
        set.add(1);
        int count = 1;
        int ans = 1;
        int i = 2;
        while(count < n){
            for(int j = 0; j < primes.length; j++){
                int prime = primes[j];
                // set only has the numbers which have only the given primes as their factors
                if(i % prime == 0 && set.contains(i / prime)){
                    count ++;
                    ans = i;
                    set.add(i);
                    break;
                }
            }
            i++;
        }
        return ans;
    }
}
