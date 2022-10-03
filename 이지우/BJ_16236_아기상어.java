package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16236_아기상어 {
	static int N;
	static int[][] map;
	static int shark_size = 2;
	static int stack_feed = 0;
	static int[] nowLoc = new int[2];
	static int[] dy = new int[] {-1,0,0,1};
	static int[] dx = new int[] {0,-1,1,0};
	static int time = 0;
	static Queue<int[]> que = new LinkedList<>();
	static boolean[][] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				int a = Integer.parseInt(st.nextToken());
				map[i][j] = a;
				if(a == 9) {
					nowLoc[0] = i; nowLoc[1] = j;
					map[i][j] = 0;
				}
			}
		}
		
		findFeedAndEat();
		System.out.println(time);
	}
	private static void findFeedAndEat() {
		que.clear();
		int nextLocY = N;
		int nextLocX = N;
		int nextValue = Integer.MAX_VALUE;
		visited = new boolean[N][N];
		que.add(new int[] {nowLoc[0],nowLoc[1],0});
		visited[nowLoc[0]][nowLoc[1]] = true;
		int nr,nc;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			if(now[2] >= nextValue)break;
			for(int d = 0; d < 4; d++) {
				nr = now[0]+dy[d];
				nc = now[1]+dx[d];
				if(nr<0||nr>=N||nc<0||nc>=N||visited[nr][nc]||map[nr][nc]>shark_size)continue;
				visited[nr][nc]=true;
				if(shark_size == map[nr][nc] || map[nr][nc] == 0) {
					que.add(new int[] {nr,nc,now[2]+1});
				}else {
					if(nextLocY > nr) {
						nextLocY = nr;
						nextLocX = nc;
						nextValue = now[2]+1;
					}else if(nextLocY == nr && nextLocX > nc){
						nextLocX = nc;
					}
				}
				
			}
		}
		if(nextValue != Integer.MAX_VALUE) {
			map[nextLocY][nextLocX] = 0;
			stack_feed++;
			if(shark_size == stack_feed) {
				stack_feed = 0;
				shark_size++;
			}
			time += nextValue;
			nowLoc[0]=nextLocY;
			nowLoc[1]=nextLocX;
			findFeedAndEat();
		}
		
	}

}























