package A202206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_9370_미확인도착지 {
	static int N, M, CASE, S, from, to, INF = 100_000_000;
	static int[][] graph;
	static int[] minDist, candid;
	static PriorityQueue<int[]> pque = new PriorityQueue<>((int[] o1, int[] o2) -> {return o1[1] - o2[1];});
	static boolean[] visited;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 0 ; t < T; t++) {
			 st = new StringTokenizer(br.readLine());
			 N = Integer.parseInt(st.nextToken());
			 graph = new int[N+1][N+1];
			 minDist = new int[N+1];
			 visited = new boolean[N+1];
			 Arrays.fill(minDist, INF);
			 M = Integer.parseInt(st.nextToken());
			 CASE = Integer.parseInt(st.nextToken());
			 candid = new int[CASE];
			 st = new StringTokenizer(br.readLine());
			 S = Integer.parseInt(st.nextToken());
			 from = Integer.parseInt(st.nextToken());
			 to = Integer.parseInt(st.nextToken());
			 while(M-->0) {
				 st = new StringTokenizer(br.readLine());
				 int a = Integer.parseInt(st.nextToken());
				 int b = Integer.parseInt(st.nextToken());
				 int weight = Integer.parseInt(st.nextToken()) * 2;
				 graph[a][b] = weight;
				 graph[b][a] = weight;
			 }
			 
			 graph[from][to]--;
			 graph[to][from]--;
			 for(int i = 0 ; i < CASE; i++) {
				 candid[i] = Integer.parseInt(br.readLine());
			 }
			 dijkstra();
			 Arrays.sort(candid);
			 for(int a : candid) {
				 if(minDist[a] % 2 == 1)sb.append(a + " ");
			 }
			 sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	private static void dijkstra() {
		pque.add(new int[] {S, 0});
		minDist[S] = 0;
		while(!pque.isEmpty()) {
			int[] now = pque.poll();
			if(visited[now[0]])continue;
			visited[now[0]]=true;
			for(int i = 1 ; i <= N; i++) {
				if(graph[now[0]][i] == 0)continue;
				if(minDist[i] > now[1] + graph[now[0]][i]) {
					minDist[i] = now[1] + graph[now[0]][i];
					pque.add(new int[] {i, minDist[i]});
				}
			}
		}
	}

}
