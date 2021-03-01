package 排序算法;

import static 排序算法.快速排序.QuickSort;

public class 快速排序 {
    public static void main(String[] args) {
        int[] a = {3, 4, 2, 1, -7, 0};
        QuickSort(a, 0, a.length - 1);
        System.out.print(a);
    }

    public static void QuickSort(int[] arr, int low, int high) {
        int l = low;
        int r = high;
        if (l > r) {
            return;
        }
        int tar = arr[l];
        while (r > l) {
            while (r > l && arr[r] > tar) {
                r--;
            }
            arr[l] = arr[r];
            while (r > l && arr[l] < tar) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = tar;
        QuickSort(arr, low, l - 1);
        QuickSort(arr, l + 1, high);
    }
}
