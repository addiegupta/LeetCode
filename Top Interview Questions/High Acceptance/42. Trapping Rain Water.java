/*

LeetCode: 42. Trapping Rain Water

Hard

Link: https://leetcode.com/problems/group-anagrams/

Topics: Array, Two Pointer, String

Acceptance: 43.3

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
 */
 
class Solution {
    public int trap(int[] height) {
        
		//return dpArrayMethod(height);
        return twoPointerNoArrayMethod(height);
    }
	
	// O(n) time, O(n) space
	private int dpArrayMethod(int[] height){
	
        if(height==null || height.length==0)return 0;

        int n = height.length;
        int[] maxLeft = new int[n];
        int[] maxRight = new int[n];
        
		// Create arrays which contain the highest element value on left and right of index
        maxLeft[0]=height[0];
        maxRight[n-1]=height[n-1]; 
		for(int i=1;i<n;i++){
            maxLeft[i] = Math.max(maxLeft[i-1],height[i]);
            maxRight[n-1-i] = Math.max(maxRight[n-i],height[n-i]);
        }
		
        int ans=0;
        int lower;
        for(int i=1;i<n-1;i++){
			// Max water level will be smaller of the 2 highest walls on both sides
            lower = Math.min(maxLeft[i],maxRight[i]);
			// If current height is less than lower of the 2, only then will water be trapped
            if(lower>height[i]) ans += (lower - height[i]);
        }
	}
	
	// O(n) time O(1) space
    private int twoPointerNoArrayMethod(int[] height){
        
        if(height==null || height.length==0)return 0;
        
        int n = height.length;
        int left=0,right=n-1;
        int maxLeft=0,maxRight=0;
        int ans=0;
        
		// Two Pointer approach that doesnt require memory
        while(left<right){
			
			// If height at left is less than the height value at right,then higher values in middle do not effect the solution
			// Similar is the case for else statement in case of right
            if(height[left]<height[right]){
				// Current height is greater than max on left; no water is trapped here
                if(height[left] >= maxLeft)  maxLeft = height[left];
				// Else add the water for this index
                else ans += maxLeft - height[left];
                left++;
            }
            else{
				// Current height is greater than max on right, no water is trapped here
                if(height[right] >= maxRight) maxRight = height[right];
				// Else add the water trapped here
                else ans += maxRight - height[right];
                right--;
            }
        }
        return ans;
    }
}