package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_11052_카드구매하기 {
	/*
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] w = new int[N+1];
		int[] dp = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) {
			w[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i <= N; i++) {
			for(int j = i; j <= N; j++) {
				if(dp[j] < dp[j-i]+w[i])dp[j] = dp[j-i]+w[i];
			}
		}
		System.out.println(dp[N]);
	}
	*/
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()), tmp, i, j, k;
		int[] dp = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (i = 1; i <= N; ++i)
			dp[i] = Integer.parseInt(st.nextToken());
		for (i = 2; i <= N; ++i) {
			k = i >> 1;
			for(j = 1; j <= k; j++) {
				tmp = dp[j] + dp[i - j];
				if (tmp > dp[i])
					dp[i] = tmp;
			}
		}
		System.out.print(dp[N]);
	}
}
