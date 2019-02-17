package Array;

import java.util.Arrays;
/*
假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。

注意：
总人数少于1100人。

示例

输入:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]

输出:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 */
public class QueueReconstructByHeight {
    public int[][] reconstructQueue(int[][] people) {
        // 首先按照身高从高到低进行排序，相同高度的按照k排序
        Arrays.sort(people, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o2[0] - o1[0];
            } else {
                return o1[1] - o2[1];
            }
        });
        // 随后对每个人进行插入排序，按照每个人的k值
        // 从前往后，身高低的后排序，这样在相同k值情况下身高低的人在前面
        int[][] ans = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            int pos = people[i][1];
            for (int j = i; j > pos; j--) {
                ans[j] = ans[j - 1];
            }
            ans[pos] = people[i];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] people = new int[][]{
                {7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2},
        };
        int[][] queue = new QueueReconstructByHeight().reconstructQueue(people);
        for (int[] p: queue) {
            System.out.println(Arrays.toString(p));
        }
    }
}
