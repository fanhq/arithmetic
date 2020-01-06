package com.fanhq.example.problem;

import java.util.Scanner;

/**
 * @author fanhaiqiu
 * @date 2020/1/6
 */
public class CalculateStringLength {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        if (input == null) {
            return;
        }
        String[] array = input.split(" ");
        System.out.println(array[array.length -1].length());
    }
}
