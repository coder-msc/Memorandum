package leetcode_normal.数组;

/*

峰值元素是指其值大于左右相邻值的元素。
给你一个输入数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，
在这种情况下，返回 任何一个峰值 所在位置即可。
* */
public class 寻找峰值79 {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 1};
        int peakElement = findPeakElement(arr);
        System.out.println(peakElement);
        System.out.println(findNum(arr));

    }

    public static int findPeakElement(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return 0;
        }
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                return i;
            }
        }
        return nums.length - 1;
    }

    public static int findNum(int[] arr) {
        return search(arr, 0, arr.length - 1);
    }

    public static int search(int[] arr, int l, int r) {
        if (l == r) {
            return l;
        }
        int mid = l + (r - l) / 2;
        if (arr[mid] > arr[mid+1]) {
            return search(arr, l, mid);
        } else {
            return search(arr, mid + 1, r);
        }
    }
}
