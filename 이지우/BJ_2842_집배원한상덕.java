package A202207;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_2842_집배원한상덕 {
	static char[][] map;
	static int[][] high;
	static int[] pLoc;
	static Set<Integer> hList = new HashSet<>();
	static Integer[] hArr;
	static int N, pHigh, kCnt = 0, cnt, left=0, right, min = 100_0000, max, limitLeft, nr, nc;
	static int[] dy = new int[] { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = new int[] { 0, 1, 1, 1, 0, -1, -1, -1 };
	static boolean[][] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.valueOf(br.readLine());
		map = new char[N][N];
		high = new int[N][N];
		pLoc = new int[2];
		char a[];
		for (int i = 0; i < N; i++) {
			a = br.readLine().toCharArray();
			for (int j = 0; j < N; j++) {
				map[i][j] = a[j];
				if (a[j] == 'P') {
					pLoc[0] = i;
					pLoc[1] = j;
				} else if (a[j] == 'K')
					kCnt++;
			}
		}
		int b;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				b = Integer.valueOf(st.nextToken());
				high[i][j] = b;
				if(map[i][j] != '.') {
					min = Math.min(min, b);
					max = Math.max(max, b);
				}
				hList.add(b);
			}
		}
		hArr = hList.toArray(new Integer[hList.size()]);
		Arrays.sort(hArr);
		limitLeft = Arrays.binarySearch(hArr, min);
		right = Arrays.binarySearch(hArr, max);
		int answer = hArr[hArr.length-1]- hArr[0];
		while(left<=limitLeft&& right<hArr.length) {
			visited = new boolean[N][N];
			visited[pLoc[0]][pLoc[1]]=true;
			cnt = 0;
			dfs(pLoc[0], pLoc[1]);
			if(kCnt==cnt) {
				answer = Math.min(answer, hArr[right]-hArr[left]);
				left++;
			}else {
				right++;
			}
		}
		System.out.println(answer);
	}

	private static void dfs(int r, int c) {
		visited[r][c] = true;
		if(map[r][c]=='K')cnt++;
		for(int d = 0; d < 8; d++) {
			nr = r + dy[d];
			nc = c + dx[d];
			if(nr<0||nc<0||nr>=N||nc>=N||visited[nr][nc]||hArr[left]>high[nr][nc]||hArr[right]<high[nr][nc])continue;
			dfs(nr,nc);
		}
		
	}

}












