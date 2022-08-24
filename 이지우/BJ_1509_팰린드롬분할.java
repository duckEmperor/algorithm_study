package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_1509_팰린드롬분할 {
	static String str;
	static int len;
	static boolean[][] palin;
	static int[] dp;
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        len = str.length();
        palin = new boolean[len+1][len+1];
        dp = new int[len+1];
        for (int i = 1; i <= len; i++) {
			dp[i] = Integer.MAX_VALUE;
		}
        setpalin();
        minCnt();
        System.out.println(dp[len]);
	}
	
	private static void setpalin() {
		init();
		int size = 3;
		while(size <= len) {
			for(int start = 1; start + size - 2 < len; start++) {
				if(str.charAt(start-1) == str.charAt(start + size - 2)) {
					if(palin[start+1][start + size - 2]) palin[start][start + size - 1] = true;
				}
			}
			size++;
		}
	}

	public static void minCnt() {
		for(int i = 1; i <= len; i++) { //끝점
			for(int j = 1; j <= i; j++) { //시작점
				if(palin[j][i]) { //해당이 팰린드롬이라면
					dp[i] = Math.min(dp[j-1] + 1, dp[i]);
				}
			}
		}
	}

	private static void init() {
		for(int i = 1; i <= len; i++) {
			palin[i][i] = true;
			if(i != len && str.charAt(i-1) == str.charAt(i)) palin[i][i+1] = true;
		}
	}
	
}





















