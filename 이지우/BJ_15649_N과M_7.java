package A202207;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15649_N과M_7 {
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N,M;
	static int[] values;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.valueOf(st.nextToken());
		M = Integer.valueOf(st.nextToken());
		st = new StringTokenizer(br.readLine());
		values = new int[N+1];
		for(int i = 1 ; i <= N ; i++) {
			values[i] = Integer.valueOf(st.nextToken());
		}
		Arrays.sort(values);
		dp(0);
		bw.flush();
		bw.close();
	}
	private static void dp(int r) throws IOException {
		if(r >= M) {
			sb.append("\n");
			bw.write(sb.toString());
			return;
		}
		for(int i = 1; i <= N; i++) {
			int size = sb.length();
			sb.append(values[i] + " ");
			dp(r+1);
			sb.delete(size, sb.length());
		}
	}
}