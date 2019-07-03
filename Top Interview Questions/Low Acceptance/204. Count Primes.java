/*

LeetCode: 204. Count Primes

Easy

Link: https://leetcode.com/problems/count-primes/

Topics:Hash Table, Math

Acceptance: 29.2

Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
 
 */
class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        // First set all nums to prime
        for(int i=2;i<n;i++) isPrime[i]=true;
        
        // To find prime numbers upto n, we only need to run the Sieve of Eratosthenes till sqrt n since 
        // nums above that will already have been marked
        for(int i=2;i*i <n;i++){
            if(!isPrime[i])continue;
            // Prime found, nums till i^2 will already have been marked prime by smaller values
            // e.g. for 5, 5*2=10,5*3=15, 5*4=20 will already have been marked by 2,3 and 2 respectively
            for(int j= i*i;j<n;j+=i){
                isPrime[j]=false;
            }
        }
        // Count the total number of primes
        int count =0;
        for(int i=2;i<n;i++){
            if(isPrime[i])count++;
        }
        return count;
    }
}