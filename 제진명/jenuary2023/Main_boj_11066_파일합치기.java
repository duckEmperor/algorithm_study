package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_11066_파일합치기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1 ; t <= T ; t++) {
            int N = Integer.parseInt(br.readLine());

            int [] sizes = new int [N+1];
            int [] sum = new int [N+1];

            st = new StringTokenizer(br.readLine());

            for (int i = 1 ; i <= N ; i++) {
                sizes[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum [i-1] + sizes[i];
            }

            int [][] dp = new int[N+1][N+1];

            for (int i = 1 ; i <= N ; i++) {
                for (int from = 1 ; from + i <= N ; from++) {
                    int to = from + i;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int k = from ; k < to ; k++) {
                        dp[from][to] = Math.min(dp[from][to], dp[from][k] + dp[k + 1][to] + sum[to] - sum[from -1]);
                    }
                }
            }

            sb.append(dp[1][N]).append("\n");

        }

        System.out.print(sb);

    }
}
