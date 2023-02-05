package 제진명.february2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_boj_1958_LCS3 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char [] s1 = br.readLine().toCharArray();
        char [] s2 = br.readLine().toCharArray();
        char [] s3 = br.readLine().toCharArray();

        int [][][] dp = new int[s3.length + 1][s2.length + 1][s1.length + 1];

        for (int i = 1 ; i <= s3.length ; i++) {
            for (int j = 1 ; j <= s2.length ; j++) {
                for (int k = 1 ; k <= s1.length ; k++) {
                    if (s3[i - 1] == s2[j - 1] && s2[j - 1] == s1[k - 1]) {
                        dp[i][j][k] = dp[i - 1][j - 1][k - 1] + 1;
                    } else {
                        dp[i][j][k] = Math.max(dp[i][j][k - 1], Math.max(dp[i - 1][j][k], dp[i][j - 1][k]));
                    }
                }
            }
        }

        System.out.println(dp[s3.length][s2.length][s1.length]);
    }
}
