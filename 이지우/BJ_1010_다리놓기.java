package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1010_다리놓기 {

	public static void main(String[] args)  throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int[][]dp = new int[30][30];
		dp[1][0] = dp[1][1] = 1;
		
		for(int i = 2; i < 30; i++) {
			dp[i][0]=dp[i][i]=1;
			for(int j = 1; j <i; j++) {
				dp[i][j]=dp[i-1][j-1]+dp[i-1][j];
			}
		}
		
		int N = Integer.parseInt(br.readLine());
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(dp[b][a]+"\n");
		}
		System.out.println(sb);
	}

}
