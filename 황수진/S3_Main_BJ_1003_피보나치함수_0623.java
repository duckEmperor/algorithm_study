import java.util.Scanner;

/*
 메모리 : 12996KB
 시  간 : 124ms
 */

public class S3_Main_BJ_1003_피보나치함수_0623 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder(); 
		
		int T = sc.nextInt();
		while(T-- > 0) {
			int N = sc.nextInt();
			
			int[] zero = new int[N+1];
			int[] one = new int[N+1];
			
			zero[0] = 1;
			if(N > 0)
				one[1] = 1;
			
			if(N > 1) {
				for(int i = 2; i <= N; i++) {
					zero[i] = zero[i-1] + zero[i-2];
					one[i] = one[i-1] + one[i-2];		
				}
			}
			sb.append(zero[N] + " " + one[N] + "\n");
		}
		sb.setLength(sb.length() - 1);
		
		System.out.println(sb.toString());
		sc.close();
	}
}
