package com.fanhq.example.problem;

/**
 * @author fanhaiqiu
 * @date 2020/1/7
 */
public class ShortJobFirst {

    public static void main(String[] args) {
        int[] requestTime = {0, 2, 4, 5};
        int[] durationTime = {7, 4, 1, 4};
        System.out.println("平均等待时间：" + minAverageWaitTime(requestTime, durationTime));

    }

    public static float minAverageWaitTime(int[] requestTime, int[] durationTime) {

        int length = requestTime.length;
        //服务时间 即是持续时间
        int[] serviceTime = new int[length];
        for (int i = 0; i < length; i++) {
            serviceTime[i] = durationTime[i];
        }
        //任务号
        int[] task = new int[length];
        for (int i = 0; i < length; i++) {
            task[i] = i + 1;
        }
        //等待时间
        int[] waitTime = new int[length];
        //开始时间
        int[] startTime = new int[length];
        //完成时间
        int[] finishTime = new int[length];
        //周转时间
        int[] turnTime = new int[length];
        //带权周转时间
        float[] rightTurnTime = new float[length];

        startTime[0] = requestTime[0];
        finishTime[0] = startTime[0] + durationTime[0];
        waitTime[0] = startTime[0] - requestTime[0];
        turnTime[0] = finishTime[0] - requestTime[0];
        rightTurnTime[0] = (float) turnTime[0] / durationTime[0];
        int minIndex = 0;
        int lastIndex = 0;
        //得到任务调动的顺序
        int[] durations = getMin(requestTime);
        for (int i = 1; i < length; i++) {
            minIndex = durations[i - 1] + 1;
            //开始时间 = task == 0 ? 0 : 上个优选任务的完成时间
            startTime[minIndex] = finishTime[lastIndex];
            //完成时间 = 开始时间 + 服务时间
            finishTime[minIndex] = startTime[minIndex] + durationTime[minIndex];
            //等待时间 = 开始时间 - 提交时间
            waitTime[minIndex] = startTime[minIndex] - requestTime[minIndex];
            //周转时间 = 完成时间 - 提交时间
            turnTime[minIndex] = finishTime[minIndex] - requestTime[minIndex];
            //带权周转时间 = 周转时间 / 服务时间
            rightTurnTime[minIndex] = (float) turnTime[minIndex] / durationTime[minIndex];
            //将当前索引记为上一个任务索引
            lastIndex = minIndex;
        }

        int add = 0;
        float result;
        for (int i = 0; i < length; i++) {
            add += waitTime[i];
        }
        //求平均等待时间
        result = (float) add / length;
        return result;
    }

    /**
     * 得到任务调动的顺序
     *
     * @param durationTime
     * @return
     */
    private static int[] getMin(int[] durationTime) {
        int length = durationTime.length;
        //去除第一个任务，剩下的任务的服务时间
        int[] arr = new int[length - 1];
        //存放剩下任务的开始顺序索引
        int[] arr1 = new int[length - 1];
        //存放剩下任务的开始顺序值
        int[] arr2 = new int[length - 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = durationTime[i + 1];
        }
        int minIndex = 0;
        //趟数
        for (int i = 0; i < arr.length; i++) {
            //冒泡比较法，但是不交换位置
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            arr1[i] = minIndex;
            arr2[i] = arr[minIndex];
            arr[minIndex] = Integer.MAX_VALUE;
        }
        return arr1;

    }
}
