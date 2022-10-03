package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17404_RGB2 {
	static final int INF = 1000000;
	public static void main(String[] args) throws Exception {
		int answer = INF;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][]dp = new int[N][3];
		int[][]v = new int[N][3];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 3; j++) {
				v[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int k = 0; k < 3; k++) {
			for(int i = 0; i < 3; i++) {
				if(k==i)dp[0][i] = v[0][i];
				else dp[0][i] = INF;
			}
			for(int i = 1; i < N; i++) {
				dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + v[i][0];
				dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + v[i][1];
				dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + v[i][2];
			}
			for(int i = 0; i < 3; i++) {
				if(i!=k)answer = Math.min(dp[N-1][i], answer);
			}
		}
		System.out.println(answer);
	}

}













