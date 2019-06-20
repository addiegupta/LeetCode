/*

LeetCode: 160. Intersection of Two Linked Lists

Easy

Link: https://leetcode.com/problems/intersection-of-two-linked-lists/

Topics: Linked List

Acceptance: 34.1


Write a program to find the node at which the intersection of two singly linked lists begins.

For example, the following two linked lists:


begin to intersect at node c1.

 

Example 1:


Input: intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
Output: Reference of the node with value = 8
Input Explanation: The intersected node's value is 8 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [4,1,8,4,5]. From the head of B, it reads as [5,0,1,8,4,5]. There are 2 nodes before the intersected node in A; There are 3 nodes before the intersected node in B.
 

Example 2:


Input: intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
Output: Reference of the node with value = 2
Input Explanation: The intersected node's value is 2 (note that this must not be 0 if the two lists intersect). From the head of A, it reads as [0,9,1,2,4]. From the head of B, it reads as [3,2,4]. There are 3 nodes before the intersected node in A; There are 1 node before the intersected node in B.
 

Example 3:


Input: intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
Output: null
Input Explanation: From the head of A, it reads as [2,6,4]. From the head of B, it reads as [1,5]. Since the two lists do not intersect, intersectVal must be 0, while skipA and skipB can be arbitrary values.
Explanation: The two lists do not intersect, so return null.
 

Notes:

If the two linked lists have no intersection at all, return null.
The linked lists must retain their original structure after the function returns.
You may assume there are no cycles anywhere in the entire linked structure.
Your code should preferably run in O(n) time and use only O(1) memory.


 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
	
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		//return getListLengthsFirstSolution(headA,headB);
		return switchPointerToOtherListSolution(headA,headB);
		
	}
	private ListNode getListLengthsFirstSolution(ListNode headA,ListNode headB){
	
		// First calculate lengths of the 2 lists (say n1 and n2)
        int n1=0,n2=0;
        ListNode trav = headA;
        while(trav!=null){
            trav=trav.next;
            n1++;
        }
        trav=headB;
        while(trav!=null){
            trav=trav.next;
            n2++;
        }
		
		
		// Now start pointers at both the lists and advance the bigger list pointer by n1-n2 nodes
        int diff = Math.abs(n1-n2);
        ListNode big,small;
        if(n1>n2){
            big = headA;
            small = headB;
        }
        else{
            big = headB;
            small=headA;
        }
        for(int i=0;i<diff;i++)big=big.next;
		
		// The 2 pointers will eventually meet at the intersection node
        while(big!=small){
            big=big.next;
            small=small.next;
        }
		return big;
	}
	
	// Similar to the above approach (based on the length of the lists)
	// Let List A have m nodes and List B have n nodes
	// It can be observed that total number of nodes is (m+n-x) where x is common list length ----(1)
	// Now once pointer on A has traversed m nodes, if it is pointed to head of B then remaining nodes till intersection point is n-x
	// Hence when A reaches intersection point it will have covered total m + (n-x) nodes
	// Similarly when B reaches int. point, it will have covered total n+ (m-x) nodes
	// They will thus meet at int. point
	// 
	// If the lists do not intersect, then the loop will end after second iteration
	// This is because total number of nodes will be m+n and they will both have traversed m+n nodes together and will meet at null
	private ListNode switchPointerToOtherListSolution(ListNode headA,ListNode headB){
		
		if(headA==null || headB==null)return null;
		
        ListNode a = headA,b=headB;
		while(a!=b){
			a = (a==null)?headB:a.next;
			b = (b==null)?headA:b.next;
		}
		return a;
		
    }
		
	}
}