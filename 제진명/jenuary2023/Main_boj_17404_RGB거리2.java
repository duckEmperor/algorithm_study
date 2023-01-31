package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_17404_RGB거리2 {

    static int N, INF = 1_000_000_000;
    static int [][] cost;
    static int [][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        cost = new int[N][3];
        dp = new int[N][3];

        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());

            int R = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            cost[i][0] = R;
            cost[i][1] = G;
            cost[i][2] = B;
        }

        int answer = INF;

        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                if (i == j) dp[0][j] = cost[0][j];
                else dp[0][j] = INF;
            }

            for (int j = 1 ; j < N ; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + cost[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + cost[j][1];
                dp[j][2] = Math.min(dp[j - 1][1], dp[j - 1][0]) + cost[j][2];
            }

            for (int j = 0 ; j < 3 ; j++) {
                if (i == j) continue;
                answer = Math.min(answer, dp[N-1][j]);
            }
        }

        System.out.println(answer);
    }
}
