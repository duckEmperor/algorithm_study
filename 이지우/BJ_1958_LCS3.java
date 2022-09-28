package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1958_LCS3 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		char[] A = br.readLine().toCharArray();
		char[] B = br.readLine().toCharArray();
		char[] C = br.readLine().toCharArray();
		
		int al = A.length;
		int bl = B.length;
		int cl = C.length;
		
		int dp[][][] = new int[al+1][bl+1][cl+1];
		
		for(int i = 1; i <=al; i++) {
			for(int j = 1; j <= bl; j++) {
				for(int z = 1; z <= cl; z++) {
					if(A[i-1] == B[j-1] && B[j-1] == C[z-1] )dp[i][j][z] = dp[i-1][j-1][z-1]+1;
					else dp[i][j][z] = Math.max(dp[i-1][j][z], Math.max(dp[i][j-1][z], dp[i][j][z-1]));
				}
				
			}
		}
		
		System.out.println(dp[al][bl][cl]);
	}

}