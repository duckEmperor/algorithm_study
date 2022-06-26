package A202206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2098_외판원순회 {
	static int infinity = 987654321, N;
	static int[][] graph, dp;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st  = null;
		N = Integer.parseInt(br.readLine());
		graph = new int[N][N];
		dp = new int[N][(1<<N)-1];
		
		for(int i = 0 ; i < N; i++) {
			st  = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], infinity);
			for(int j = 0 ; j < N; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());;
			}
		}
		
		System.out.println(dfs(1,0));
	}

	private static int dfs(int masking, int now) {
		if(masking == (1<<N)-1) { //모두 돌았으면
			if(graph[now][0] == 0) {
				return infinity-1000001;
			}
			return graph[now][0];
		}
		if(dp[now][masking] != infinity) {	// dp값이 존재하는경우
			return dp[now][masking];
		}
		
		
		// TODO Auto-generated method stub
		for(int i = 0 ; i < N; i++) {
			if(graph[now][i] == 0 ||(masking & (1<<i)) != 0) continue; //같은 위치거나 못가는것이거나 이미 갔던 곳이면 볼필요 없다.
			dp[now][masking] = Math.min(dp[now][masking], dfs(masking | (1 << i), i) + graph[now][i]);
		}
		return dp[now][masking];
	}

}
