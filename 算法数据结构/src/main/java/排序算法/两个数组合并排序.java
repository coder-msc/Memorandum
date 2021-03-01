package 排序算法;

public class 两个数组合并排序 {
    public static void main(String[] args) {
        int[] a = {1,5,7,8,9,17,20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] b = {0,2,4,6,7,17,18,23,25};
//        mergeArray(a, 7, b, 9);//1
        myMerge(a, 7, b, 9); //2
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
