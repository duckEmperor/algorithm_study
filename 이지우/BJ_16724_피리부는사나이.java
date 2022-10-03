package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_16724_피리부는사나이 {
	static char[][] map;
	static int C;
	static int[] parent;
	static int[] level;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		parent = new int[R*C];
		level = new int[R*C];
		for(int i = 0 ; i < R*C; i++) {
			parent[i] = i;
		}
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				dfs(i, j);
			}
		}
		int ans = 0;
		for(int i = 0 ; i < R*C; i++) {
			if(parent[i] == i) ans++;
		}
		System.out.println(ans);
	}

	private static void dfs(int i, int j) {
		int a = i;
		int b = j;
		char dir = map[i][j];
		switch (dir) {
		case 'U':
			i--;
			break;
		case 'D':
			i++;
			break;
		case 'L':
			j--;
			break;
		case 'R':
			j++;
		}
		if (!union(a*C+b, i*C+j)) {
			dfs(i, j);
		}
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b)return true; //이미 같은 집합이다.
		if(level[a] < level[b]) { // 항상 a쪽의 깊이가 크거나 같게 해준다
			int tmp = a;
			a = b;
			b = tmp;
		}
		parent[b] = a; //작은쪽을 큰쪽에 속해준다 속도면에서 유리하기 때문에
		if(level[a]==level[b])level[a]++;
		return false;
	}
	private static int find(int a) {
		if(parent[a]==a)return a;
		return parent[a] = find(parent[a]);
	}
}














