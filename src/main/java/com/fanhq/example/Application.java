package com.fanhq.example;

import com.fanhq.example.leetcode.LeetCode;
import com.fanhq.example.leetcode.ListNode;

/**
 * Hello world!
 */
public class Application {

    public static void main(String[] args) throws Exception {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        LeetCode leetCode = new LeetCode();
        ListNode l = leetCode.addTwoNumbers(l1, l2);
        assert l != null;

    }

}


