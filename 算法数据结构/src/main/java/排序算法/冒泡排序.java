package 排序算法;

public class 冒泡排序 {
    public static void main(String[] args) {
        int[] a ={3,4,2,1,-7,0};
        BubbleSort(a);
//        maopao2(a);
        print(  BubbleSort(a));

    }

    public static   void  print(int[] a){
    for (int i =0;i<a.length;i++) {
        System.out.print(a[i]+" ");
    }
    System.out.println();
}
    public static int[]  BubbleSort(int[] arr){
        int flag=0;
        for(int i=0;i<arr.length-1 ;i++){
            for(int j=0;j<arr.length-1-i;j++){
                if(arr[j]>arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag++;
                }
            }
            if(flag==0){
                break;
            }
        }
        return  arr;
    }
}
