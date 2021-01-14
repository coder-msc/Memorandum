package 排序算法;

public class 两个数组合并排序 {
    public static void main(String[] args) {
        int[] a = {4, 2, 6, 3, 0, 0, 0, 0, 0, 0};
        int[] b = {5, 1, 7};
        mergeArray(a, 4, b, 3);//1
//        myMerge(a, 4, b, 3); //2
        System.out.print(a.toString());
        System.out.print(a.toString());


    }

    // 符合题意 只有一半的归并
    public static int[] mergeArray(int[] a, int al, int[] b, int bl) {
        int m = al - 1;
        int n = bl - 1;
        int k = bl + al - 1;
        while (n >= 0 && m >= 0) {
            if (a[m] > b[n]) {
                a[k] = a[m];
                m--;
                k--;
            } else {
                a[k] = b[n];
                n--;
                k--;
            }
        }
        while (n >= 0) {
            a[k] = b[n];
            n--;
            k--;
        }
//        if(){}

        return a;
    }

    //完整归并
    public static int[] myMerge(int[] a, int al, int[] b, int bl) {
        int k = al + bl - 1;
        int m = al - 1;
        int n = bl - 1;
        int[] arr = new int[k + 1];

        while (m >= 0 && n >= 0) {
            if (a[m] > b[n]) {
                arr[k--] = a[m--];
            } else {
                arr[k--] = b[n--];
            }
        }
        while (m >= 0) {
            arr[k--] = a[m--];
        }

        while (n >= 0) {
            arr[k--] = b[n--];
        }
        for (int i = 0; i < al + bl; i++) {
            a[i] = arr[i];
        }
        return a;
    }
}
