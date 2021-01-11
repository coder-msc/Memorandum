package 排序算法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 输入n个整数，找出其中最小的K个数。例如输入4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4。
 * */
public class 找出最小的K个数 {

    public static void main(String[] args) {
        Map<String, Object> stringObjectHashMap = new HashMap();
        int [] arr = {4,5,7,1,2,3};
        ArrayList<Integer> arrayList = firstSortK(arr, 3);
        System.out.print(arrayList.toString());
    }

    // 1 、按照从小到大排序 选出前K个 直接暴力法
    public static ArrayList<Integer > firstSortK(int arr[] , int k ){
        ArrayList<Integer> tmp =new ArrayList();
        if(arr.length<k){
            return tmp;  //如果k 超过数组长度 直接返回空数组；
        }
        for(int i =0;i<arr.length;i++){
            for(int j= i ;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    int temp =arr[i];
                    arr[i] =arr[j];
                    arr[j]=temp;
                }
            }
        }
        for(int i = 0;i<k ;i++){
            tmp.add(arr[i]);
        }
        return  tmp;
    }

    //2 、 拿到Offer解法  使用堆排序


}

