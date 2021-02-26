package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/*
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 * */
public class TwoSum {
    // {1,3,4,5,5}  7
    public static void main(String[] args) {
        int[] test = {1, 1,2,2,3,3, 4,};
        int tar = 7;
        int[] ints = twoSum(test, tar);
        System.out.println(twoSum(test, tar));
    }

    public static int[] twoSum(int[] arr, int tar) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], i);
        }

        for (int j = 0; j < arr.length; j++) {
            if (map.containsKey(tar - arr[j])) {
                if (j != map.get(tar - arr[j])) {
                    return new int[]{j, map.get(tar - arr[j])};
                }
            }
        }

        return null;
    }
}
