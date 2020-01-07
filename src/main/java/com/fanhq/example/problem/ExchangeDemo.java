package com.fanhq.example.problem;

/**
 * @author fanhaiqiu
 * @date 2020/1/7
 */
public class ExchangeDemo {
    /*
     * 问题：换钱问题
     * 给定一个数组arraydemo，arraydemo中所有的值都为正数且不重复。
     * 每个值代表一种面值的货币，每种面值的货币可以使用任意张，再给定一个整数aim代表要找的钱数，
     * 求组成aim的最少货币数。
     *
     * 举例：
     * arraydemo=[1,5,10,50,100],aim = 53
     * 1张50元,再加上3张1元，可以组成53元；其它的找钱方案都要使用更多张的货币，所以返回4
     * arraydemo=[1,5,10,50,100],aim=0
     * 不用任何货币就可以组成0元，返回0
     * arraydemo=[5,10,50,100],aim=2
     * 根本无法组成2元，钱不能找开的情况下默认返回-1
     *
     * */

    private static int exchangeWays(int[] arraydemo, int aim) {
        // TODO Auto-generated method stub

        if (arraydemo == null || arraydemo.length == 0 || aim < 0) {
            return -1;
        }

        int n = arraydemo.length;
        int max = Integer.MAX_VALUE;
        int[][] dp = new int[n][aim + 1];
        for (int j = 1; j <= aim; j++) {
            dp[0][j] = max;
            if (j - arraydemo[0] >= 0 && dp[0][j - arraydemo[0]] != max) {
                dp[0][j] = dp[0][j - arraydemo[0]] + 1;
            }
        }

        int left = 0;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= aim; j++) {
                left = max;
                if (j - arraydemo[i] >= 0 && dp[i][j - arraydemo[i]] != max) {
                    left = dp[i][j - arraydemo[i]] + 1;
                }
                dp[i][j] = Math.min(left, dp[i - 1][j]);
            }
        }


        return dp[n - 1][aim] != max ? dp[n - 1][aim] : -1;
    }

    public static void main(String[] args) {
        int arrayDemo[] = {1, 5, 10, 50, 100};
        int aim = 57;

        int answer = exchangeWays(arrayDemo, aim);
        System.out.println("# ANSWER= " + answer);

    }
}
