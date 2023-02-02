package 제진명.jenuary2023;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_boj_14002_가장긴증가하는부분수열4 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int [] seq = new int[N];
        int [] dp = new int[N];
        int len = 1;

        for (int i = 0 ; i < N ; i++) {
            seq[i] = Integer.parseInt(st.nextToken());

            dp[i] = 1;
            for (int j = i - 1 ; j >= 0 ; j--) {
                if (seq[i] > seq[j]) dp[i] = Math.max(dp[i], dp[j]+1);
                len = Math.max(len, dp[i]);
            }
        }

        sb.append(len).append("\n");
        Stack<Integer> stack = new Stack<>();
        for (int i = N - 1 ; i >= 0 ; i--) {
            if (dp[i] == len) {
                stack.push(seq[i]);
                len--;
            }

            if (len == 0) break;
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }

        System.out.print(sb);
    }
}
