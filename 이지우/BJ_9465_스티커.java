package A202209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9465_스티커 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			int N = Integer.parseInt(br.readLine());
			int[] up = new int[N];
			int[] down = new int[N];
			int[][] dp = new int[2][N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)up[i]=Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)down[i]=Integer.parseInt(st.nextToken());
			dp[0][0] = up[0];
			dp[1][0] = down[0];
			if(N==1) {
				sb.append(Math.max(dp[0][0], dp[1][0])).append("\n");
				continue;
			}
			dp[0][1] = dp[1][0] + up[1];
			dp[1][1] = dp[0][0] + down[1];
			for(int i = 2; i < N; i++) {
				dp[0][i] = Math.max(dp[1][i-1], dp[1][i-2]) + up[i];
				dp[1][i] = Math.max(dp[0][i-1], dp[0][i-2]) + down[i];
			}
			sb.append(Math.max(dp[1][N-1], dp[0][N-1])).append("\n");
		}
		System.out.println(sb);
	}

}
