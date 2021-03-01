package 贪心;

import java.util.Comparator;
import java.util.PriorityQueue;

/*
 * 项目有成本 和利润 两个属性
 * 一个项目只能做一次  同一时间只能做一个项目  给定初始资金
 * 要求达到最大收益
 * 得到结束任务后的资金是多少 （本金+利润） w+p
 *
 * */
public class 项目最大收益 {  //这个贪心策略靠经验  但是不需要纠结证明

    public static class Node {
        public int p;  // 利润
        public int c;  //成本

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    public static class MyMinCompatator implements Comparator<Node> { //定义小根堆的优先队列 存所有的项目 维护小根堆

        @Override
        public int compare(Node o1, Node o2) {
            return o1.c - o2.c;
        }
    }

    public static class MyMaxCompatator implements Comparator<Node> { //定义大根堆的优先队列   存能做的项目 维护大根堆
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }

    public static int getMaxProfit(int k, int w, int[] Profits, int[] Capital) {
        Node[] nodes = new Node[Profits.length];
        for (int i = 0; i < Profits.length; i++) {
            nodes[i] = new Node(Profits[i], Capital[i]);
        }

        PriorityQueue<Node> maxProfitQ = new PriorityQueue<>(new MyMaxCompatator());
        PriorityQueue<Node> minCostsQ = new PriorityQueue<>(new MyMinCompatator());
        for (int i = 0; i < nodes.length; i++) {
            minCostsQ.add(nodes[i]); //最小成本 项目放入 小根堆
        }

        for (int i = 0; i < k; i++) { //最多支持做k个项目
            while (!minCostsQ.isEmpty() && minCostsQ.peek().c < w) {
                maxProfitQ.add(nodes[i]);
            }
            if (maxProfitQ.isEmpty()) { //如果项目可做了  那就退出
                return w;
            }

            w += maxProfitQ.poll().p; //取出 最大收益的项目来做
        }
        return w;
    }
}


