package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_2482_색상환 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        int [][] dp = new int[N+1][N+1];

        dp[1][1] = 1;
        dp[1][0] = 1;
        dp[2][1] = 2;
        dp[2][0] = 1;

        for (int i = 3 ; i <= N ; i++) {
            dp[i][1] = i;
            dp[i][0] = 1;
            for (int k = 2 ; k <= (i + 1) / 2 ; k++) {
                dp[i][k] = (dp[i-1][k] + dp[i-2][k-1]) % 1_000_000_003;
            }
        }

        System.out.println((dp[N-3][K-1] + dp[N-1][K]) % 1_000_000_003);

    }
}
