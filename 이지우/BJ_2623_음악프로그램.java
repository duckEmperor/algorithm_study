package A202207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2623_음악프로그램 {
	static Node[] list;
	static int[] fan_in;
	static StringBuilder sb;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		int N = Integer.valueOf(st.nextToken());
		int M = Integer.valueOf(st.nextToken());
		list = new Node[N+1];
		fan_in = new int[N+1];
		visited = new boolean[N+1];
		while(M-->0) {
			st = new StringTokenizer(br.readLine());
			int t = Integer.valueOf(st.nextToken());
			int l = Integer.valueOf(st.nextToken());
			int r = 0;
			while(t-->1) {
				r = Integer.valueOf(st.nextToken());
				list[l] = new Node(r,list[l]);
				fan_in[r]++;
				l = r;
			}
		}
		Queue<Integer> que = new LinkedList<>();
		for(int i = 1; i <= N; i++) {
			if(fan_in[i] == 0)que.add(i);
		}
		while(!que.isEmpty()) {
			int now = que.poll();
			sb.append(now+"\n");
			for(Node node = list[now]; node != null; node = node.next) {
				if(--fan_in[node.num] == 0)que.offer(node.num);
			}
		}
		for(int a : fan_in) {
			if(a != 0) {
				System.out.println(0);
				return;
			}
		}
		System.out.println(sb.toString());
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
