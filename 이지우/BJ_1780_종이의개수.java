package A202209;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1780_종이의개수 {
	static int[][] map;
	static int[] ans = new int[3];
	static int N;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		division(0,0,N);
		for(int a : ans) {
			System.out.println(a);
		}
	}
	private static void division(int i, int j, int size) {
		if(isSame(i, j, size)) {
			ans[map[i][j]+1]++;
			return;
		}
		size/=3;
		for(int k = 0; k < 3; k++) {
			for(int l = 0; l < 3; l++) {
				division(i+k*size, j+l*size, size);
			}
		}
	}
	private static boolean isSame(int i, int j, int size) {
		int y = -1;
		int x = -1;
		int v = map[i][j];
		while(++y<size) {
			while(++x<size) {
				if(v != map[y+i][x+j]) {
					return false;
				}
			}
			x = -1;
		}
		return true;
	}



}










