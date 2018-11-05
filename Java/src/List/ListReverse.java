package List;
/*
Reverse a singly linked list.

Example:

Input: 1->2->3->4->5->NULL
Output: 5->4->3->2->1->NULL
Follow up:

A linked list can be reversed either iteratively or recursively. Could you implement both?
 */
public class ListReverse {
    public  ListNode reverseList(ListNode node){
        ListNode pre = null;
        ListNode curr = node;
        while (curr != null){
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public ListNode reverseistRecursive(ListNode node){
        if (node == null || node.next == null) return node;
        ListNode p = reverseistRecursive(node.next);
        node.next.next = node;
        node.next = null;
        return p;
    }
}
