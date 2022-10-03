package A202208;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_9461_파도반수열 {

	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        long dp[] = new long[101];
        dp[1] = 1; dp[2] = 1; dp[3] = 1; dp[4] = 2; dp[5] = 2;
        for(int i = 6; i <= 100; i++) {
        	dp[i] = dp[i-1]+dp[i-5];
        }
        int T = Integer.parseInt(br.readLine());
        while(T-->0) {
        	sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
	}

}
