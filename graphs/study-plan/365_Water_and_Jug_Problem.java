/*

LeetCode: 365. Water and Jug Problem

Medium

Link: https://leetcode.com/problems/water-and-jug-problem/

Topics: Graph, Depth first Search, Breadth first search, Math

Acceptance: 34.9

 You are given two jugs with capacities jug1Capacity and jug2Capacity liters. There is an infinite amount of water supply available. Determine whether it is possible to measure exactly targetCapacity liters using these two jugs.

If targetCapacity liters of water are measurable, you must have targetCapacity liters of water contained within one or both buckets by the end.

Operations allowed:

Fill any of the jugs with water.
Empty any of the jugs.
Pour water from one jug into another till the other jug is completely full, or the first jug itself is empty.
 

Example 1:

Input: jug1Capacity = 3, jug2Capacity = 5, targetCapacity = 4
Output: true
Explanation: The famous Die Hard example 
Example 2:

Input: jug1Capacity = 2, jug2Capacity = 6, targetCapacity = 5
Output: false
Example 3:

Input: jug1Capacity = 1, jug2Capacity = 2, targetCapacity = 3
Output: true
 

Constraints:

1 <= jug1Capacity, jug2Capacity, targetCapacity <= 106
 
*/

class Solution {
    class JugState {
        int jug1;
        int jug2;
        JugState(int jug1, int jug2){
            this.jug1 = jug1;
            this.jug2 = jug2;
        }
        
        @Override
        public boolean equals(Object obj){
            JugState j = (JugState)obj;
            return this.jug1 == j.jug1 && this.jug2 == j.jug2;
        }
        
        @Override
        public int hashCode(){
            return this.jug1 * 31 + this.jug2;
        }
    }
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        //return initialExplicitSolution(jug1Capacity, jug2Capacity, targetCapacity);
        return newCleanSolution(jug1Capacity, jug2Capacity, targetCapacity);
    }
    // rather than keeping explicit values of each jug, solution can be reached
    // by keeping track of sum of water in both jugs
    // then simply add or remove jug1/jug2 capacity from total at every step
    private boolean newCleanSolution(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Queue<Integer> q = new LinkedList();
        Set<Integer> visited = new HashSet();
        q.offer(0);
        int[] dirs = new int[]{jug1Capacity, -jug1Capacity, jug2Capacity, -jug2Capacity};
        while(!q.isEmpty()){
            int volume = q.poll();
            if(visited.contains(volume))continue;
            visited.add(volume);
            if(volume == targetCapacity){
                return true;
            }
            for(int i = 0 ; i < 4; i++){
                int newVol = volume + dirs[i];
                if(newVol < 0 || newVol > (jug1Capacity + jug2Capacity) || visited.contains(newVol)){
                    continue;
                }
                q.offer(newVol);
            }
        }
        return false;
    }
    private boolean initialExplicitSolution(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        Queue<JugState> q = new LinkedList();
        Set<JugState> set = new HashSet();
        q.offer(new JugState(0, 0));
        while(!q.isEmpty()){
            JugState j = q.poll();
            if(set.contains(j))continue;
            set.add(j);
            
            // found answer
            int sum = j.jug1 + j.jug2;
            if(j.jug1 == targetCapacity || j.jug2 == targetCapacity || sum == targetCapacity){
                return true;
            }
            
            // empty first jug
            if(j.jug1 > 0){
                q.offer(new JugState(0, j.jug2));
            }
            // empty second jug
            if(j.jug2 > 0){
                q.offer(new JugState(j.jug1, 0));
            }
            
            if(j.jug1 < jug1Capacity) {
                // fill first jug
                q.offer(new JugState(jug1Capacity, j.jug2));
                // pour second jug to first jug
                int amountPoured = Math.min(j.jug2, jug1Capacity - j.jug1);
                q.offer(new JugState(j.jug1 + amountPoured, j.jug2 - amountPoured));
            }
            
            if(j.jug2 < jug2Capacity){
                // fill second jug
                q.offer(new JugState(j.jug1, jug2Capacity));
                // pour first jug to second jug
                int amountPoured = Math.min(j.jug1, jug2Capacity - j.jug2);
                q.offer(new JugState(j.jug1 - amountPoured, j.jug2 + amountPoured));
            }
        }
        return false;
        
    }
}
