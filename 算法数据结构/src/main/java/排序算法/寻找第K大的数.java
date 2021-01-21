package 排序算法;
/**
 * 有一个整数数组，请你根据快速排序的思路，找出数组中第K大的数。
 *
 * 给定一个整数数组a,同时给定它的大小n和要找的K(K在1到n之间)，请返回第K大的数，保证答案存在。*/
public class 寻找第K大的数 {
    public static void main(String[] args) {
//        int[] a={1332802,1177178,1514891,871248,753214,123866,1615405,328656,1540395,968891,1884022,252932,1034406,1455178,821713,486232,860175,1896237,852300,566715,1285209,1845742,883142,259266,520911,1844960,218188,1528217,332380,261485,1111670,16920,1249664,1199799,1959818,1546744,1904944,51047,1176397,190970,48715,349690,673887,1648782,1010556,1165786,937247,986578,798663};
//        Qsort(a,0,a.length-1);
        int[] a={1,3,5,9,2};
        int m=findKth(a,a.length,3);
        System.out.print(m);

    }
    public static int findKth(int[] a, int n, int K) {
        Qsort(a,0,n-1);
        return a[n-K]; //升序 所以倒着数
    }
    public static void Qsort(int[] arr,int low,int hig){
        int l =low;
        int r=hig;
        if(l>r){return;}
        int tar=arr[l];
        while(r>l){
            while(r>l&& arr[r]>=tar){r--;}
            arr[l]=arr[r];
            while(r>l&& arr[l]<=tar){l++;}
            arr[r]=arr[l];
        }
        arr[l]=tar;
        Qsort(arr,low,l-1);
        Qsort(arr,l+1,hig);
    }
}
