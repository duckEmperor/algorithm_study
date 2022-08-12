package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_9318_열쇠 {
	static StringBuilder sb = new StringBuilder();
	static char[][] map;
	static boolean[][] visited;
	static int R, C;
	static int[] dy = new int[] { -1, 0, 1, 0 };
	static int[] dx = new int[] { 0, 1, 0, -1 };
	static Queue<int[]> que = new LinkedList<>();
	static long key;
	static List<int[]> stuckArr;

	public static void main(String[] args) throws IOException { // 투포인터
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st = null;
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new char[R + 2][C + 2];
			visited = new boolean[R + 2][C + 2];
			key = 0;
			stuckArr = new ArrayList<>();
			String tmp = "";
			Arrays.fill(map[0], '.');
			Arrays.fill(map[R + 1], '.');
			for (int i = 1; i <= R; i++) {
				map[i][0] = '.';
				map[i][C + 1] = '.';
				tmp = br.readLine();
				for (int j = 1; j <= C; j++) {
					map[i][j] = tmp.charAt(j-1);
				}
			}
			String str = br.readLine();
			if (str.charAt(0) != '0') {
				for (int i = 0; i < str.length(); i++) {
					int x = str.charAt(i) - 97;
					key += (1 << x);
				}
			}
			getMoney();
		}
		System.out.println(sb.toString());
	}

	private static void getMoney() { // 65~90:A~Z 97~122:a~z
		int answer = 0;
		que.add(new int[] { 0, 0 });
		visited[0][0] = true;
		while (!que.isEmpty()) {
			int[] now = que.poll();
			for (int d = 0; d < 4; d++) {
				int nr = now[0] + dy[d];
				int nc = now[1] + dx[d];
				if (nr < 0 || nc < 0 || nr >= R + 2 || nc >= C + 2 || visited[nr][nc] || map[nr][nc] == '*')
					continue;
				if (map[nr][nc] == '$')
					answer++;
				else if (map[nr][nc] <= 122 && map[nr][nc] >= 97) {
					key = key | (1 << (map[nr][nc] - 97));
					getStuckPlace();
				} else if (map[nr][nc] <= 90 && map[nr][nc] >= 65 && !canOpen(map[nr][nc])) {
					stuckArr.add(new int[] { nr, nc, map[nr][nc] });
					continue;
				}
				visited[nr][nc] = true;
				que.add(new int[] { nr, nc });
			}
		}
		sb.append(answer).append("\n");
	}

	private static boolean canOpen(char d) {
		if (((1 << (d - 65)) & key) != 0) {
			return true;
		}
		return false;
	}

	private static void getStuckPlace() {
		for (int i = 0; i < stuckArr.size(); i++) {
			int stuckPoint[] = stuckArr.get(i);
			if (((1 << (stuckPoint[2] - 65)) & key) != 0) {
				que.add(stuckPoint);
				stuckArr.remove(i);
				i--;
			}
		}

	}

}
