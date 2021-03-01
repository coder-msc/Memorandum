package 贪心;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 一块金条 三个人分  【10，20，30】 表示每个人分的份数  总长度为 求和 60
 * 每切割一次 需要花费长度个硬币
 * 60先分成10和50  需要花费60个硬币   50在分成30和20  需要50个硬币 总共100硬币
 * 60先分成30和30 花费60硬币         30分成10和20 需要30个硬币  总共90硬币
 * */
public class 分黄金 { //这个贪心策略靠经验  但是不需要纠结证明

    public static void main(String[] args) {
        int[] arr = {10, 20, 30};
        int i = lessMoney(arr);
        System.out.println(i);

    }

    public static int lessMoney(int[] arr) {
        PriorityQueue<Integer> pQ = new PriorityQueue<Integer>(new MM());
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]); //放入优先队列中
        }

        int sum = 0;
        int cur = 0;
        while (pQ.size() > 1) {
            cur = pQ.poll() + pQ.poll();
            sum += cur;
            pQ.add(cur);
        }

        return sum;
    }

    public static class MM implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }


}