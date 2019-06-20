/*

LeetCode: 134. Gas Station

Medium

Link: https://leetcode.com/problems/set-matrix-zeroes/

Topics: Greedy

Acceptance: 34.0

There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1.

Note:

If there exists a solution, it is guaranteed to be unique.
Both input arrays are non-empty and have the same length.
Each element in the input arrays is a non-negative integer.
Example 1:

Input: 
gas  = [1,2,3,4,5]
cost = [3,4,5,1,2]

Output: 3

Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
Example 2:

Input: 
gas  = [2,3,4]
cost = [3,4,3]

Output: -1

Explanation:
You can't start at station 0 or 1, as there is not enough gas to travel to the next station.
Let's start at station 2 and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 0. Your tank = 4 - 3 + 2 = 3
Travel to station 1. Your tank = 3 - 3 + 3 = 3
You cannot travel back to station 2, as it requires 4 unit of gas but you only have 3.
Therefore, you can't travel around the circuit once no matter where you start.

 */
class Solution {
    private int firstSolution(int[] gas,int[] cost){
        int n = gas.length;
        int total =0;
        int fails=0;
        int ind=-1;
        int i=0;
        while(fails!=n){
            total += gas[i]-cost[i];
            if(total<0){
                total=0;
                fails++;
                ind=-1;
            }
            else{
                if(i==ind-1 || (ind==0 && i==n-1))return ind;
                if(ind==-1)ind =i;
            }
            i= (i==n-1) ? 0 : i+1;
        }
        return -1;
    }
    // Works on the principle that if total gas is more than total cost
    // then a solution will exist
    // Another important point is that If car starts at A and can not reach B then
    // any station between A and B can not reach B and hence cannot be answer.(B is the first station that A can not reach.)
    private int leetCodeSolution(int[] gas,int[] cost){
        int start =0, total =0, tank=0, n=gas.length;
        
        for(int i=0;i<n;i++){
            tank += gas[i] - cost[i];
            // Solution will not be found till here, init ans index as next index
            if(tank<0){
                // for i==n-1 if start is set to n which is out of range
                // in that case the last return condition total<0 will be true
                // as no index in the array fulfilled gas condition hence -1 will definitely be returned and not n
                start = i+1;
                total += tank;
                tank=0;
            }
        }
        total +=tank;
        return (total<0)?-1:start;
    }
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // return firstSolution(gas,cost);
        return leetCodeSolution(gas,cost);
    }
} 