/*

LeetCode: 69. Sqrt(x)

Easy

Link: https://leetcode.com/problems/sqrtx/

Topics: Math, Binary Search

Acceptance: 31.5

Implement int sqrt(int x).

Compute and return the square root of x, where x is guaranteed to be a non-negative integer.

Since the return type is an integer, the decimal digits are truncated and only the integer part of the result is returned.

Example 1:

Input: 4
Output: 2
Example 2:

Input: 8
Output: 2
Explanation: The square root of 8 is 2.82842..., and since 
             the decimal part is truncated, 2 is returned.


 */
class Solution {
	private int binarySearchMethod(int x){
    	if(x==0)return 0;
        int l=1,h=x,mid=0;
        while(l<=h){
            mid = l+(h-l)/2;
            if(x/mid > = mid){
            	if(x/(mid+1) <=mid) return mid;
            	l = mid+1;
            }
            else h = mid-1;
        }
        return l;
    
	}

	// Reference: https://www.youtube.com/watch?v=tUFzOLDuvaE
	//
	// It is an approximation method that uses tangents at a point on function f(x) = x^2 - num
	// graph is parabolic which intersects x axis at sqrt(x)
	// Tangents intersection with x axis gives us another point, the y equivalent of which on the function graph
	// will give new point for new tangent, this way we get closer and closer to root which intersects with x axis
	// dy/dx = 2x
	// => tangent equation is y = f'(xi) (x- xi) + f(xi)
	// therefore for finding x intercept, y=0
	// => 0 = f'(xi)(x-xi) + f(xi)
	// this is simplified to x' = x - (f(xi))/(f'(xi))
	// where x' is the new x value
	// x' = x - (xi^2 - num)/(2*xi)
	// x' = (x+(num/x))/2
	private int newtonMethod(int x){
		int r = x;
		while(r*r>x){
			r= (r+(x/r))/2;
		}
		return r;
	}
	private int bitManipMethod(int x) {
        long ans = 0;
        // Start from MSB; 16th bit is larger than sqrt of Integer.MAX_VALUE so can start from 16
        long bit = 1l << 16;
        
        while(bit > 0) {
            // Set current bit in the ans value
            ans |= bit;
            // If ans is larger than root value of x, then remove this bit from ans; else keep it
            if (ans * ans > x) {
                ans ^= bit;
            }
            // Repeat for next bit to right
            bit >>= 1;
        }
        return (int)ans;
    }
    public int mySqrt(int x) {
    	
    	return bitManipMethod(x);
    	// return binarySearchMethod(x);
    	// return newtonMethod(x);
    }
}