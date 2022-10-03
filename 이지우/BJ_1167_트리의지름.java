package A202207;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1167_트리의지름 {
	static class Node{
		Node node;
		int w;
		int num;
		Node(Node node, int w, int num){
			this.node = node;
			this.w = w;
			this.num = num;
		}
	}
	static Node[] nList;
	static boolean[] visited;
	static int max;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N  = Integer.valueOf(br.readLine());
		nList = new Node[N+1];
		visited = new boolean[N+1];
		StringTokenizer st = null;
		while(N-->0) {
			st = new StringTokenizer(br.readLine());
			int index = Integer.valueOf(st.nextToken());
			int num = 0;
			int weight = 0;
			do{
				num = Integer.valueOf(st.nextToken());
				if(num > 0) {
					weight = Integer.valueOf(st.nextToken());
					nList[index] = new Node(nList[index], weight, num);
				}
			}while(st.hasMoreElements());
		}
		dfs(1);
		System.out.println(max);
	}
	private static int dfs(int index) {
		int one = 0;
		int two = 0;
		visited[index] = true;
		
		for(Node n = nList[index]; n != null; n = n.node) {
			if(visited[n.num])continue;
			int tmp = dfs(n.num) + n.w;
			if(tmp > one) {
				two = one;
				one = tmp;
			}else if(tmp > two) {
				two = tmp;
			}
		}
		max = Math.max(max, one+two);
		
		return one;
	}	

}





























