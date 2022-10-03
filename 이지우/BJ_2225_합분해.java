package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2225_합분해 {
	static final int mod = 10_0000_0000;
	static int N, K;
	static long[][] dp;
	static long ans = 0;
	public static void main(String[] args)  throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		setDp();
		System.out.println(dp[K][N]);
	}
	private static void setDp() {
		dp = new long[K+1][N+1];
		for(int i = 1; i <=K; i++) {
			dp[i][0] = 1; 
			for(int j = 1; j <= N; j++) {
				dp[i][j] = dp[i][j-1]+dp[i-1][j]%mod;
			}
		}
		
	}
	
}














