package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_17626_FourSquares {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[]dp = new int[N+1];
		for(int i = 1; i <=N; i++) {
			dp[i] = 5;
			for(int j = 1; j*j <= i; j++) {
				dp[i] = Math.min(dp[i], dp[i-j*j]+1);
			}
		}
		System.out.println(dp[N]);
	}

}
