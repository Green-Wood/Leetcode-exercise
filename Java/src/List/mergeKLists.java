package List;

/*
合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

输入:
[
  1->4->5,
  1->3->4,
  2->6
]
输出: 1->1->2->3->4->4->5->6

HINT: 使用优先队列
 */
import java.util.Comparator;
import java.util.PriorityQueue;

public class mergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val)
                    return -1;
                else if (o1.val > o2.val)
                    return 1;
                else
                    return 0;
            }
        });

        for (ListNode node: lists){
            if (node != null){
                queue.add(node);
            }
        }

        ListNode res = new ListNode(0);
        ListNode tail = res;
        while (!queue.isEmpty()){
            tail.next = queue.poll();
            tail = tail.next;

            if (tail.next != null){
                queue.add(tail.next);
            }
        }
        return res.next;
    }

    public static void main(String[] args){

    }
}
