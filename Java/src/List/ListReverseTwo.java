package List;
/*
反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

说明:
1 ≤ m ≤ n ≤ 链表长度。

示例:

输入: 1->2->3->4->5->NULL, m = 2, n = 4
输出: 1->4->3->2->5->NULL
 */
public class ListReverseTwo {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < m-1; i++){
            prev = prev.next;
        }
        ListNode curr = prev.next;
        ListNode then = curr.next;

        for (int i = 0; i < n-m; i++){      // ****** 每次进行一次翻转，将后面的节点提到前面来，并持续维护curr的下一个节点地址
            curr.next = then.next;
            then.next = prev.next;
            prev.next = then;
            then = curr.next;
        }

        return dummy.next;
    }
}
