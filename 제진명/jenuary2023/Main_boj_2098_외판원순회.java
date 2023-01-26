package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_2098_외판원순회 {

    static int N, statusFullBit, INF = 987654321;
    static int [][] cost, dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        statusFullBit = (1 << N) - 1;

        cost = new int[N][N];
        dp = new int[N][statusFullBit];

        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(dp[i], -1);
        }
        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0 ; j < N ; j++) {
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(tsp(0, 1));
    }

    static int tsp (int x, int check) {
        if (check == statusFullBit) {
            return cost[x][0] == 0 ? INF : cost[x][0];
        }

        if (dp[x][check] != -1) return dp[x][check];

        dp[x][check] = INF;

        for (int i = 0 ; i < N ; i++) {
            int next = check | (1 << i);

            if (cost[x][i] == 0 || (check & (1 << i)) != 0) continue;

            dp[x][check] = Math.min(dp[x][check], tsp(i, next) + cost[x][i]);
        }

        return dp[x][check];
    }
}
