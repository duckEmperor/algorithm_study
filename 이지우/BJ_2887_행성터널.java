package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_2887_행성터널 {
	static int[] parents, level;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		parents = new int[N];
		level = new int[N];
		for(int i = 0 ; i < N; i++) {
			parents[i] = i;
		}
		PriorityQueue<int[]> xpque = new PriorityQueue<>((int[]o1, int[]o2) -> {return o1[1] - o2[1];});
		PriorityQueue<int[]> ypque = new PriorityQueue<>((int[]o1, int[]o2) -> {return o1[1] - o2[1];});
		PriorityQueue<int[]> zpque = new PriorityQueue<>((int[]o1, int[]o2) -> {return o1[1] - o2[1];});
		StringTokenizer st = null;
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			xpque.add(new int[] {i, Integer.parseInt(st.nextToken())});
			ypque.add(new int[] {i, Integer.parseInt(st.nextToken())});
			zpque.add(new int[] {i, Integer.parseInt(st.nextToken())});
		}
		
		int[] x = xpque.poll();
		int[] y = ypque.poll();
		int[] z = zpque.poll();
		PriorityQueue<int[]> dist = new PriorityQueue<>((int[]o1, int[]o2) -> {return o1[2] - o2[2];});
		while(xpque.peek() != null) {
			dist.add(new int[] {x[0], xpque.peek()[0], xpque.peek()[1] - x[1]});
			dist.add(new int[] {y[0], ypque.peek()[0], ypque.peek()[1] - y[1]});
			dist.add(new int[] {z[0], zpque.peek()[0], zpque.peek()[1] - z[1]});
			x = xpque.poll();
			y = ypque.poll();
			z = zpque.poll();
		}
		int[] now;
		long answer = 0;
		while(!dist.isEmpty()) {
			now = dist.poll();
			if(union(now[0], now[1])) {
				answer += now[2];
			}
		}
		System.out.println(answer);
	}
	public static boolean union(int a, int b) {
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
	
	public static int find(int a) {
		if(a == parents[a])return a;
		return parents[a] = find(parents[a]);
	}
	
	
	
	
	
	
	
	
	
}
