/*

LeetCode: 142. Linked List Cycle II

Medium

Link: https://leetcode.com/problems/linked-list-cycle-ii/

Topics: Hash Table, Linked List, Two Pointers

Acceptance: 44.2

Given the head of a linked list, return the node where the cycle begins. If there is no cycle, return null.

There is a cycle in a linked list if there is some node in the list that can be reached again by continuously following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer is connected to (0-indexed). It is -1 if there is no cycle. Note that pos is not passed as a parameter.

Do not modify the linked list.



Example 1:


Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.
Example 2:


Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.
Example 3:


Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.


Constraints:

The number of the nodes in the list is in the range [0, 104].
-105 <= Node.val <= 105
pos is -1 or a valid index in the linked-list.


Follow up: Can you solve it using O(1) (i.e. constant) memory?

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
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null){
            return null;
        }
        // creating dummy node just to handle edge case where loop might start at head
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy;
        boolean found = false;
        while(fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next;
            // fast moves at 2 nodes unless if cycle has been found then only 1 node
            if(!found){
                fast = fast.next;
            }
            // cycle found, now move fast to first node and move both at 1 node speed
            // both will again meet at start of cycle
            //Distance traveled by slow when they meet: L1+L2
            //Distance traveled by fast when they meet: L1+L2+x+L2, where x is the remaining length of the cycle (meeting point to start of the cycle).

            //2(L1+L2) = L1+L2+x+L2
            //2L1 + 2L2 = L1+2L2+x
            //=> x = L1
            if(slow == fast){
                if(found){
                    return slow;
                }
                found = true;
                fast = dummy;
            }
        }
        return null;
    }
}
