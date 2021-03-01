package 排序算法;

/*
 * 不稳定  最好：n**2  平均：n**2 最坏：n**2
 * */
public class 选择排序 {
    public static void main(String[] args) {
        int[] a = {3, 4, 2, 1, -7, 0};
        selectSortTest(a);

        for (int i =0;i<a.length;i++) {
            System.out.print(a[i]+" ");
        }
    }

    public static int[] selectSort(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int min = i; //找到最小的下标
            for (int j = i + 1; j < arr.length; j++) { //自从i  之前的 肯定都是有序的  下一次找最小的值 就从 i之后开始查找
                min = arr[j] > arr[min] ? min : j;
            }
            // 前面的值和最小的下标做交换
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
        return arr;
    }


    public static int[] selectSortTest(int[] arr) {
        for(int i=0;i<arr.length;i++){
            int min=i;
            for(int j=i+1;j<arr.length;j++){
                min=arr[min]>arr[j]?j:min;
            }

            //最小的和前面的交换
            int tem=arr[min];
            arr[min]=arr[i];
            arr[i]=tem;
        }
        return arr;
    }
}
