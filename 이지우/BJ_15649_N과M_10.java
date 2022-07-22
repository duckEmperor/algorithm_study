package A202207;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15649_N과M_10 {
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N,M;
	static int[] values, numbers;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		st = new StringTokenizer(br.readLine());
		numbers = new int[M];
		values = new int[N+1];
		for(int i = 1 ; i <= N ; i++) {
			values[i] = Integer.valueOf(st.nextToken());
		}
		Arrays.sort(values);
		dp(0,1);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
	private static void dp(int r, int n) {
		if(r >= M) {
			for(int a : numbers)sb.append(a + " ");
			sb.append("\n");
			return;
		}
		if(n>N)return;
		if(numbers[r] == values[n]) {
			dp(r, n+1);
		}else {
			numbers[r]=values[n];
			dp(r+1, n+1);
			dp(r, n+1);
		}
		numbers[r] = 0;
	}
}