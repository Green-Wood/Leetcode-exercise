package Exercises.Backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> lists = new ArrayList<>();
        backtracing(lists, new ArrayList<>(), 1, n, k);
        return lists;
    }

    private void backtracing(List<List<Integer>> lists, List<Integer> currList, int start, int remain, int k) {
        if (remain < 0 || k < 0) return;
        else if (remain == 0 && k == 0) {
            lists.add(new ArrayList<>(currList));
        } else {
            for (int i = start; i <= remain && i <= 9; i++) {
                currList.add(i);
                backtracing(lists, currList, i + 1, remain - i, k - 1);
                currList.remove(currList.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = new CombinationSum3().combinationSum3(3, 9);
        lists.forEach(System.out::println);
    }
}
