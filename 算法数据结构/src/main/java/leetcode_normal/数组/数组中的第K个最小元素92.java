package leetcode_normal.数组;

/*
 * 在未排序的数组中找到第 k 个最大的元素。请注意，
 * 你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
 * */
public class 数组中的第K个最小元素92 {
    public static void main(String[] args) {
        int[] arr = {3, 2, 7, 1, 2, 4, 5, 5, 6};
//        findK(arr,2);
        System.out.println(findK1(arr, 5));

    }

    public static int findK1(int[] arr, int k) {
        if (k > arr.length) {
            return 0;
        }
        int[] arry = new int[k];
        System.arraycopy(arr, 0, arry, 0, k);

        for (int i = k / 2 - 1; i >= 0; i--) {
            BigHeap(arry, i, k); //前K个数 构建大根堆
        }

//        后面的所有的数 维护大根堆

        for (int i = k; i < arr.length; i++) {
            if (arr[i] < arry[0]) {
                arry[0] = arr[i];
                BigHeap(arry, 0, k);
            }
        }
        return arry[0];
    }

    public static void BigHeap(int[] arr, int index, int len) {
        int temp = arr[index];
        for (int k = 2 * index + 1; k < len; k = 2 * k + 1) {
            if (k + 1 < len && arr[k + 1] > arr[k]) {
                k++;
            }
            if (temp < arr[k]) {
                arr[index] = arr[k];
                index = k;
            } else {
                break;
            }
        }
        arr[index] = temp;

    }


    //    public int findKthLargest(int[] nums, int k) {
//        QuikSort(nums, 0, nums.length - 1);
//        return nums[nums.length-k];
//    }
    public static int findK(int[] arr, int k) {
        QuikSort(arr, 0, arr.length - 1);
        return arr[arr.length - k];
    }


    public static void QuikSort(int[] arr, int low, int high) {
        if (arr.length == 0) {
            return;
        }
        int l = low;
        int r = high;
        if (r < l) {
            return;
        }
        int tar = arr[l];
        while (r > l) {
            while (r > l && arr[r] >= tar) {
                r--;
            }
            arr[l] = arr[r];
            while (r > l && arr[l] < tar) {
                l++;
            }
            arr[r] = arr[l];
        }
        arr[l] = tar;
        QuikSort(arr, low, l - 1);
        QuikSort(arr, l + 1, high);
    }


    /*使用堆排序  维护大根堆*/

}
