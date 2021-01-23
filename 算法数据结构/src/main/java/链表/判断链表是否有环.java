package 链表;

import java.util.ArrayList;
import java.util.List;

public class 判断链表是否有环 {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next=new ListNode(4);
        head.next.next=new ListNode(3);
        System.out.print(isLoopNodeList(head));
    }
    /**打印链表公共方法*/
    public static List<Integer> printListNode(ListNode head){
        List list = new ArrayList<Integer>();
        while(head!=null){
            list.add(head.val);
            head=head.next;
        }
        return list;
    }
    public static boolean isLoopNodeList(ListNode head){
        if(head.next==null){
            return false;
        }
        ListNode fast=head.next.next;
        ListNode slow=head.next;
        while(slow!=null && fast!=null){
            if(fast.val==slow.val){
                return true;
            }
            if(fast.next==null){ //防止快指针 的next为空 那么空的next就报错了
                return false;
            }
            slow=slow.next;
            fast=fast.next.next;

        }
        return false;
    }

    //更简洁的做法
public static boolean isLoopNodelist(ListNode head){
        ListNode fast=head;
        ListNode slow=head;
        while(fast!=null&& fast.next!=null){
            fast=fast.next.next;
            slow=slow.next;
            if(fast!=null&&fast.val==slow.val){
                return true;
            }
        }
        return false;
}
}
