package 排序算法;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 */
public class 找出最小的K个数 {

    public static void main(String[] args) {
        Map<String, Object> stringObjectHashMap = new HashMap();
        int[] arr = {9, 4, 15, 7, 6, 8, 87, 5, 12, 34};
//        ArrayList<Integer> arrayList = firstSortK(arr, 3);
//        System.out.print(arrayList.toString());

//        ArrayList<Integer> integers = solution.GetLeastNumbers_Solution(arr, 3);
        ArrayList<Integer> integers = GetLeastNumbers_Solution1(arr, 3);
        System.out.print(integers.toString());

    }
    // 学习使用堆 并排序返回

    public static ArrayList<Integer> GetLeastNumbers_Solution1(int[] arr, int k) {
        if (arr.length < k) {
            return new ArrayList<>();
        }
        ArrayList<Integer> res =new ArrayList<>();
         //创建前K个数
        int[] a = new int[k];
        for (int i = 0; i < k; i++) {
            a[i] = arr[i];
        }

        //前K个数 维持一个大根堆
        for(int i=k/2 -1;i>=0;i--){
            BigHeap(a,i,k);
        }
        //后面len - k个数 进入堆中维持一个大顶堆
        for(int i=k;i<arr.length;i++){
            if(arr[i]<a[0]){
                a[0] = arr[i];
                BigHeap(a,0,k);
            }
        }
        //将大顶堆输出 就是堆排序
        for(int i =a.length-1; i>=0;i--){
            int tem=a[i];
            a[i] =a[0];
            a[0] =tem;
            BigHeap(a,0,i);
        }

        for(int x:a){
            res.add(x);
        }

        return res;
    }

    public static void BigHeap(int[] arr,int index ,int len){
        int temp =arr[index];
        for(int k = 2*index+1;k<len;k=2*k +1){
            if((k+1)<len && arr[k+1] > arr[k]){
                k++;
            }
            if(temp<arr[k]){
                arr[index] =arr[k];
               index=k;
            }else{
                break;
            }
        }
        arr[index] =temp;
    }

    // 1 、按照从小到大排序 选出前K个 直接暴力法
    public static ArrayList<Integer> firstSortK(int arr[], int k) {
        ArrayList<Integer> tmp = new ArrayList();
        if (arr.length < k) {
            return tmp;  //如果k 超过数组长度 直接返回空数组；
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        for (int i = 0; i < k; i++) {
            tmp.add(arr[i]);
        }
        return tmp;
    }


}

