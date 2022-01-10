/*

LeetCode: 1094. Car Pooling

Medium

Link: https://leetcode.com/problems/car-pooling/

Topics: Array, Sorting, Heap, Simulation, Prefix Sum

Acceptance: 59.2

There is a car with capacity empty seats. The vehicle only drives east (i.e., it cannot turn around and drive west).

You are given the integer capacity and an array trips where trip[i] = [numPassengersi, fromi, toi] indicates that the ith trip has numPassengersi passengers and the locations to pick them up and drop them off are fromi and toi respectively. The locations are given as the number of kilometers due east from the car's initial location.

Return true if it is possible to pick up and drop off all passengers for all the given trips, or false otherwise.
 

Example 1:

Input: trips = [[2,1,5],[3,3,7]], capacity = 4
Output: false
Example 2:

Input: trips = [[2,1,5],[3,3,7]], capacity = 5
Output: true
 

Constraints:

1 <= trips.length <= 1000
trips[i].length == 3
1 <= numPassengersi <= 100
0 <= fromi < toi <= 1000
1 <= capacity <= 105
 
*/

class Solution {
    // Time O(n logn): array is sorted in order of trip start
    // Space O(m): m is num of trips, all trips are stored in heap
    private boolean heapSolution(int[][] trips, int capacity){
        // sort array in order of start location
        Arrays.sort(trips, (a, b) -> a[1] - b[1]);
        // keep heap in order of min end location to be able to remove ended trips
        PriorityQueue<int[]> heap = new PriorityQueue<int[]>((a, b) -> a[2] - b[2]);
        
        int passengers = 0;
        for(int i = 0; i < trips.length; i++){
            int[] trip = trips[i];
            // remove ended trips from heap
            while(heap.size() > 0 && heap.peek()[2] <= trip[1]){
                int[] endedTrip = heap.poll();
                passengers -= endedTrip[0];
            }
            // use current trip
            passengers += trip[0];
            
            // capacity exceeded, cannot complete trips
            if(passengers > capacity){
                return false;
            }
            
            // add current trip to heap
            heap.offer(trip);
        }
        return true;
    }
    // Time: O(n) n is number of trips
    // Space: O(m) m is number of stops
    private boolean mapSolution(int[][] trips, int capacity){
	    // as per constraint max num of trips is 1001
        int[] map = new int[1001];
        
        for(int i = 0; i < trips.length; i++){
	        // treat index in map as location, at location increment the num of passengers that entered
	        // and decrement those who got off
            int[] trip = trips[i];
            int num = trip[0];
            map[trip[1]] += num;
            map[trip[2]] -= num;
        }
	    // iterate over the locations from start to end, if count of passengers is ever greater than capacity
	    // then cannot complete trip
        int count = 0;
        for(int i = 0; i < 1001; i++){
            count += map[i];
            if(count > capacity){
                return false;
            }
        }
        return true;
    }
    public boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || trips.length == 0){
            return true;
        }
        
        //return heapSolution(trips, capacity);
        return mapSolution(trips, capacity);
    }
}
