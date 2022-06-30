package A202206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1504_특정한최단경로 {
	static int graphINf = 1001 , N, INf = 200000000;
	static int[][]	graph;
	static PriorityQueue<int[]> pQue = new PriorityQueue<>((int[] o1,int[] o2) -> {return o1[1] - o2[1];});
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] mindist1 = new int[N+1];
		int[] mindistA = new int[N+1];
		int[] mindistB = new int[N+1];
		Arrays.fill(mindist1, INf);
		Arrays.fill(mindistA, INf);
		Arrays.fill(mindistB, INf);
		graph = new int[N+1][N+1];
		for(int i = 1; i <= N; i++) {
			Arrays.fill(graph[i], graphINf);
		}
		for(int i = 0 ; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(graph[from][to] > weight) {
				graph[from][to] = weight;
				graph[to][from] = weight;
			}
		}
		st = new StringTokenizer(br.readLine());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		dijkstra(new boolean[N+1], 1, mindist1);
		dijkstra(new boolean[N+1], A, mindistA);
		dijkstra(new boolean[N+1], B, mindistB);
		
		int ans = mindist1[A] + mindistA[B] + mindistB[N];
		int ans2 = mindist1[B] + mindistB[A] + mindistA[N];
		int answer = (ans >= INf && ans2 >= INf) ? -1 : Math.min(ans, ans2);
		System.out.println(answer);
	}
	
	private static void dijkstra(boolean[] visited, int a, int[] mindist) {
		pQue.add(new int[] {a,0});
		mindist[a] = 0;
		while(!pQue.isEmpty()) {
			int[] now = pQue.poll();
			if(visited[now[0]])continue;
			visited[now[0]] = true;
			for(int i = 1 ; i <= N; i++ ) {
				if(graph[now[0]][i] == 1001)continue; //간선이 없음
				if(now[1] + graph[now[0]][i] < mindist[i]) {
					mindist[i] = now[1] + graph[now[0]][i];
					pQue.add(new int[] {i, mindist[i]});
				}
			}
		}
		
	}
}

















