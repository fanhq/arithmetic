package com.fanhq.example.problem;

import java.util.*;

/**
 * @author fanhaiqiu
 * @date 2020/1/6
 */
public class RandomSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNext()){
            int count = scanner.nextInt();
            List<Integer> array = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                int val = scanner.nextInt();
                if (array.contains(val)){
                    continue;
                }
                array.add(val);
            }
            array.sort(new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    if (o1 > o2) {
                        return 1;
                    }
                    return -1;
                }
            });
            array.forEach(x->{
                System.out.println(x);
            });
        }
    }
}
