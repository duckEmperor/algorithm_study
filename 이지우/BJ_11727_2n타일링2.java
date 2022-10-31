package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_11727_2n타일링2 {

	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		if(N==1) {
			System.out.println(1);return;
		}
		int mod = 10007;
		int[]dp = new int[N+1];
		dp[1]=1;
		dp[2]=3;
		for(int i = 3; i <=N; i++) {//5만
			dp[i] = dp[i-1]+dp[i-2]*2;
			dp[i] %= mod;
		}
		System.out.println(dp[N]);

	}

}
