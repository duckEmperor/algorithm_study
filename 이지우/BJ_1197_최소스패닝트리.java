package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1197_최소스패닝트리 {
	static int[] parents;
	static int[] level;
	public static void main(String[] args) throws IOException{ // 이분탐색
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		parents = new int[V+1];
		level = new int[V+1];
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
		int E = Integer.parseInt(st.nextToken());
		PriorityQueue<Edge> pque = new PriorityQueue<>();
		while(E-->0) {
			st = new StringTokenizer(br.readLine());
			pque.add(new Edge(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		long ans = 0;
		while(!pque.isEmpty()) {
			Edge now = pque.poll();
			if(union(now.from,now.to)) {
				ans += now.w;
			}
		}
		System.out.println(ans);
	}
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b)return false;
		if(level[a] < level[b]) {
			int tmp = a;
			a = b;
			b = tmp;
		}
		parents[b] = a;
		if(level[a] == level[b])level[a]++;
		return true;
	}
	private static int find(int a) {
		if(parents[a]==a)return a;
		return parents[a] = find(parents[a]);
	}
	static class Edge implements Comparable<Edge>{
		int from;
		int to;
		int w;
		public Edge(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Edge o) {
			// TODO Auto-generated method stub
			return this.w - o.w;
		}
	}
}
