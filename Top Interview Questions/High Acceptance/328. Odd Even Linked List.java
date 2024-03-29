/*

LeetCode: 328. Odd Even Linked List

Medium

Link: https://leetcode.com/problems/odd-even-linked-list/

Topics: Linked List

Acceptance: 49.2

Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

Example 1:

Input: 1->2->3->4->5->NULL
Output: 1->3->5->2->4->NULL
Example 2:

Input: 2->1->3->5->6->4->7->NULL
Output: 2->3->6->7->1->5->4->NULL
Note:

The relative order inside both the even and odd groups should remain as it was in the input.
The first node is considered odd, the second node even and so on ...
 
*/
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */

class Solution {
    public ListNode oddEvenList(ListNode head) {
        
        // List is already in required state
	// this many checks are not required
        // if(head == null || head.next==null || head.next.next == null)return head;
        if(head == null )return head;
        
        // Needed to connect tail of odd list to head of even list at the end
        ListNode evenHead=head.next;

        // Pointers to tails of odd and even list
        ListNode even=head.next,odd=head;
        while(even!=null && even.next!=null){

            // Connect the nodes as required
            odd.next=odd.next.next;
            even.next=even.next.next;
            odd=odd.next;
            even=even.next;
        }
        odd.next=evenHead;
        return head;
    }
}
