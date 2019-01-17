package Exercises.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class Combination {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> lists = new ArrayList<>();
        backtracking(lists, new ArrayList<>(), 1, n, k);
        return lists;
    }

    private void backtracking(List<List<Integer>> lists, List<Integer> currList, int start, int n, int k) {
        if (k == 0) {
            lists.add(new ArrayList<>(currList));
        } else {
            for (int i = start; i <= n; i++) {
                currList.add(i);
                backtracking(lists, currList, i + 1, n, k - 1);
                currList.remove(currList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new Combination().combine(4, 2);
        lists.forEach(System.out::println);
    }
}
