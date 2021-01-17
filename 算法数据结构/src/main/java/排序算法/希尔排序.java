package 排序算法;

public class 希尔排序 {
    public static void main(String[] args) {
        int[] a = {3, 4, 2, 1, -7, 0};
//        ShellSort(a);
        ShellSortMove(a);
        for(int a1:a){
            System.out.print(a1);

        }
        System.out.print(a);

    }
    public static int[] ShellSort(int[] arr){
        for(int jap=arr.length/2;jap>0;jap/=2){
            for (int i=jap;i<arr.length;i++){
                int x=i;
                while(x-jap>=0 && arr[x]<arr[x-jap]){
                    int tmp=arr[x];
                    arr[x] =arr[x-jap];
                    arr[x-jap]=tmp;
                    x=x-jap;
                }
            }
        }
        return  arr;
    }

    //基于移动的希尔排序 int[] a = {3, 4, 2, 1, -7, 0};
    public static int[] ShellSortMove(int[] arr){
        for(int jap=arr.length/2;jap>0;jap/=2){
            for(int i=jap;i<arr.length;i++){
                int x=i;
                int tmp =arr[x];
                while(x-jap>=0 && tmp<arr[x-jap]){
                    arr[x] =arr[x-jap];
                    x=x-jap;
                }
                arr[x] =tmp;
            }
        }
        return arr;
    }
}
