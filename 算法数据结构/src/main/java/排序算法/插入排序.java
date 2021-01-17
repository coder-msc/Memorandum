package 排序算法;

public class 插入排序 {
    public static void main(String[] args) {
        int[] a = {3, 4, 2, 1, -7, 0};
        InsertMoveSort(a);
        for (int i : a) {
            System.out.print(i + "\t");
        }

    }

    public static int[] InsertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    swap(arr, j, j - 1);
                }
            }
        }
        return arr;
    }

    static void swap(int[] a, int b, int c) {
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }

    // 一个一个挪动

    public static int[] InsertMoveSort(int[] arr) {
        int i;
        int j;
        //第一种方法
        //{3, 4, 2, 1, -7, 0}
        for (i = 1; i < arr.length; i++) {
            int tmp = arr[i];
            for (j = i; j >= 0; j--) {
                if (arr[j - 1] > tmp) {
                    arr[j] = arr[j - 1];
                } else {
                    break;
                }
            }
            arr[j] = tmp;
        }
        // 第二种方法
        for (int m = 1; i < arr.length; i++) {
            int tar = arr[m];
            int count = m - 1;
            while (count >= 0 && arr[count] > tar) {
                arr[count + 1] = arr[count];
                count--;
            }
            arr[count + 1] = tar;
        }
        return arr;
    }
}


//p