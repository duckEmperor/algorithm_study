package A202206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_2606_바이러스 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int L = Integer.parseInt(br.readLine());
		boolean[][] field = new boolean[N+1][N+1];
		boolean[] visited = new boolean[N+1];
		visited[0] = true;
		visited[1] = true;
		for(int i = 0 ; i < L; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()); 
			int c = Integer.parseInt(st.nextToken()); 
			field[r][c] = true;
			field[c][r] = true;
		}
		
		Queue<Integer> que = new LinkedList<>();
		que.add(1);
		while(!que.isEmpty()) {
			int tmp = que.poll();
			for(int i = 2; i < N+1 ; i++) {
				if(field[tmp][i] && !visited[i]) {
					visited[i] = true;
					que.add(i);
				}
			}
		}
		int ans = 0;
		for(int i = 2; i < N+1; i++) {
			if(visited[i]) ans++;
		}
		System.out.println(ans);
	}

}
