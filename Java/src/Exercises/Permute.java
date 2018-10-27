package Exercises;

import java.util.ArrayList;
import java.util.List;

public class Permute {
    List<List<Integer>> list;
    int[] nums;
    public List<List<Integer>> permute(int[] nums) {
        list = new ArrayList<>();
        this.nums = nums;
        ArrayList<Integer> tempList = new ArrayList<>();
        backTrack(tempList);
        return list;
    }

    private void backTrack(List<Integer> temptList){
        if (temptList.size() == nums.length){
            list.add(new ArrayList<>(temptList));
        } else {
            for (int num: nums){
                if (temptList.contains(num)) continue;
                temptList.add(num);
                backTrack(temptList);
                temptList.remove(temptList.size() - 1);
            }
        }
    }

    public static void main(String[] args){
        List<List<Integer>> list = new Permute().permute(new int[]{1, 2, 3});
        for (List<Integer> l: list){
            for (int s: l){
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
