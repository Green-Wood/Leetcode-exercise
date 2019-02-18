package Exercises.DoublePoint;

import java.util.Arrays;
import java.util.TreeMap;

public class BoatsToSavePeople {
    public int numRescueBoats(int[] people, int limit) {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int weight: people) {
            treeMap.put(weight, treeMap.getOrDefault(weight, 0) + 1);
        }
        int count = 0;
        while (!treeMap.isEmpty()) {
            int key = treeMap.lastKey();
            while (treeMap.containsKey(key) && treeMap.get(key) > 0) {
                treeMap.put(key, treeMap.get(key) - 1);
                if (treeMap.get(key) == 0) treeMap.remove(key);
                Integer anotherKey = treeMap.floorKey(limit - key);
                if (anotherKey != null) {
                    int anotherValue = treeMap.get(anotherKey);
                    treeMap.put(anotherKey, anotherValue - 1);
                    if (anotherValue == 1) treeMap.remove(anotherKey);
                }
                count++;
            }
        }
        return count;
    }

    public int doublePoint(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int count = 0;
        while (i <= j) {
            count++;
            if (people[i] + people[j] <= limit) {
                i++;
            }
            j--;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new BoatsToSavePeople().doublePoint(new int[]{3, 4, 3, 5}, 5));
    }
}
