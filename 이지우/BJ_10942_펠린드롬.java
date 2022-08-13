package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10942_펠린드롬 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		int []num = new int[N+1];
		for(int i = 1; i <= N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		int [][] dp = new int[N+1][N+1];
		for(int i = 1; i < N; i++) {
			dp[i][i] = 1;
			if(num[i] == num[i+1])dp[i][i+1] = 1;
		}
		dp[N][N] = 1;
		
		for(int k = 2; k < N; k++) {
			for(int i = 1; i <= N-k; i++) {
				if(dp[i+1][i+k-1] == 1 && num[i] == num[i+k])dp[i][i+k]=1;
			}
		}
		
		int T = Integer.parseInt(br.readLine());
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			sb.append(dp[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())]).append("\n");
		}
		System.out.println(sb.toString());
	}
}














