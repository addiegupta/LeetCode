/*

LeetCode: 21. Merge Two Sorted Lists

Easy

Link: https://leetcode.com/problems/merge-two-sorted-lists/

Acceptance: 47.3

Topics: Linked list

Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

Example:

Input: 1->2->4, 1->3->4
Output: 1->1->2->3->4->4


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
    private ListNode newIterativeSolution (ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0), temp;
        ListNode tail = dummy;
        while (l1!=null && l2!=null){
            if (l1.val < l2.val){
                temp = l1;
                l1 = l1.next;
            } else{
                temp = l2;
                l2 = l2.next;
            }
            tail.next = temp;
            tail = tail.next;
        }
        tail.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    private ListNode iterativeSolution(ListNode l1,ListNode l2){
        if(l1==null)return l2;
        if(l2==null) return l1;

        // Init ans with smaller value node
        ListNode ans;
        if(l1.val<l2.val){
            ans=l1;
            l1=l1.next;
        }
        else{
            ans=l2;
            l2=l2.next;
        }

        // Traverse and create links for final list
        ListNode trav=ans;
        while(l1!=null && l2!=null){
            if(l1.val<l2.val){
                trav.next=l1;
                l1=l1.next;
            }
            else{
                trav.next=l2;
                l2=l2.next;
            }
            trav=trav.next;
        }
        // Connect any remaining node list
        if(l1!=null)trav.next=l1;
        if(l2!=null)trav.next=l2;
        return ans;
    }
    private ListNode recursiveSolution(ListNode l1,ListNode l2){
        if(l1==null)return l2;
        if(l2==null)return l1;
        
        if(l1.val<l2.val){
            l1.next = mergeTwoLists(l1.next,l2);
            return l1;
        }
        else{
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        
        // return iterativeSolution(l1,l2);
	// return newIterativeSolution(l1, l2);
        return recursiveSolution(l1,l2);
    }
}
