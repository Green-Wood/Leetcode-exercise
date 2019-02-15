package Exercises.Others;

import java.util.*;

public class TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer>[] freq = new List[nums.length+1];
        for (int n: map.keySet()){
            int times = map.get(n);
            if (freq[times] == null) freq[times] = new ArrayList<>();
            freq[times].add(n);
        }
        List<Integer> res = new ArrayList<>();
        for (int i = freq.length - 1; i > 0 && k > 0; i--){
            if (freq[i] != null){
                res.addAll(freq[i]);
                k -= freq[i].size();
            }
        }
        return res;
    }

    public List<Integer> treeMapVersion(int[] nums, int k){
        Map<Integer, Integer> map = new HashMap<>();
        for (int num: nums){
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        TreeMap<Integer, List<Integer>> treeMap = new TreeMap<>();
        for (int n: map.keySet()){
            int freq = map.get(n);
            if (!treeMap.containsKey(freq)) treeMap.put(freq, new ArrayList<>());
            treeMap.get(freq).add(n);
        }
        List<Integer> res = new ArrayList<>();
        while (res.size() < k){
            Map.Entry<Integer, List<Integer>> entry = treeMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }

    public static void main(String[] args){
//        List<Integer> l = new TopKFrequent().topKFrequent(new int[]{4,1,-1,2,-1,2,3}, 2);
//        for (int n: l){
//            System.out.println(n);
//        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        System.out.println(queue.peek());
    }
}
