package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_11049_행렬곱셈순서 {
    static class Info {
        int s, e, r;
        Info (int s, int e, int r) {
            this.s = s;
            this.e = e;
            this.r = r;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Info [] matrices = new Info[N + 1];
        Info [][] dp = new Info[N+1][N+1];

        for (int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(br.readLine());
            matrices[i] = new Info(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 0);
        }

        for (int i = 1 ; i <= N ; i++) {
            for (int j = i ; j <= N ; j++) {
                dp[i][j] = new Info(matrices[i].s , matrices[j].e , 0);
            }
        }

        for (int i = 1 ; i <= N ; i++) {
            for (int from = 1 ; from + i <= N ; from++) {
                int to = from + i;
                dp[from][to] = new Info(matrices[from].s, matrices[to].e, Integer.MAX_VALUE);
                for (int k = from ; k < to ; k++) {
                    dp[from][to].r = Math.min(dp[from][to].r, dp[from][k].r + dp[k + 1][to].r + dp[from][k].s * dp[from][k].e * dp[k + 1][to].e);
                }
            }
        }

        System.out.print(dp[1][N].r);

    }
}
