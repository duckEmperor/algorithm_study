package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2166_다각형의면적 {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.valueOf(br.readLine());
		long[] x = new long[N+1];
		long[] y = new long[N+1];
		StringTokenizer st = null;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x[i] = Integer.valueOf(st.nextToken());
			y[i] = Integer.valueOf(st.nextToken());
		}
		x[N] = x[0];
		y[N] = y[0];
		long sumA = 0;
		long sumB = 0;
		for(int i = 0; i < N; i++) {
			sumA += x[i]*y[i+1];
			sumB += y[i]*x[i+1];
		}
		System.out.println(String.format("%.1f", Math.abs(sumA - sumB) / 2.0));
	}

}
