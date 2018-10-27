package Exercises;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    private List<List<Integer>> list;
    private int[] nums;
    public List<List<Integer>> subsets(int[] nums) {
        list = new ArrayList<>();
        this.nums = nums;
        getSets(new ArrayList<>(), 0);
        return list;
    }

    private void getSets(List<Integer> tempList, int i){
        if (i == nums.length) {
            list.add(new ArrayList<>(tempList));
        } else {
            getSets(tempList, i+1);         // 不选此数字
            tempList.add(nums[i]);
            getSets(tempList, i+1);           // 选择此数字
            tempList.remove(tempList.size() - 1);
        }
    }

    public static void main(String[] args){
        List<List<Integer>> list = new Subsets().subsets(new int[]{1, 2,});
        for (List<Integer> l: list){
            for (int s: l){
                System.out.print(s + " ");
            }
            System.out.println();
        }
        System.out.println("size: " + list.size());
    }
}
