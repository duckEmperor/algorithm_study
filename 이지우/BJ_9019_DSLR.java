package A202211;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_9019_DSLR {
	static class Node{
		public int num;
		public String str;
		public Node(int num, String str) {
			this.num = num;
			this.str = str;
		}
	}
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		int s,e;
		boolean[] visited;
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			s=Integer.parseInt(st.nextToken());
			e=Integer.parseInt(st.nextToken());
			visited = new boolean[10000];
			Queue<Node> que = new LinkedList<>();
			que.add(new Node(s, ""));
			while(true) {
				Node now = que.poll();
				if(now.num == e) {
					sb.append(now.str+"\n");
					break;
				}
				if(visited[now.num])continue;
				visited[now.num] = true;
				que.add(new Node(D(now.num), now.str+"D"));
				que.add(new Node(S(now.num), now.str+"S"));
				que.add(new Node(L(now.num), now.str+"L"));
				que.add(new Node(R(now.num), now.str+"R"));
			}
		}
		System.out.println(sb);
	}
	
	public static int D(int num) {
		return (num*2)%10000;
	}
	
	public static int S(int num) {
		num+=10000;
		return (--num)%10000;
	}
	
	public static int L(int num) {
		num*=10;
		num+=(num/10000);
		return num%10000;
	}
	
	public static int R(int num) {
		return num/10 + (num%10)*1000;
	}
}
