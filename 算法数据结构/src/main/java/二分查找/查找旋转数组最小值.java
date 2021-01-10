package 二分查找;

public class 查找旋转数组最小值 {
    public static void main(String[] args) {
        int [] arr = {4,5,7,1,2,3};
        //暴力法  直接遍历
//        int min=arr[2];
//        for(int i=0;i<arr.length;i++){
//            min=arr[i]<min?arr[i]:min;
//        }
        System.out.print(BinarySearch(arr));
    }


    /**二分查找法*/
    public static int BinarySearch(int[] arr){
        if(arr.length==0){
            return 0;
        }
        int l = 0;
        int r=arr.length-1;
        while(r>l){
            int m =l+(r-l)/2;
            if(arr[m]>arr[r]){ //中值比you侧大  那么最小值在 中值的右边
                l = m+1; // 4 5 7 1 2 3
            }else if(arr[m]<arr[r]){
                r=m;
            }else{ //有重复的值 那么不断往左边靠
                r--;
            }
        }
        return arr[r];
    }
}
