package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_11404_플루이드 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		int[][] cost = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(cost[i], 1000_0000);
			cost[i][i] = 0;
		}
		int start, end, w;
		while(M-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			w = Integer.parseInt(st.nextToken());
			cost[start][end] = Math.min(cost[start][end], w); 
		}
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				if(i==k)continue;
				for(int j = 1; j <= N; j++) {
					if(k==j || i==j)continue;
					cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				if(cost[i][j] == 1000_0000)sb.append("0 ");
				else sb.append(cost[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}

}



























