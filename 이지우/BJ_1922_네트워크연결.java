package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1922_네트워크연결 {
	public static void main(String[] args) throws IOException { // 프림
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		boolean[] visited = new boolean[N];
		int[][] edge = new int[N][N];
		int[] minValue = new int[N];
		Arrays.fill(minValue, Integer.MAX_VALUE);
		while(M-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			int c = Integer.parseInt(st.nextToken());
			edge[a][b]=edge[b][a]=c;
		}
		int sizeMst = 0;
		minValue[0] = 0;
		
		int now=0, ans = 0, min;
		while(sizeMst++<N) {
			min=Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				if(!visited[i] && minValue[i] < min ) {
					min = minValue[i];
					now = i;
				}
			}
			ans += min;
			visited[now] = true;
			for(int i = 0; i < N; i++) {
				if(!visited[i] && edge[now][i] != 0 && minValue[i] > edge[now][i] ) {
					minValue[i] = edge[now][i];
				}
			}
		}
		System.out.println(ans);
	}
	
	
	
	/* 크루스칼
	static int[] parents, level;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parents = new int[N+1];
		level = new int[N+1];
		for(int i = 0 ; i <= N; i++) {
			parents[i] = i;
		}
		int M = Integer.parseInt(br.readLine());
		PriorityQueue<Node> pque = new PriorityQueue<>();
		while(M-->0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pque.add(new Node(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
		}
		int ans = 0;
		while(!pque.isEmpty()) {
			Node now = pque.poll();
			if(union(now.from,now.to))ans+=now.w;
		}
		System.out.println(ans);
	}
	private static boolean union(int a , int b) {
		a = find(a);
		b = find(b);
		if(a==b)return false;
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

	static class Node implements Comparable<Node>{
		int from;
		int to;
		int w;
		public Node(int from, int to, int w) {
			this.from = from;
			this.to = to;
			this.w = w;
		}
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return this.w - o.w;
		}
		
	}
	*/ 
}
