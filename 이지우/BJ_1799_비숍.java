package A202208;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_1799_비숍 {
	static int[][] map;
	static int[] num;
	static int opp, max, len, b_max, w_max, b_size, w_size;
	static ArrayList<int[]> black;
	static ArrayList<int[]> white;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		len = Integer.parseInt(br.readLine());
		black = new ArrayList<>();
		white = new ArrayList<>();
		StringTokenizer st = null;
		for(int i = 0 ; i < len; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < len; j++) {
				int x = Integer.parseInt(st.nextToken());
				if(x==1) {
					if((i+j)%2 == 0) {
						black.add(new int[] {i,j});
					}else {
						white.add(new int[] {i,j});
					}
				}
			}
		}
		b_size = black.size();
		w_size = white.size();
		blackComb(0,0,new boolean[len][len]);
		whiteComb(0,0,new boolean[len][len]);
		System.out.println(b_max + w_max);
	}
	private static void blackComb(int n, int r, boolean[][] occu) {
		if(r+b_size-n <= b_max) {//백트래킹
			return;
		}
		if(n == b_size) {
			b_max = Math.max(b_max, r);
			return;
		}
		int[] now = black.get(n);
		if(!occu[now[0]][now[1]]) {
			blackComb(n+1, r+1, getOccu(occu, now[0], now[1]));
		}
		blackComb(n+1, r, occu);
	}
	private static void whiteComb(int n, int r, boolean[][] occu) {
		if(r+w_size-n <= w_max) {//백트래킹
			return;
		}
		if(n == w_size) {
			w_max = Math.max(w_max, r);
			return;
		}
		int[] now = white.get(n);
		if(!occu[now[0]][now[1]]) {
			whiteComb(n+1, r+1, getOccu(occu, now[0], now[1]));
		}
		whiteComb(n+1, r, occu);
	}
	
	private static boolean[][] getOccu(boolean[][] occu, int y, int x) { // 자리를 차지했다는 가정하에 만듬
		boolean[][] nextOccu = new boolean[len][len];
		for(int i = 0; i < len; i++) {
			for(int j = 0; j < len ; j++) {
				if(Math.abs(y-i) == Math.abs(x-j)) nextOccu[i][j] = true;
				else nextOccu[i][j] = occu[i][j];
			}
		}
		return nextOccu;
	}
}





















