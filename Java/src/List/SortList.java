package List;
/*
在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

示例 1:

输入: 4->2->1->3
输出: 1->2->3->4
示例 2:

输入: -1->5->3->4->0
输出: -1->0->3->4->5
 */
public class SortList {
    // my solution
    public ListNode sortList(ListNode head) {
        ListNode p = head;
        int len = 0;
        while (p != null) {
            p = p.next;
            len++;
        }
        return mergeSort(head, len);
    }

    private ListNode mergeSort(ListNode head, int len) {
        if (len <= 1) return head;
        int firstLen = len / 2;
        ListNode firstEnd = head;
        for (int i = 0; i < firstLen - 1; i++) {
            firstEnd = firstEnd.next;
        }
        ListNode secondHead = firstEnd.next;
        firstEnd.next = null;

        ListNode l1 = mergeSort(head, firstLen);
        ListNode l2 = mergeSort(secondHead, len - firstLen);

        ListNode ans = new ListNode(0);
        ListNode ansHead = ans;
        while (l1 != null && l2 != null) {
            if (l1.val > l2.val) {
                ans.next = new ListNode(l2.val);
                l2 = l2.next;
            } else {
                ans.next = new ListNode(l1.val);
                l1 = l1.next;
            }
            ans = ans.next;
        }
        while (l1 != null) {
            ans.next = new ListNode(l1.val);
            l1 = l1.next;
            ans = ans.next;
        }
        while (l2 != null) {
            ans.next = new ListNode(l2.val);
            l2 = l2.next;
            ans = ans.next;
        }
        return ansHead.next;
    }

    private void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + ", ");
            head = head.next;
        }
    }

    // best practice
    public ListNode sortlist(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        prev.next = null;
        ListNode l1 = sortlist(head);
        ListNode l2 = sortlist(slow);

        return merge(l1, l2);
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {              // 无需新建一个节点，只需要将连接引用过去即可
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        if (l1 != null) {               // 无需一个个复制节点
            p.next = l1;
        }
        if (l2 != null) {
            p.next = l2;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(3);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(4);
        SortList sort = new SortList();
        sort.printList(sort.sortList(head));
    }
}
