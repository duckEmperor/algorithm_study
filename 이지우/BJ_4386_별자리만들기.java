package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_4386_별자리만들기 {
	static PriorityQueue<Stuff> pque;
	static boolean[] visited;
	static double[][] dist;
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dist = new double[N][N];
		visited = new boolean[N];
		StringTokenizer st = null;
		ArrayList<double[]> coord = new ArrayList<>();
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			coord.add(new double[] {Float.parseFloat(st.nextToken()),Float.parseFloat(st.nextToken())});
		}
		double[] a;
		double[] b;
		double d;
		for(int i = 0 ; i < N; i++) {
			for(int j = i+1 ; j < N; j++) {
				a = coord.get(i);
				b = coord.get(j);
				d = Math.sqrt((a[0]-b[0])*(a[0]-b[0]) + (a[1]-b[1])*(a[1]-b[1]));
				dist[i][j] = dist[j][i] = d;
			}
		}
		pque = new PriorityQueue<>((Stuff d1, Stuff d2)->{return Double.compare(d1.dist, d2.dist);});
		visited[0]=true;
		addStuff(0);
		double ans = 0;
		while(!pque.isEmpty()) {
			Stuff now = pque.poll();
			if(visited[now.num])continue;
			visited[now.num] = true;
			ans += now.dist;
			addStuff(now.num);
		}
		ans *= 100;
		ans = Math.round(ans);
		System.out.println(ans/100.0);
	}
	
	private static void addStuff(int n) {
		for(int i = 0 ; i < N; i++) {
			if(visited[i])continue;
			pque.add(new Stuff(dist[n][i], i));
		}
		
	}

	public static class Stuff{
		double dist;
		int num;
		public Stuff(double dist, int num) {this.dist=dist; this.num=num;}
	}
}
