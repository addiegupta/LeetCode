/*

LeetCode: 341. Flatten Nested List Iterator

Medium

Link: https://leetcode.com/problems/flatten-nested-list-iterator/

Topics: Tree, Depth First Search

Acceptance: 48.4

Given a nested list of integers, implement an iterator to flatten it.

Each element is either an integer, or a list -- whose elements may also be integers or other lists.

Example 1:

Input: [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, 
             the order of elements returned by next should be: [1,4,6].
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class NestedIterator implements Iterator<Integer> {

    Stack<NestedInteger> stack = new Stack();
    // Store all elements of list in stack on construction
    public NestedIterator(List<NestedInteger> nestedList) {
        for(int i=nestedList.size()-1;i>=0;i--) stack.push(nestedList.get(i));
    }

    
    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }


    // flatten list if required
    @Override
    public boolean hasNext() {
        // if top element is not integer, then it is a list
        // flatten this list and push on stack
        // repeat this process until integer is obtained
        while(!stack.isEmpty()){
            NestedInteger curr = stack.peek();
            if(curr.isInteger())return true;

            stack.pop();
            for(int i=curr.getList().size()-1;i>=0;i--)
                stack.push(curr.getList().get(i));
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */