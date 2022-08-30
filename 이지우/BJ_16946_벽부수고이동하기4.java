package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_16946_벽부수고이동하기4 {
	static int[][] mask;
	static int[] maskCount;
	static int[][] map;
	static Set<Integer> set;
	static boolean[][] visited;
	static int[] dx = new int[] {1,-1,0,0};
	static int[] dy = new int[] {0,0,1,-1};
	static int maskNum = 1, R, C;
	public static void main(String[] args) throws IOException{ // 이분탐색
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		mask = new int[R][C];
		visited = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			String str = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j] == 0 && !visited[i][j]) {
					goMasking(i,j);
					maskNum++;
				}
			}
		}
		maskCount = new int[maskNum];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(mask[i][j]!=0)maskCount[mask[i][j]]++;
			}
		}
		
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(map[i][j]==1) setMap(i,j);
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb.append(map[i][j]%10);
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	private static void setMap(int r, int c) {
		set = new HashSet<>();
		for(int d = 0; d < 4; d++) {
			int rr = r + dy[d];
			int cc = c + dx[d];
			if(rr>=0 && rr<R && cc>=0 && cc<C && map[rr][cc]==0) {
				set.add(mask[rr][cc]);
			}
		}
		Iterator<Integer> it = set.iterator();
		while(it.hasNext()) {
			map[r][c]+=maskCount[it.next()];
		}
	}
	private static void goMasking(int r, int c) {
		visited[r][c] = true;
		mask[r][c] = maskNum;
		for(int d = 0; d < 4; d++) {
			int rr = r + dy[d];
			int cc = c + dx[d];
			if(rr>=0 && rr<R && cc>=0 && cc<C && !visited[rr][cc] && map[rr][cc]==0) {
				goMasking(rr,cc);
			}
		}
	}
}