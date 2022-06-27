package A202206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1507_궁금한민호 {
	//static int INF = 4000000;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine());
		
		int[][] minGraph = new int[N+1][N+1];
		int[][] dp = new int[N+1][N+1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				minGraph[i][j] = dp[i][j] =Integer.parseInt(st.nextToken());
			}
		}
		
		for(int k = 1; k <= N; k ++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i==k || j==k || i==j || dp[i][j] == 0)continue;
					if(minGraph[i][k] + minGraph[k][j] < minGraph[i][j]) {
						System.out.println(-1);
						return;
					}
					if(minGraph[i][k] + minGraph[k][j] == minGraph[i][j]) {
						dp[i][j] = 0;
						dp[j][i] = 0;
					}
				}
			}
		}
		
		int ans = 0;
		
		for(int i = 1; i <= N; i++) {
			for(int j = i+1; j <= N; j++) {
				ans += dp[i][j];
			}
		}
		
		System.out.println(ans);
	}

}


















