package Exercises.Others;

import java.util.*;

public class groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++){
            char[] array = strs[i].toCharArray();
            Arrays.sort(array);
            String s = String.valueOf(array);
            if (!map.containsKey(s)) map.put(s, new ArrayList<>());
            map.get(s).add(strs[i]);
        }
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args){
        List<List<String>> list = new groupAnagrams().groupAnagrams(new String[] {"eat", "tea", "tan", "ate", "nat", "bat"});
        for (List<String> l: list){
            for (String s: l){
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}
