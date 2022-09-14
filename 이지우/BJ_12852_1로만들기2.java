package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_12852_1로만들기2 {
	static int[][] memo;
	static int n;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		memo = new int[n+1][2];
		
		dfs(n, 0);
		
		sb.append(memo[1][1] + "\n");
		makeArr(1);
		
		System.out.println(sb);
	}
	private static void makeArr(int a) {
		if(a < n)makeArr(memo[a][0]);
		sb.append(a + " ");
	}
	private static void dfs(int x, int cnt) {
		goNext(x, x/3, x%3, cnt);
		goNext(x, x/2, x%2, cnt);
	}
	
	private static void goNext(int b, int x, int mod, int cnt) {
		if(x < 1 || ((memo[x][0] != 0 && cnt+mod+1 >= memo[x][1])))return;
		memo[x][0] = b-mod;
		memo[x][1] = cnt+mod+1;
		while(mod > 0) {
			memo[b - mod][0] = b-mod+1;
			memo[b - mod][1] = cnt+mod;
			mod--;
		}
		dfs(x, memo[x][1]);
	}

}
