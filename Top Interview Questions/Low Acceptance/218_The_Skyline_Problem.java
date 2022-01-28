/*

LeetCode: 218. The Skyline Problem

Hard

Link: https://leetcode.com/problems/the-skyline-problem/

Topics: Array, Divide and Conquer, Binary Indexed Tree, Segment Tree, Line Sweep, Heap(Priority Queue), Ordered Set

Acceptance: 38.1

A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.

The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:

lefti is the x coordinate of the left edge of the ith building.
righti is the x coordinate of the right edge of the ith building.
heighti is the height of the ith building.
You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...]. Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list, which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends. Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.

Note: There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]

 

Example 1:


Input: buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
Output: [[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
Explanation:
Figure A shows the buildings of the input.
Figure B shows the skyline formed by those buildings. The red points in figure B represent the key points in the output list.
Example 2:

Input: buildings = [[0,2,3],[2,5,3]]
Output: [[0,3],[5,0]]
 

Constraints:

1 <= buildings.length <= 104
0 <= lefti < righti <= 231 - 1
1 <= heighti <= 231 - 1
buildings is sorted by lefti in non-decreasing order.
 
 */

class Solution {
    
    // Was causing TLE but passed 39/40 test cases
    // idea is to put every building critical point(start and height)
    // in TreeMap(sorted iterable map) as x -> height key value pairs
    private List<List<Integer>> originalTLESolution(int[][] buildings){
        List<List<Integer>> ans = new ArrayList();
        int n = buildings.length;
        TreeMap<Integer, Integer> map = new TreeMap();
        
        // add values to map
        for(int i = 0; i < n; i++){
            int[] building = buildings[i];
            int start = building[0];
            int end = building[1];
            int height = building[2];
            map.put(start, height);
            // end might already have been placed in map as a start value for some other point
            if(!map.containsKey(end)){
                map.put(end, 0);
            }
        }
        
        for(int i = 0; i < n; i++){
            int[] building = buildings[i];
            int start = building[0];
            int end = building[1];
            int height = building[2];
             
            // tried an optimisation to beat TLE, did pass 1 more case but still not accepted
            // if range is too large dont iterate over entire array, instead iterate on TreeMap
            boolean isRangeLarge =( end - start ) > map.size();
            if(isRangeLarge){
                for(Map.Entry<Integer, Integer> entry : map.entrySet()){
                    int index = entry.getKey();
                    // irrelevant points for this building
                    if(index < start) continue;
                    if( index == end) break;
                    int currHeight = entry.getValue();
                    // for every critical point, set the max height at that point
                    map.put(index, Math.max(currHeight, height));
                }
            } else{
                for(int j = start; j < end; j++){
                    if(!map.containsKey(j)){
                        continue;
                    }
                    int currHeight = map.get(j);
                    map.put(j, Math.max(currHeight, height));
                }
            }
        }
        
        int prevHeight = -1;
        // in map now, at every critical point, max possible height has been set
        // iterate over map and add to ans array, if same height is not repeated
        for(Map.Entry<Integer, Integer> entry : map.entrySet()){
            int index = entry.getKey();
            int height = entry.getValue();
            if (height == prevHeight) {
                continue;
            }
            List<Integer> keyPoint = new ArrayList();
            keyPoint.add(index);
            keyPoint.add(height);
            ans.add(keyPoint);
            prevHeight = height;
        }
        return ans; 
    }
    
    // used to add all start, end points to a priority queue
    class Point implements Comparable<Point>{
        int x;
        int height;
        boolean isStart;
        public Point(int x, int height, boolean isStart){
            this.x = x;
            this.height = height;
            this.isStart = isStart;
        }
        
        @Override
        public int compareTo(Point o){
            if(this.x != o.x){
                return Integer.compare(this.x, o.x);
            }
            else{
                // if x position is same, we want to keep the buildings that start at this point
                // before the buildings that end at this point to get correct answer
                // and for cases where both are start or both are end, -height will give correct expected values
                return Integer.compare((this.isStart ? -this.height : this.height),(o.isStart ? -o.height : o.height));
            }
        }
    }
    
            
    // Idea is to first create buildings list in terms of critical points
    // in sorted order of x position, and then iterate over them, adding heights to priority queue
    // to keep track of the current running building heights; as building starts add to pq, when it ends remove from pq
    // when maxheight changes, add the point to answer since its a critical point
    // Time: O(n^2): for every building, operate on heap (O(log N)) operation, but removal will also cause O(n) since not just max value is removed
    //  can be O(n logn) if tree map is used instead of priority queue since both addition and removal will be O(log n) and key value pair will be
    //  key: height, value: count of this height; and max will be obtained using last object in map (as it is sorted, will give max value)
    // Space: O(n) store all buildings in new list as well as in heap
    private List<List<Integer>> lcPriorityQueueSolution(int[][] buildings){
        List<List<Integer>> ans = new ArrayList();
        int n = buildings.length;
        int j = 0;
        Point[] list = new Point[n * 2];
        for(int[] building : buildings){
            list[j++] = new Point(building[0], building[2], true);
            list[j++] = new Point(building[1], building[2], false);
        }
        Arrays.sort(list);
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        
        // add ground level to max heap
        pq.offer(0);
        int maxHeight = 0;
        int prevMaxHeight = 0;
        for(Point point: list){
            
            // add or remove based on start/end
            if(point.isStart){
                pq.offer(point.height);
            } else{
                pq.remove(point.height);
            }
            maxHeight = pq.peek();
            
            // max running height has changed, found a critical point; add it to ans array
            if(maxHeight != prevMaxHeight){
                prevMaxHeight = maxHeight;
                List<Integer> ansPoint = new ArrayList();
                ansPoint.add(point.x);
                ansPoint.add(maxHeight);
                ans.add(ansPoint);
            }
        }
        return ans;
    }
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if(buildings == null || buildings.length == 0){
            return new ArrayList();
        }
        
        //return originalTLESolution(buildings);
        return lcPriorityQueueSolution(buildings);
        
    }
}
