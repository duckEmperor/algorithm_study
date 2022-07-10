package A202207;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class BJ_2718_타일채우기 {
	static int[][] dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<>();
		int max = -1;
		for(int i = 0; i < T; i++) {
			int a = Integer.parseInt(br.readLine());
			max = Math.max(max, a);
			arr.add(a);
		}
		dp = new int[max+2][13];
		dp[1][0] = dp[2][0] = dp[2][3] = dp[2][9] = dp[2][12] = 1;
		setDp(max+1, 0);
		for(int a : arr) {
			sb.append(dp[a+1][0] + "\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}
	private static int setDp(int lv, int type) {
		if(lv < 1)return 0;
		if(dp[lv][type] != 0) return dp[lv][type];
		if(type == 0)
			dp[lv][0] = setDp(lv-1,0) + setDp(lv-1,3) * 2 + setDp(lv-1,9) + setDp(lv-2,0);
		if(type == 3)
			dp[lv][3] = setDp(lv-1,0) + setDp(lv-1,3);
		if(type == 6)
			dp[lv][6] = setDp(lv-1,9);
		if(type == 9)
			dp[lv][9] = setDp(lv-1,0) + setDp(lv-1,6);
		
		return dp[lv][type];
	}

}
