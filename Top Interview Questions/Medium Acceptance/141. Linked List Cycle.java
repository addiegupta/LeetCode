/*

LeetCode: 141. Linked List Cycle

Easy

Link: https://leetcode.com/problems/linked-list-cycle/

Topics: Linked List, Two Pointers

Acceptance: 37.1


Given a linked list, determine if it has a cycle in it.

To represent a cycle in the given linked list, we use an integer pos which represents the position (0-indexed) in the linked list where tail connects to. If pos is -1, then there is no cycle in the linked list.

 

Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the second node.


Example 2:

Input: head = [1,2], pos = 0
Output: true
Explanation: There is a cycle in the linked list, where tail connects to the first node.


Example 3:

Input: head = [1], pos = -1
Output: false
Explanation: There is no cycle in the linked list.
 

Follow up:

Can you solve it using O(1) (i.e. constant) memory?
 
*/
 
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// Floyd's Tortoise and Hare Algorithm
// Fast and slow pointers will eventually meet if a cycle is present in the list
public class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow= head, fast = head;
        while(fast!=null && fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast)return true;
        }
        return false;
    }
}