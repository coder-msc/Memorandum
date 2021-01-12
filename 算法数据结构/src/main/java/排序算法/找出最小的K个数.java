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
        int [] arr = {9,4,15,7,6,8,87,5,12,34};
//        ArrayList<Integer> arrayList = firstSortK(arr, 3);
        Solution solution = new Solution();
//        System.out.print(arrayList.toString());

        ArrayList<Integer> integers = solution.GetLeastNumbers_Solution(arr, 3);
        System.out.print(integers.toString());

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
    public static class Solution {
        public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
            ArrayList<Integer> list = new ArrayList<>();
            if (input == null || input.length == 0 || k > input.length || k == 0)
                return list;
            int[] arr = new int[k + 1];//数组下标0的位置作为哨兵，不存储数据
            //初始化数组
            for (int i = 1; i < k + 1; i++)
                arr[i] = input[i - 1];
            buildMaxHeap(arr, k + 1);//构造大根堆
            for (int i = k; i < input.length; i++) {
                if (input[i] < arr[1]) {
                    arr[1] = input[i];
                    adjustDown(arr, 1, k + 1);//将改变了根节点的二叉树继续调整为大根堆
                }
            }
            for (int i = 1; i < arr.length; i++) {
                list.add(arr[i]);
            }
            return list;
        }
        /**
         * @Author: ZwZ
         * @Description: 构造大根堆
         * @Param: [arr, length]  length:数组长度 作为是否跳出循环的条件
         * @return: void
         * @Date: 2020/1/30-22:06
         */
        public void buildMaxHeap(int[] arr, int length) {
            if (arr == null || arr.length == 0 || arr.length == 1)
                return;
            for (int i = (length - 1) / 2; i > 0; i--) {
                adjustDown(arr, i, arr.length); //将堆 调整成大根堆
            }
        }
        /**
         * @Author: ZwZ
         * @Description: 堆排序中对一个子二叉树进行堆排序
         * @Param: [arr, k, length]
         * @return:
         * @Date: 2020/1/30-21:55
         */
        public void adjustDown(int[] arr, int k, int length) {
            arr[0] = arr[k];//哨兵
            for (int i = 2 * k; i <= length; i *= 2) {
                if (i < length - 1 && arr[i] < arr[i + 1])
                    i++;//取k较大的子结点的下标
                if (i > length - 1 || arr[0] >= arr[i])
                    break;
                else {
                    arr[k] = arr[i];
                    k = i; //向下筛选
                }
            }
            arr[k] = arr[0];
        }
    }

}

