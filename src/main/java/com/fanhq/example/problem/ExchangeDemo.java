package com.fanhq.example.problem;

/**
 * @author fanhaiqiu
 * @date 2020/1/7
 */
public class ExchangeDemo {


    public static void main(String[] args) {
        int[] arr1 = new int[]{2, 3, 5};
        int aim1 = 20;
        System.out.println(minCoinChange(arr1, aim1));
        System.out.println(CoinChange(arr1, aim1));

        int[] arr2 = new int[]{1, 5, 10, 25};
        int aim2 = 15;
        System.out.println(numCoinExchange(arr2, aim2));
    }

    /**
     * 最少硬币
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int minCoinChange(int[] coins, int amount) {
        if (coins == null || coins.length <= 0 || amount < 0) {
            return -1;
        }
        int n = coins.length;
        int[][] dp = new int[n][amount + 1];
        //dp[0][j]只能是那些j为coin[0]整数倍的有值，其他的置为max_value
        for (int j = 1; j < dp[0].length; j++) {
            dp[0][j] = Integer.MAX_VALUE;
            if (j % coins[0] == 0) {
                dp[0][j] = j / coins[0];
            }
        }
        int left = Integer.MAX_VALUE;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                left = Integer.MAX_VALUE;  //注意每次要重新置位
                //dp[i][j - coins[i]] = Integer.MAX_VALUE表示无法组成该货币值
                if (j >= coins[i] && dp[i][j - coins[i]] != Integer.MAX_VALUE) {
                    left = dp[i][j - coins[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }
        return dp[n - 1][amount] != Integer.MAX_VALUE ? dp[n - 1][amount] : -1;
    }

    /**
     * 兑换硬币的路径
     *
     * @param arr
     * @param aim
     * @return
     */
    public static int numCoinExchange(int[] arr, int aim) {
        if (arr == null || arr.length <= 0) {
            return 0;
        }
        int[][] dp = new int[arr.length][aim + 1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = 1;
        }
        for (int j = 0; j < dp[0].length; j++) {
            if (j % arr[0] == 0) {
                dp[0][j] = 1;
            }
        }
        int num = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                num = dp[i - 1][j];
                num = num + (j >= arr[i] ? dp[i][j - arr[i]] : 0);
                dp[i][j] = num;
            }
        }
        return dp[arr.length - 1][aim];
    }

    /**
     * 钱币最少
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int CoinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // dp[0] 为 0，其他默认为 amount + 1（实际是不可能的），为了方便取对比结果中的最小值
        for (int i = 1; i < dp.length; i++) {
            dp[i] = amount + 1;
        }

        // 计算 1~amount 每项 dp[i] 的值
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                // 如果i能使用存在的面额来组合，得到每种面值组合后的最小值
                if (coins[j] <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        // 如果 dp[amount] 是 amount + 1 ，代表没找到组合结果，否则返回组合成 amount 需要的最少硬币数 dp[amount]
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
