package A202207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2252_줄세우기 {
	static Node[] list;
	static StringBuilder sb;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		list = new Node[N+1];
		visited = new boolean[N+1];
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.valueOf(st.nextToken());
			int t = Integer.valueOf(st.nextToken());
			list[t] = new Node(s,list[t]);
		}
		for(int i = 1; i <= N; i++) {
			if(visited[i])continue;
			dfs(i);
		}
		System.out.println(sb.toString());
	}
	private static void dfs(int n) {
		visited[n] = true;
		for(Node node = list[n]; node != null; node = node.next) {
			if(!visited[node.num])dfs(node.num);
		}
		sb.append(n+" ");
	}
}
class Node{
	int num;
	Node next;
	public Node(int num, Node next) {
		this.num = num;
		this.next = next;
	}
}
