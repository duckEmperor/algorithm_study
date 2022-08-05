package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_2211_네트워크복구 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][]	adjGraph = new int[N+1][N+1];
		int[] dist = new int[N+1];
		Arrays.fill(dist, 10001);
		dist[1] = 0;
		
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjGraph[from][to]=w;
			adjGraph[to][from]=w;
		}
		Set<String> set = new HashSet<>();
		int[] whereFrom = new int[N+1];
		PriorityQueue<int[]> pQue = new PriorityQueue<>((int[] o1, int[] o2)->{
															return Integer.compare(o1[0],o2[0]);
															});
		pQue.offer(new int[] {0,1});
		while(!pQue.isEmpty()) {
			int[] now = pQue.poll();
			for(int i=1; i<=N; i++) {
				if(adjGraph[now[1]][i] != 0 && dist[i] > dist[now[1]]+adjGraph[now[1]][i]) {
					dist[i] = dist[now[1]]+adjGraph[now[1]][i];
					pQue.offer(new int[] {dist[i], i});
					whereFrom[i] = now[1];
				}
			}
		}
		for(int i=2; i <=N; i++) {
			set.add(whereFrom[i] + " " + i);
		}
		System.out.println(set.size());
		for(String str : set) {
			System.out.println(str);
		}
	}
}
