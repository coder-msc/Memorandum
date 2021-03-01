package 排序算法;

public class 归并排序 {
    public static void main(String[] args) {
        int[] a = {3, 4, 2, 1, -7, 0};
        int[] tmp = new int[a.length];
        sort(a, 0, a.length - 1, tmp);
//        mergeSort(a);
        System.out.print(a);
    }


    public static void sort(int[] arr, int l, int r, int[] tmp) {
        if (r > l) {
            int mid = (l + r) / 2;
            sort(arr, l, mid, tmp);
            sort(arr, mid + 1, r, tmp);
            mergeSort(arr, l, mid, r, tmp);
        }
    }

    public static void mergeSort(int[] arr, int left, int mid, int right, int[] temp) {
        System.out.print("Merge次数"); // 3, 4, 2, 1, -7, 0
        int i = left;
        int j = mid + 1;
        int t = 0;
        while (i <= mid && j <= right) {
            if (arr[i] > arr[j]) {
                temp[t++] = arr[j++];
            } else {
                temp[t++] = arr[i++];
            }
        }
        while (i <= mid) {
            temp[t++] = arr[i++];
        }
        while (j <= right) {
            temp[t++] = arr[j++];
        }
        t = 0;
        while (right >= left) {
            arr[left++] = temp[t++];
        }
//        return arr;
    }
}
