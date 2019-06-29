/*

LeetCode: 2. Add Two Numbers

Medium

Link: https://leetcode.com/problems/add-two-numbers/

Topics: Math, Linked List

Acceptance: 31.3

You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Example:

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8
Explanation: 342 + 465 = 807.


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
    // Code could have been cleaner, same logic as updated solution
    // No creation of new nodes unless additional carry to be created
    private ListNode initialSolution(ListNode l1,ListNode l2){
        if(l1==null)return l2;
        if(l2==null)return l1;
        
        ListNode tail1=l1;
        int carry =0,sum;
        ListNode trav1 = l1, trav2 = l2;
        
        // Add while both lists are not null
        while(trav1!=null && trav2!=null){
            sum = trav1.val + trav2.val + carry;
            trav1.val = sum%10;
            carry = sum/10;
            tail1 = trav1;
            trav1 = trav1.next;
            trav2 = trav2.next;
        }
        // If list1 gets finished, append list2 to tail of list1
        if(trav2!=null){
            tail1.next = trav2;
        }
        // Add remaining carry
        while(carry!=0){
            if(tail1.next == null){
                tail1.next = new ListNode(carry%10);
                carry/=10;
            }
            else{
                sum = tail1.next.val + carry;
                tail1.next.val = sum%10;
                carry = sum/10;
            } 
            tail1 = tail1.next;
        }
        return l1;
    }
    
    // Same logic,cleaner code
    private ListNode improvedSolution(ListNode l1, ListNode l2){
        ListNode head = l1;
        int carry =0,sum;

        // Traverse while list 1 does not get null; this simplifies and cleans up code a lot
        while(l1!=null){
            sum = carry + l1.val + (l2!=null ? l2.val : 0);
            carry = sum/10;
            l1.val = sum%10;
            // Append list 2 if list 1 gets finished
            if(l1.next == null && l2!=null){
                l1.next = l2.next;
                l2=null;
            }
            // Add remaining carry
            if(l1.next == null && carry ==1){
                l1.next = new ListNode(carry);
                l1=l1.next;
            }
            l1=l1.next;
            if(l2!=null) l2 = l2.next;
        }
        return head;
    }
    
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        // return initialSolution(l1,l2);
        return improvedSolution(l1,l2);
        
    }
}