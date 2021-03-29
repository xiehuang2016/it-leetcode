package example.map;

import java.util.*;

/**
 * 给你一个整数数组 `arr` 和一个整数 `k` 。现需要从数组中恰好移除 `k` 个元素，请找出移除后数组中不同整数的最少数目。
 */
public class FindLeastNumOfUniqueInts {

    public static void main(String[] args) {
        int[] arr = {1, 1, 1, 2, 3, 3, 4};
        int k = 2;
        // 2(1,3)
        System.out.println(findLeastNumOfUniqueInts(arr, k));
    }

    public static int findLeastNumOfUniqueInts(int[] arr, int k) {
        Map<Integer, Integer> group = new HashMap<>();
        for (int num : arr) {
            int count = group.getOrDefault(num, 0) + 1;
            group.put(num, count);
        }

        List<int[]> freq = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : group.entrySet()) {
            int[] keyValue = {entry.getKey(), entry.getValue()};
            freq.add(keyValue);
        }

//        Collections.sort(freq, (keyValue1, keyValue2) -> keyValue1[1] - keyValue2[1]);
        Collections.sort(freq, Comparator.comparingInt(keyValue -> keyValue[1]));

        int ans = freq.size();
        for (int i = 0; i < freq.size(); i++) {
            int occ = freq.get(i)[1];
            if (k >= occ) {
                --ans;
                k -= occ;
            } else {
                break;
            }
        }
        return ans;
    }

}
