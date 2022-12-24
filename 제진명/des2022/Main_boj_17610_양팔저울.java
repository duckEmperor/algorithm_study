package 제진명.des2022;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_boj_17610_양팔저울 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());

        int [] weights = new int[N];
        int sum = 0;

        for (int i = 0 ; i < N ; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            sum += weights[i];
        }

        boolean [][] dp = new boolean [N][sum + 1];

        dp[0][0] = true;
        dp[0][weights[0]] = true;

        for (int i = 1 ; i < N ; i++) {
            for (int j = 0 ; j < dp[i].length ; j++) {
                if (dp[i-1][j]) {
                    dp[i][j] = true;
                    dp[i][Math.abs(j-weights[i])] = true;
                    dp[i][j+weights[i]] = true;
                }
            }
        }

        int answer = 0;

        for (int i = 1 ; i <= sum ; i++) {
            if (!dp[N-1][i]) answer++;
        }

        System.out.println(answer);
    }

}
