package A202206;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14500_테트로미노 {
	static int max, R, C;
	static int[] dy = new int[] {-1,0,0,1};
	static int[] dx = new int[] {0,1,-1,0};
	static int[][] sh_y = new int[][] {{0,0,0,1}, {0,0,0,-1}, {0,-1,-2,-1}, {0,-1,-2,-1}}; 
	static int[][] sh_x = new int[][] {{0,1,2,1}, {0,1,2,1}, {0,0,0,1}, {0,0,0,-1}}; 
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[][] field = new int[R][C];
		visited = new boolean[R*C];
		for(int i = 0 ; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < C; j++) {
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ; i < R; i++) {
			for(int j = 0; j < C; j++) {
				visited[i*C + j] = true;
				dfs(field, 0, i, j, 0);
				visited[i*C + j] = false;
				exDfs(field,i,j);
			}
		}
		
		System.out.println(max);
	}
	private static void exDfs(int[][] field, int r, int c) {
		loop:
		for(int t = 0; t < 4; t++) {
			int[] shapeY = sh_y[t];
			int[] shapeX = sh_x[t];
			int sum = 0;
			for(int k = 0 ; k < 4; k++) {
				int rr = r+shapeY[k];
				int cc = c+shapeX[k];
				if(rr < 0 || cc < 0 || rr >= R || cc >= C)continue loop;
				sum += field[rr][cc];
			}
			max = Math.max(sum, max);
		}
	}
	private static void dfs(int[][] field, int cnt, int r, int c, int sum) {
		if(cnt >= 4) {
			max = Math.max(sum, max);
			return;
		}
		sum += field[r][c];
		visited[r*C + c] = true;
		for(int d = 0 ; d < 4; d ++) {
			int nextR = r + dy[d];
			int nextC = c + dx[d];
			if(nextR < 0 || nextC < 0 || nextR >= R || nextC >= C || visited[nextR*C + nextC]) continue;
			visited[nextR*C + nextC] = true;
			dfs(field, cnt+1, nextR, nextC, sum);
			visited[nextR*C + nextC] = false;
		}
	}
}
