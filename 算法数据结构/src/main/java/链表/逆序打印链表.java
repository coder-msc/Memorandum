package 链表;

import java.util.ArrayList;
import java.util.List;

public class 逆序打印链表  {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next=new ListNode(4);
        head.next.next=new ListNode(3);
        List<Integer> integers = printListNode(head);
        System.out.print(integers.toString());
        ListNode listNode = reverseOrderListNode(head);
        System.out.print(printListNode(listNode).toString());
    }

    /**单链表逆序方法*/
    public static ListNode reverseOrderListNode(ListNode head){
        if(head==null) {
            return null;
        }
        // 1  4  3  ====>  3   4   1
        ListNode p = new ListNode(0);
        while(head!=null){
            ListNode tmp = head.next;// 4   3
            head.next = p.next; //
            p.next = head;// null
            head = tmp;
        }
        return p.next;
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
}

class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}