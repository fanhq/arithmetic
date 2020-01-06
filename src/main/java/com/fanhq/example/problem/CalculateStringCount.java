package com.fanhq.example.problem;

import java.util.Scanner;

/**
 * @author fanhaiqiu
 * @date 2020/1/6
 */
public class CalculateStringCount {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String mark = scanner.next();
        int count = 0;
        if (input == null || mark == null) {
            System.out.println(count);
            return;
        }
        int length = input.length();
        for (int i = 0; i < length; i++) {
            String val = String.valueOf(input.charAt(i));
            if (val.toLowerCase().equals(mark.toLowerCase())) {
                count++;
            }
        }
        System.out.println(count);
    }
}
