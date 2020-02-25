package com.fanhq.example.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author fanhaiqiu
 * @date 2020/1/21
 */
public class LeetCode {


    /**
     * 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        StringBuilder sb1 = new StringBuilder();
        while (l1 != null) {
            sb1.append(l1.val);
            l1 = l1.next;
        }
        StringBuilder sb2 = new StringBuilder();
        while (l2 != null) {
            sb2.append(l2.val);
            l2 = l2.next;
        }
        String str1 = sb1.reverse().toString();
        String str2 = sb2.reverse().toString();
        int len1 = str1.length();
        int len2 = str2.length();
        int count = 0;
        int max = Math.max(len1, len2);
        boolean flag = false;
        ListNode l3 = null;
        ListNode last = null;
        while (count < max) {
            int val1 = 0;
            int val2 = 0;
            if (count < len1) {
                val1 = Integer.valueOf(String.valueOf(str1.charAt(len1 - count - 1)));
            }
            if (count < len2) {
                val2 = Integer.valueOf(String.valueOf(str2.charAt(len2 - count - 1)));
            }
            int val3 = val1 + val2;
            if (flag) {
                val3++;
            }
            int val = 0;
            if (val3 >= 10) {
                val = val3 % 10;
                flag = true;
            } else {
                flag = false;
                val = val3;
            }
            if (l3 == null) {
                l3 = new ListNode(val);
                last = l3;
            } else {
                ListNode next = new ListNode(val);
                last.next = next;
                last = next;
            }
            count++;
        }
        if (flag) {
            ListNode next = new ListNode(1);
            last.next = next;
            last = next;
        }
        return l3;
    }

    /**
     * 无重复字符的最长子串
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Map<String, Integer> temp = new HashMap<>();
        int length = s.length();
        int max = 0;
        int index = 0;
        while (index < length){
            String val = String.valueOf(s.charAt(index));
            if (temp.containsKey(val)) {
                if (max < temp.size()){
                    max = temp.size();
                }
                index = temp.get(val);
                temp.clear();
            }else {
                temp.put(val, index);
            }
            index ++;
        }
        if (max < temp.size()) {
            max = temp.size();
        }
        return max;
    }
}
