package A202207;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10775_공항 {
	static int [] parents;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int G = Integer.parseInt(br.readLine());
		int T = Integer.parseInt(br.readLine());
		parents = new int[G+1];
		for(int i = 1 ; i <= G; i++) {
			parents[i] = i;
		}
		int ans = 0;
		while(T-->0) {
			int x = Integer.parseInt(br.readLine());
			int parent = find(x);
			if(parent == 0)break;
			ans++;
			union(parent, parent-1);
		}
		System.out.println(ans);
	}
	private static void union(int x, int y) {
		int xx = find(x);
		int yy = find(y);
		
		if(xx != yy) {
			parents[xx] = yy;
		}
	}
	private static int find(int x) {
		if(parents[x] == x)return x;
		return parents[x] = find(parents[x]);
	}

}
