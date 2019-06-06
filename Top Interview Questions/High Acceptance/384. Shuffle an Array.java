/*

LeetCode: 384. Shuffle an Array

Medium

Link: https://leetcode.com/problems/shuffle-an-array/

Topics: Array

Acceptance: 50.1


Shuffle a set of numbers without duplicates.

Example:

// Init an array with set 1, 2, and 3.
int[] nums = {1,2,3};
Solution solution = new Solution(nums);

// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
solution.shuffle();

// Resets the array back to its original configuration [1,2,3].
solution.reset();

// Returns the random shuffling of array [1,2,3].
solution.shuffle();

 */
 
 
class Solution {

    int[] nums;
    Random rand = new Random();
    public Solution(int[] nums) {
        this.nums=nums;
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return nums;
    }
    
    private void swapElements(int i,int j,int[] ans){
        int temp = ans[i];
        ans[i]=ans[j];
        ans[j]=temp;
    }
    /** Returns a random shuffling of the array. */
	
	// Fisher Yates algorithm,
	/*
	The Fisher-Yates algorithm is remarkably similar to the brute force solution. 
	On each iteration of the algorithm, we generate a random integer between the current index and the 
	last index of the array. Then, we swap the elements at the current index and the chosen index - 
	this simulates drawing (and removing) the element from the hat, as the next range from which we select a random index 
	will not include the most recently processed one. One small, yet important detail is that it is possible to swap an element
	with itself - otherwise, some array permutations would be more likely than others.
	*/
    public int[] shuffle() {
        int[] ans = nums.clone();
        for(int i=ans.length-1;i>=0;i--){
            swapElements(i,rand.nextInt(i+1),ans);
        }
        return ans;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */