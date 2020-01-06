package com.fanhq.example.problem;

import java.util.Scanner;

/**
 * @author fanhaiqiu
 * @date 2020/1/6
 */
public class SplitString8Fmt {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            split(s);
        }
    }

    public static void split(String s) {
        while (s.length() >= 8) {
            System.out.println(s.substring(0, 8));
            s = s.substring(8);
        }
        if (s.length() < 8 && s.length() > 0) {
            s = s + "00000000";
            System.out.println(s.substring(0, 8));
        }
    }
}
