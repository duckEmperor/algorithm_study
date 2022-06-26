package A202206;

import java.util.Scanner;

public class BJ_1003_피보나치함수 {
	static int[][] memo = new int[41][2];
	public static void main(String[] args) throws Exception{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		memo[0][0] = 1;
		memo[1][1] = 1;
		for(int t = 0; t < T; t++) {
			int num = sc.nextInt();
			dp(num);
			System.out.printf("%d %d\n", memo[num][0], memo[num][1]);
		}
		sc.close();
	}
	private static int[] dp(int num) {
		if(memo[num][0] != 0 || memo[num][1] !=0) {
			return memo[num];
		}
		
		int tmp[] = dp(num-1);
		int tmp2[] = dp(num-2);
		memo[num][0] = tmp[0] + tmp2[0];
		memo[num][1] = tmp[1] + tmp2[1];
		return memo[num];
	}
}
