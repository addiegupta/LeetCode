/*

LeetCode: 295. Find Median from Data Stream

Hard

Link: https://leetcode.com/problems/find-median-from-data-stream/

Topics: Heap, Design

Acceptance: 37.6

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

For example,
[2,3,4], the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

Design a data structure that supports the following two operations:

void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
 

Example:

addNum(1)
addNum(2)
findMedian() -> 1.5
addNum(3) 
findMedian() -> 2
 

Follow up:

If all integer numbers from the stream are between 0 and 100, how would you optimize it?
If 99% of all integer numbers from the stream are between 0 and 100, how would you optimize it?
*/

class MedianFinder {
    // Store smaller elements in maxHeap, larger elements in min Heap
    // So median will be obtained as avg of maxHeap and minHeap top elements for even length
    // or from one of the heaps
    PriorityQueue<Integer> minHeap;
    PriorityQueue<Integer> maxHeap;
    double median;

    /** initialize your data structure here. */
    public MedianFinder() {
        this.minHeap = new PriorityQueue<>();
        this.maxHeap = new PriorityQueue<>((x,y)->y-x);
        this.median = 0;
    }
    
    // Insert the number in minHeap or maxHeap
    // If diff bw sizes of the heaps exceeds 2, then one element is moved to the other heap
    public void addNum(int num) {
        int minSize = minHeap.size();
        int maxSize = maxHeap.size();
        
        // Add element to minHeap (right side elements of an array)
        if(num>median){
            
            minHeap.add(num);
            
            // Shift one element from minheap to maxHeap
            if(minSize>maxSize){
                maxHeap.add(minHeap.poll());
            }
        }
        // Add element to maxHeap (left side elements of array)
        else{

            maxHeap.add(num);
            
            // Shift one element from maxHeap to minHeap           
            if(minSize<maxSize){
                minHeap.add(maxHeap.poll());
            }

        }
        // Set new median value
        if(minHeap.size()>maxHeap.size()){
            median = minHeap.peek();
        }
        else if(maxHeap.size()>minHeap.size()){
            median = maxHeap.peek();
        }
        // Even size case
        else median = ((double)minHeap.peek() + (double)maxHeap.peek())/2;
    }
    
    public double findMedian() {
        return median;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */