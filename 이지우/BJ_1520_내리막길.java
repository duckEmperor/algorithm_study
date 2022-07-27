package A202207;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1520_내리막길 {
	static int N,M;
	static int[][] field, dp;
	static int[] dy = new int[]{-1,0,1,0};
	static int[] dx = new int[]{0,1,0,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		field = new int[N][M];
		dp = new int[N][M];
		
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M; j++) {
				field[i][j] = Integer.valueOf(st.nextToken());
				dp[i][j] = -1;
			}
		}
		System.out.println(dfs(0,0));
	}

	private static int dfs(int r, int c) {
		if(r==N-1 && c==M-1)return 1;
		if(dp[r][c] != -1)return dp[r][c];
		for(int d = 0; d < 4; d++) {
			int yy = r + dy[d];
			int xx = c + dx[d];
			if(yy<0||xx<0||yy>=N||xx>=M||field[r][c] <= field[yy][xx]) continue;
			dp[r][c] += dfs(yy,xx);
		}
		dp[r][c] += 1;
		return dp[r][c];
	}

}






















