package Array;

import java.util.HashSet;
import java.util.Set;

public class FirstMissingPositive {
    // Time: O(n)        Space: O(n)
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        int max = 0;
        for (int n: nums) {
            set.add(n);
            max = Math.max(n, max);
        }
        for (int i = 1; i <= max; i++) {
            if (!set.contains(i)) return i;
        }
        return max + 1;
    }

    public static void main(String[] args) {
        System.out.println(new FirstMissingPositive().firstMissingPositive(new int[]{-1}));
    }
}
