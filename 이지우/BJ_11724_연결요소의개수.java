package A202211;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_11724_연결요소의개수 {
	static int[] parents,level;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		level = new int[N+1];
		for(int i = 1; i <= N; i++)
			parents[i] = i;
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		Set<Integer> set = new HashSet<>();
		for(int i = 1; i <= N; i++)set.add(find(i));
		System.out.println(set.size());
	}
	static public void union (int a, int b) {
		a = find(a);
		b = find(b);
		if(a==b)return;
		if(level[a]<level[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		if(level[a]==level[b])level[a]++;
		parents[b] = a;
		return;
	}
	static public int find(int a) {
		if(parents[a] == a)return a;
		return parents[a]=find(parents[a]);
	}

}
