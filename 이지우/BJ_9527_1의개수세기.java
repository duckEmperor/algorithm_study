package A202208;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_9527_1의개수세기 {
	static long dp[];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long A = Long.parseLong(st.nextToken())-1;
		long B = Long.parseLong(st.nextToken());
		
		int max_bit_A = 0;
		int max_bit_B = 0;
		for(int i = 0; i <= 53; i++) {
			if((A&(1L<<i)) != 0) {
				max_bit_A = i;
			}
			if((B&(1L<<i)) != 0) {
				max_bit_B = i;
			}
		}
		setDp(55);
		
		long b_sum = B&1;
		long a_sum = A&1;
		
		while(max_bit_B > 0) {
			if(B >= (1L<<max_bit_B)) {
				b_sum += dp[max_bit_B-1];
				b_sum += B-(1L<<max_bit_B)+1;
				B -= (1L<<max_bit_B);
			}
			max_bit_B--;
		}
		while(max_bit_A > 0) {
			if(A >= (1L<<max_bit_A)) {
				a_sum += dp[max_bit_A-1];
				a_sum += A-(1L<<max_bit_A)+1;
				A -= (1L<<max_bit_A); 
			}
			max_bit_A--;
		}
		System.out.println(b_sum-a_sum);
	}
	private static void setDp(int mb) {
		dp = new long[mb];
		dp[0] = 1;
		for(int i = 1; i < mb; i++) {
			dp[i] = 2*dp[i-1] + (1L<<i);
		}
	}
	

}
