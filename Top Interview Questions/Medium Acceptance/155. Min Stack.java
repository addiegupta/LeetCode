/*

LeetCode: 155. Min Stack

Easy

Link: https://leetcode.com/problems/min-stack/

Topics: Stack, Design

Acceptance: 37.2

Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.
Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

 */
 
 // An alternate method can be to push a Pair of ints on the stack which contains the min value while inserting that node
class MinStack {
    
    int min =Integer.MAX_VALUE;
    Stack<Integer> stk;
    
    /** initialize your data structure here. */
    public MinStack() {
        this.stk = new Stack();
    }
    
    public void push(int x) {
		// New min found, also push the current min
		// The extra min inserted will be popped while popping this element
        if(x<=min){
            stk.push(min);
            min=x;
        }
        stk.push(x);
    }
    
    public void pop() {
		// Pop off extra min and set it as min
        if(stk.pop()==min)  min = stk.pop();  
    }
    
    public int top() {
        return stk.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */