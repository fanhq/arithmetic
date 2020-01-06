package com.fanhq.example;

import java.util.Scanner;

/**
 * Hello world!
 */
public class Application {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            String s = input.nextLine();
            System.out.println(s);
        }
    }
}
