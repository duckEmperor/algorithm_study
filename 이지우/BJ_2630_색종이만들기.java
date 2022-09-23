package A202209;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2630_색종이만들기 {
	static int[][]map;
	static int N;
	static int answer[] = new int[2];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0 ; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j =0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		goGrid(0,0,N);
		for(int a : answer)System.out.println(a);
	}
	private static void goGrid(int i, int j, int n) {
		if(isSame(i,j,n)) {
			answer[map[i][j]]++;
			return;
		}
		n/=2;
		for(int l = 0; l < 2; l++) {
			for(int c = 0; c < 2; c++) {
				goGrid(i+l*n, j+c*n, n);
			}
		}
	}
	private static boolean isSame(int i, int j, int n) {
		int tmp = map[i][j];
		for(int y = i ; y < i+n; y++) {
			for(int x =j; x < j+n; x++) {
				if(map[y][x]!=tmp)return false;
			}
		}
		return true;
	}
}
























