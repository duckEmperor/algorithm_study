package A202211;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2468_안전영역 {
	static int N, max=1, max_height;
	static int[][] map;
	static int[] dy = new int[] {-1,1,0,0};
	static int[] dx = new int[]	{0,0,-1,1};
	static boolean[][] visited;
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		map=new int[N][N];
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				max_height = Math.max(max_height, map[i][j]);
			}
		}
		while(--max_height > 0) {
			visited = new boolean[N][N];
			int cnt = 0;
			for(int i = 0 ; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]&&max_height<map[i][j]) {
						cnt++;
						dfs(i,j);
					}
				}
			}
			max = Math.max(cnt, max);
		}
		System.out.println(max);
	}
	private static void dfs(int r, int c) {
		visited[r][c] = true;
		for(int d = 0; d < 4; d++) {
			int rr = r + dy[d];
			int cc = c + dx[d];
			if(rr>=0&&cc>=0&&rr<N&&cc<N&&!visited[rr][cc]&&max_height<map[rr][cc]) dfs(rr,cc);
		}
	}
}









