package 제진명.february2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main_boj_9252_LCS2 {

    static String str1, str2;
    static int [][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        str1 = br.readLine();
        str2 = br.readLine();

        dp = new int [str2.length() + 1][str1.length() + 1];

        for (int i = 1 ; i <= str2.length() ; i++) {
            for (int j = 1 ; j <= str1.length() ; j++) {
                if (str2.charAt(i - 1) != str1.charAt(j - 1)) dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                else dp[i][j] = dp[i - 1][j - 1] + 1;
            }
        }

        int i = str2.length();
        int j = str1.length();

        Stack<Character> stack = new Stack<>();

        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i - 1][j]) i--;
            else if (dp[i][j] == dp[i][j - 1]) j--;
            else {
                stack.push(str2.charAt(i - 1));
                i--;
                j--;
            }
        }

        sb.append(dp[str2.length()][str1.length()]).append("\n");

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.print(sb);

    }
}
