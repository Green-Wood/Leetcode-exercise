package List;

import java.util.HashSet;
import java.util.Set;

/*
编写一个程序，找到两个单链表相交的起始节点。



例如，下面的两个链表：

A:          a1 → a2
                   ↘
                     c1 → c2 → c3
                   ↗
B:     b1 → b2 → b3
在节点 c1 开始相交。



注意：

如果两个链表没有交点，返回 null.
在返回结果后，两个链表仍须保持原有的结构。
可假定整个链表结构中没有循环。
程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class IntersectionOfTwoLists {
    // 使用HashSet，但不满足内存为O(1)的条件
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> set = new HashSet<>();
        while (headA != null || headB != null) {
            if (headA != null){
                if (set.contains(headA)) return headA;
                set.add(headA);
                headA = headA.next;
            }
            if (headB != null) {
                if (set.contains(headB)) return headB;
                set.add(headB);
                headB = headB.next;
            }
        }
        return null;
    }
    // 类似于Floyd cycle detection
    public ListNode getIntersection(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) return null;
        ListNode a = headA;
        ListNode b = headB;
        while (a != b){
            if (a == null) a = headB;
            else a = a.next;
            if (b == null) b = headA;
            else b = b.next;
        }
        return a;
    }
}
