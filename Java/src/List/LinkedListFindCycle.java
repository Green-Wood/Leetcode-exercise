package List;

import java.util.HashSet;
import java.util.Set;

/*
给定一个链表，判断链表中是否有环。
 */
public class LinkedListFindCycle {
    // double point
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }

    // set version
    public boolean setVersion(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }

    // 找到入环的第一个节点的位置, set version
    public ListNode cyclePosition(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) return head;
            set.add(head);
            head = head.next;
        }
        return null;
    }

    // double point version
    /*
    设从开始到入环处距离为x1
    从入环到两个指针相遇的距离为x2
    从相遇处绕回入环处的距离为x3
    则slow指针走了x1 + x2
    fast指针走了x1 + x2 + x3 + x2
    因为fast指针的速度是slow指针的两倍，因此 2 (x1 + x2) = x1 + x2 + x3 + x2
    解得：x1 = x3
    因此在相遇后，从头开始走相同的距离再遇见则是入环处
     */
    public ListNode cycleDetect(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        boolean isDetect = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) {
                isDetect = true;
                break;
            }
        }

        if (!isDetect) return null;
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
