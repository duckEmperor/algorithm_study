package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_1697_숨박꼭질 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int ans = Integer.MAX_VALUE;
		int s = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		boolean[] visited = new boolean[200001];
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {s,0});
		visited[s]=true;
		while(!que.isEmpty()) {
			int now[] = que.poll();
			if(now[0] > e) {
				ans = Math.min(ans, now[0]-e+now[1]);
			}else if (now[0] == e){
				ans = Math.min(ans, now[1]);
				break;
			}else if(now[0] <= e/2 ) { //반보다 작거나 같은떄
				if(!visited[now[0]*2]) {
					visited[now[0]*2] = true;
					que.add(new int[] {now[0]*2, now[1]+1});
				}
				if(!visited[now[0]+1]) {
					visited[now[0]+1] = true;
					que.add(new int[] {now[0]+1, now[1]+1});
				}
				if( now[0] > 1 && !visited[now[0]-1]) {
					visited[now[0]-1] = true;
					que.add(new int[] {now[0]-1, now[1]+1});
				}
			}else if(now[0] > e/2) { //반보다 클때
				if(!visited[now[0]*2]) {
					visited[now[0]*2] = true;
					que.add(new int[] {now[0]*2, now[1]+1});
				}
				if(!visited[now[0]+1]) {
					visited[now[0]+1] = true;
					que.add(new int[] {now[0]+1, now[1]+1});
				}
				if(!visited[now[0]-1]) {
					visited[now[0]-1] = true;
					que.add(new int[] {now[0]-1, now[1]+1});
				}
			}
		}
		System.out.println(ans);
	}
}
