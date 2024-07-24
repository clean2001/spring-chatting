package com.study.chatting;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        dp = new int[12];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4; // 3, 1 1 1, 1 2, 2 1, =>

        int n = Integer.parseInt(br.readLine());

        for(int i=0; i<n; ++i) {
            int k = Integer.parseInt(br.readLine());
            System.out.println(makeDp(k));
        }
    }

    public static int makeDp(int cur) {
        if(cur <= 0) return 0;

        if(dp[cur] > 0) return dp[cur];

        dp[cur] = makeDp(cur-1) + makeDp(cur-2) + makeDp(cur-3);

        return dp[cur];
    }
}
