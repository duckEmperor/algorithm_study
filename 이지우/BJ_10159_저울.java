package A202206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10159_저울 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] dp = new int[N+1][N+1];
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st;
		while( M-->0 ) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dp[a][b] = 1; //크면 1
			dp[b][a] = 2; //작으면 2
		}
		
		for(int k = 1; k <= N; k ++) {
			for(int i = 1; i <= N; i++) {
				for(int j = 1; j <= N; j++) {
					if(i==j || dp[i][j] != 0)continue;
					if(dp[i][k] != 0 && dp[i][k] == dp[k][j]) dp[i][j] = dp[i][k];
				}
			}
		}
		
		for(int i = 1; i <= N; i++) {
			int cnt = 0;
			for(int j = 1; j <= N; j++) {
				if(i==j)continue;
				if(dp[i][j] == 0) cnt++;
			}
			System.out.println(cnt);
		}
	}

}
