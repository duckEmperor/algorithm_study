package A202206;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_구슬탈출2 {
	static int R, C;
	static boolean visited[][][][], blueOut, redOut;
	static int[] dy = new int[] { -1, 1, 0, 0 };
	static int[] dx = new int[] { 0, 0, 1, -1 };
	
	static class ScreenShot {
		int cnt;
		int[] redLoc;
		int[] blueLoc;
		char[][] field;

		public ScreenShot(int cnt, int[] redLoc, int[] blueLoc, char[][] field) {
			this.cnt = cnt;
			this.redLoc = redLoc;
			this.blueLoc = blueLoc;
			this.field = field;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		int[][] loc = new int[2][2];
		char[][] field = new char[R][C];
		visited = new boolean[R][C][R][C];
		for (int i = 0; i < R; i++) {
			field[i] = br.readLine().toCharArray();
			for (int j = 1; j < C - 1; j++) {
				if (field[i][j] == 'R')
					loc[0] = new int[] { i, j };
				if (field[i][j] == 'B')
					loc[1] = new int[] { i, j };
			}
		}
		System.out.println(bfs(new ScreenShot(0, loc[0], loc[1],field)));
	}

	private static int bfs(ScreenShot ss) {
		Queue<ScreenShot> que = new LinkedList<>();
		que.add(ss);
		while (!que.isEmpty()) {
			ScreenShot now = que.poll();
			if(now.cnt > 9)break;
			visited[now.redLoc[0]][now.redLoc[1]][now.blueLoc[0]][now.blueLoc[1]] = true;
			for (int d = 0; d < 4; d++) { // 상하우좌
				boolean redFirst = getOrder(now.redLoc, now.blueLoc, d);
				char[][] cloneField = new char[R][C];

				for(int i = 0; i < R; i++){ // 반복문 + ArrayCopy
				System.arraycopy(now.field[i], 0, cloneField[i], 0, C);
				}
				int[] cloneRedLoc = now.redLoc.clone();
				int[] cloneBlueLoc = now.blueLoc.clone();
				ScreenShot next = getNextSS(redFirst, d, now.cnt, cloneRedLoc, cloneBlueLoc, cloneField);
				
				if (next != null) {
					if (redOut) {
						return next.cnt;
					}
					else 
						que.add(next);
				}else redOut = blueOut = false;
			}
		}
		return -1;
	}

	private static ScreenShot getNextSS(boolean redFirst, int d, int cnt, int[] redLoc, int[] blueLoc, char[][] field) {
		if (redFirst) {
			moveBall(d, field, redLoc);
			moveBall(d, field, blueLoc);
		} else {
			moveBall(d, field, blueLoc);
			moveBall(d, field, redLoc);
		}
		// 방문여부 테스트
		if (blueOut || visited[redLoc[0]][redLoc[1]][blueLoc[0]][blueLoc[1]])
			return null;
			
		return new ScreenShot(cnt + 1, redLoc, blueLoc, field);
	}

	private static void moveBall(int d, char[][] field, int[] loc) {
		int y = dy[d];
		int x = dx[d];

		while (true) {
			if (field[loc[0] + y][loc[1] + x] != '.') {
				if (field[loc[0] + y][loc[1] + x] == 'O') {
					if (field[loc[0]][loc[1]] == 'R')
						redOut = true;
					else {
						blueOut = true;
					}
					field[loc[0]][loc[1]] = '.';
					loc[0] = 0; loc[1] = 0;
				} else {
					if(y != dy[d] || x != dx[d]) {
						field[loc[0] + y - dy[d]][loc[1] + x - dx[d]] = field[loc[0]][loc[1]];
						field[loc[0]][loc[1]] = '.';
						loc[0] = loc[0] + y - dy[d];
						loc[1] = loc[1] + x - dx[d];
					}
				}
				break;
			}
			y += dy[d];
			x += dx[d];
		}
	}

	private static boolean getOrder(int[] redLoc, int[] blueLoc, int dir) {
		switch (dir) {
		case 0:
			return redLoc[0] < blueLoc[0] ? true : false;// 위로
		case 1:
			return redLoc[0] > blueLoc[0] ? true : false;// 아래로
		case 2:
			return redLoc[1] > blueLoc[1] ? true : false;// 우로
		case 3:
			return redLoc[1] < blueLoc[1] ? true : false;// 좌로
		}
		return false;
	}
}
