package 제진명.jenuary2023;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_boj_16991_외판원순회3 {

    static int N, statusFullBit, INF = 987654321;
    static double [][] w;
    static double [][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        statusFullBit = (1 << N) - 1;

        Point [] dots = new Point[N];
        dp = new double[N][statusFullBit];

        for (int i = 0 ; i < N ; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            dots[i] = new Point(x, y);
        }

        w = new double[N][N];

        for (int i = 0 ; i < N - 1 ; i++) {
            for (int j = i + 1 ; j < N ; j++) {
                double dist = Math.sqrt(Math.pow(dots[i].x - dots[j].x, 2) + Math.pow(dots[i].y - dots[j].y, 2));

                w[i][j] = dist;
                w[j][i] = dist;
            }
        }

        System.out.println(tsp(0, 1));
    }

    static double tsp (int x, int check) {
        if (check == statusFullBit) {
            return w[x][0] == 0 ? INF : w[x][0];
        }

        if (dp[x][check] != -1) return dp[x][check];

        dp[x][check] = INF;

        for (int i = 1 ; i < N ; i++) {
            int next = check | (1 << i);

            if (w[x][i] == 0 || (check & (1 << i)) != 0) continue;

            dp[x][check] = Math.min(dp[x][check], tsp(i, next) + w[x][i]);
        }

        return dp[x][check];
    }
}
