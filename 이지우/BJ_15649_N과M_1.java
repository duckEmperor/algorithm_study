package A202207;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_15649_N과M_1 {
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	static boolean[] visited;
	static int[] numbers;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		visited = new boolean[N+1];
		numbers = new int[M];
		dp(0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	private static void dp(int r) {
		if(r >= M) {
			for(int a : numbers) sb.append(a + " ");
			sb.append("\n");
			return;
		}
		for(int i = 1; i <= N; i++) {
			if(visited[i])continue;
			visited[i] = true;
			numbers[r] = i;
			dp(r+1);
			visited[i] = false;
		}
	}
}