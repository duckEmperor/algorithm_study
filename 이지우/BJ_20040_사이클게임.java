package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20040_사이클게임 {
	static int[] parents;
	static int[] level;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		level = new int[N+1];
		for(int i = 0 ; i <= N ; i++) {
			parents[i] = i;
		}
		int tmp = 0;
		while(tmp++ < M) {
			st = new StringTokenizer(br.readLine());
			if(union(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()))) {
				System.out.println(tmp);
				return;
			}
		}
		System.out.println(0);
	}
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b)return true;
		if(level[a] < level[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		parents[b] = a;
		if(level[a] == level[b]) level[a]++;
		return false;
	}
	private static int find(int a) {
		if(parents[a] == a)return a;
		return parents[a] = find(parents[a]);
	}

}



















