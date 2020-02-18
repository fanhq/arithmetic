package com.fanhq.example.problem;

/**
 * @author fanhaiqiu
 * @date 2020/1/15
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,6,3};
        array = bubbleSort(array);
        assert array == null;
    }

    public static int[] bubbleSort(int[] array) {
        if (array != null || array.length > 0) {
            int len = array.length;
            int temp;
            int index;
            for (int i = 0; i < len; i++) {
                temp = array[i];
                index = i;
                for (int j = i + 1; j < len; j++) {
                    if (temp > array[j]) {
                        temp = array[j];
                        index = j;
                    }
                }
                if (index != i) {
                    array[index] = array[i];
                    array[i] = temp;
                }
            }
        }
        return array;
    }
}
