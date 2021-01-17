package 排序算法;

public class 选择排序 {
    public static void main(String[] args) {
        int[] a = {3, 4, 2, 1, -7, 0};
        selectSort(a);
        System.out.print(a);
    }

    public static int[] selectSort(int arr[]) {
        for(int i=0;i<arr.length;i++){
            int min =i; //找到最小的下标
            for (int j=i+1;j<arr.length;j++){ //自从i  之前的 肯定都是有序的  下一次找最小的值 就从 i之后开始查找
                min=arr[j]>arr[min]?min:j;
            }
            // 前面的值和最小的下标做交换
            int tmp=arr[i];
            arr[i]=arr[min];
            arr[min]=tmp;
    }
        return arr;
    }
}
