package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_1647_도시분할계획 {
	static List<Node>[] nList;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		nList = new List[N+1];
		visited = new boolean[N+1];
		for(int i = 1; i <= N; i++) {
			nList[i] = new ArrayList<>();
		}
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.valueOf(st.nextToken());
			int to = Integer.valueOf(st.nextToken());
			int weight = Integer.valueOf(st.nextToken());
			nList[from].add(new Node(to,weight));
			nList[to].add(new Node(from,weight));
		}
		PriorityQueue<Node> pQue = new PriorityQueue<>((Node n1, Node n2)-> {return Integer.compare(n1.w,n2.w);}); 
		visited[1] = true;
		for(Node n : nList[1]) {
			pQue.add(n);
		}
		ArrayList<Integer> arr = new ArrayList<>();
		while(!pQue.isEmpty()) {
			Node now = pQue.poll();
			if(visited[now.num])continue;
			visited[now.num] = true;
			arr.add(now.w);
			for(Node n : nList[now.num]) {
				if(visited[n.num])continue;
				pQue.add(n);
			}
		}
		Collections.sort(arr);
		int ans = 0;
		for(int i = 0 ; i < arr.size()-1; i++) ans+=arr.get(i);
		System.out.println(ans);
	}
	static class Node{
		int num;
		int w;
		public Node(int num, int w) {
			this.num = num;
			this.w = w;
		}
	}
}















