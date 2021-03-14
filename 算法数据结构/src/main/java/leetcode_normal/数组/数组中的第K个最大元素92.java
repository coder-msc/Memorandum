package leetcode_normal.数组;

/*
 * 在未排序的数组中找到第 k 个最大的元素。请注意，
 * 你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素
 * */
public class 数组中的第K个最大元素92 {
    public static void main(String[] args) {
        int[] arr = {3,2,3,1,2,4,5,5,6};
//        findK(arr,2);
        System.out.println(findK(arr,4));

    }
//    public int findKthLargest(int[] nums, int k) {
//        QuikSort(nums, 0, nums.length - 1);
//        return nums[nums.length-k];
//    }
    public static int findK(int[] arr, int k) {
        QuikSort(arr, 0, arr.length - 1);
        return arr[arr.length-k];
    }


    public static void QuikSort(int[] arr, int low, int high) {
        if (arr.length == 0) {
            return;
        }
        int l = low;
        int r = high;
        if(r<l){return;}
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
}
