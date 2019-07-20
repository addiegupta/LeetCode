/*

LeetCode: 347. Top K Frequent Elements   

Medium

Link: https://leetcode.com/problems/top-k-frequent-elements/

Topics: Hash Table, Heap

Acceptance: 54.8

Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

*/

class Solution {
   
    public List<Integer> topKFrequent(int[] nums, int k) {
    	
    	// Possibly the best solution out of the 3
    	return bucketSortSolution(nums,k);
    	
    	// return sortArraySolution(nums,k);
    	// return minHeapSolution(nums,k);
    	
    }

    private class Pair
    {
        int k,v;
        public Pair(int k, int v)
        {
            this.k = k;
            this.v = v;
        }
    }
    private List<Integer> sortArraySolution(int[] nums, int k){

    	List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        int l =k;
        // first sort the array
        Arrays.sort(nums);
        List<Pair> map = new ArrayList<>();
        int count,temp;
        for(int i=0;i<n;i++){
            count = 1;
            temp = nums[i];
            while(i<n-1 && nums[i+1]==temp){
                i++;
                count++;
            }
            // Add number,count pair to list
            map.add(new Pair(temp,count));
        }
        // Sort list on basis of count
        Collections.sort(map, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if(p1.v==p2.v)return 0;
                else if(p1.v<p2.v)return 1;
                else return -1;
            }
        });
        
        for(int i=0;i<k;i++){
            ans.add(map.get(i).k);
        }
        return ans;
    }
    private List<Integer> minHeapSolution(int[] nums,int k){

    	// Create number,count map for all elements
    	HashMap<Integer,Integer> map = new HashMap();
        for(int n:nums){
            map.put(n,map.getOrDefault(n,0)+1);
        }

        // Min frequency heap
        PriorityQueue<Integer> heap = new PriorityQueue<>((n1,n2)-> map.get(n1)-map.get(n2));
        
        // because of min heap, keep only k elements in the heap
        // this way only the min frequency element will be removed in polling
        for(int n: map.keySet()){
            heap.add(n);
            // Keep only top k frequent elements
            if(heap.size()>k) heap.poll();
        }
        
        List<Integer> ans = new LinkedList();
        while(!heap.isEmpty()){
            ans.add(heap.poll());
        }

        // Reverse because priority queue was min heap
        Collections.reverse(ans);
        return ans;
    }
    private List<Integer> bucketSortSolution(int[] nums,int k){
    	int n = nums.length;
        
        // Keep buckets for each frequency value
        List<Integer>[] bucket = new List[n+1];
        // Create num,count map
        Map<Integer,Integer> map = new HashMap();
        for(int num: nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }
        // Sort in buckets as per frequency of occurence
        for(int key : map.keySet()){
            int freq = map.get(key);
            if(bucket[freq]==null) bucket[freq] = new ArrayList();
            bucket[freq].add(key);
        }
        // Create list for highest frequencies till ans size is less than 0
        List<Integer> ans = new ArrayList();
        for(int i=n;i>=0 && ans.size()<k;i--){
            if(bucket[i]!=null)ans.addAll(bucket[i]);
        }
        return ans.subList(0,k);
    }
}
