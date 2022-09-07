package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9252_LCS2 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		char[] arrA = br.readLine().toCharArray();
		char[] arrB = br.readLine().toCharArray();
		int N = arrA.length;
		int M = arrB.length;
		int[][] dp = new int[M+1][N+1];
		for(int i = 1; i <= M; i++) {
			for(int j = 1; j <= N; j++) {
				if(arrA[j-1] == arrB[i-1])dp[i][j] = dp[i-1][j-1]+1;
				else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
			}
		}
		int i = M;
		int j = N;
		while(i>0 && j>0) {
			if(dp[i][j] == dp[i-1][j])i--;
			else if(dp[i][j] == dp[i][j-1])j--;
			else {
				sb.append(arrA[j-1]);
				i--;j--;
			}
		}
		System.out.println(sb.length());
		if(sb.length()>0)System.out.println(sb.reverse());
	}
}