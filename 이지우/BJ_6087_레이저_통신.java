package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_6087_레이저_통신 {
	static char[][] map;
	static int[][] visited;
	static List<int[]> cLoc = new ArrayList<>();
	static int [] dy = new int[] {-1,0,1,0};
	static int [] dx = new int[] {0,1,0,-1};//상우하좌
	
	public static void main(String[] args)throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		PriorityQueue<int[]> que = new PriorityQueue<>((int[] o1, int[] o2)->{return Integer.compare(o1[2],o2[2]);});
		int C = Integer.valueOf(st.nextToken());
		int R = Integer.valueOf(st.nextToken());
		map = new char[R][C];
		visited = new int[R][C];
		for(int i = 0 ; i < R; i++) {
			String str = br.readLine();
			Arrays.fill(visited[i], 10001);
			for(int j = 0; j < C; j++) {
				if(str.charAt(j) == 'C')cLoc.add(new int[] {i,j,0,-1});
				map[i][j] = str.charAt(j);
			}
		}
		que.add(cLoc.get(0));
		visited[cLoc.get(0)[0]][cLoc.get(0)[1]] = 0;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			for(int d = 0; d < 4; d++) {
				int nr = now[0]+dy[d];
				int nc = now[1]+dx[d];
				if(nr<0||nc<0||nr>=R||nc>=C||map[nr][nc]=='*')continue;
				if(map[nr][nc]=='C') {
					if(now[3]==-1||now[3]==d)visited[nr][nc]=Math.min(visited[nr][nc], now[2]);
					else visited[nr][nc]=Math.min(visited[nr][nc], now[2]+1);
				}else {
					if(now[3]==-1||now[3]==d) {
						if(visited[nr][nc] >= now[2]) {
							visited[nr][nc] = now[2];
							que.offer(new int[] {nr,nc,now[2],d});
						}
							
					}else {
						if(visited[nr][nc] >= now[2]+1) {
							visited[nr][nc] = now[2]+1;
							que.offer(new int[] {nr,nc,now[2]+1,d});
						}
							
					}
				}
			}
		}
		System.out.println(visited[cLoc.get(1)[0]][cLoc.get(1)[1]]);
	}

}












