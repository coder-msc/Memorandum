package 栈;

import java.util.Stack;

/**使用两个栈实现队列
 *
 *
 * */
public class 两个栈实现队列 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    // 入栈 直接入
    //如果你要出栈  自己出  为空了 那就找我要  我一次全部给你
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        //出
        if (stack2.empty()) {
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }


}
