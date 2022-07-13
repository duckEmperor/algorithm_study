package A202207;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2637_장남감조립 {
	static int[] remain_need;
	static int[][] graph;
	static int[][] dp;
	static int N;
	static Queue<Integer> que = new LinkedList<>();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		remain_need = new int[N+1];
		graph = new int[N+1][N+1];
		dp = new int[N+1][N+1];
		int M = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			remain_need[a]++;
			graph[a][b] = c;
		}
		initToy();
		makeToy();
		for(int i = 1; i <= N; i++) {
			if(dp[N][i] != 0) {
				sb.append(i + " " + dp[N][i] + "\n");
			}
		}
		System.out.println(sb.toString());
	}
	private static void initToy() {
		for(int i = 1; i <= N; i++) {
			if(remain_need[i] == 0) {
				remain_need[i]--;
				dp[i][i] = 1;
				que.add(i);
			}
		}
	}
	private static void makeToy() {
		while(!que.isEmpty()) {
			int now = que.poll();
			for(int i = 1; i <= N; i++) {
				if(graph[i][now] != 0) {
					remain_need[i]--;
					for(int j = 1; j <=N; j++) {
						dp[i][j] += (dp[now][j] * graph[i][now]);
					}
				}
			}
			findCanMake();
		}
		
	}
	private static void findCanMake() {
		for(int i = 1; i <= N; i++) {
			if(remain_need[i] == 0) {
				remain_need[i]--;
				que.add(i);
			}
		}
		
	}

}
