package A202208;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1941_소문난칠공주 {
	static char[] chairs = new char[25];
	static int[] check = new int[7];
	static int[] dy = new int[] {-1,1,0,0};
	static int[] dx = new int[] {0,0,-1,1};
	static int ans = 0;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] tmp; 
		for(int i = 0; i < 5; i++) {
			tmp = br.readLine().toCharArray();
			for(int j = 0 ; j < 5; j++) {
				chairs[i*5 + j] = tmp[j];
			}
		}
		comb(0,0,0);
		System.out.println(ans);
	}
	private static void comb(int n, int r, int Y) {
		if(Y>=4)return;
		if(r == 7) {
			if(isAdj(0,new boolean[7])==7) {
				ans++; 
			}
			return;
		}
		if(n == 25)return;
		check[r] = n;
		comb(n+1,r+1, Y + (chairs[n] == 'Y' ? 1:0));
		comb(n+1,r, Y);
	}
//	private static boolean countS() {
//		int cnt = 0;
//		for(int a : check) {
//			if(chairs[a] == 'S') cnt++;
//		}
//		return cnt>=4 ? true : false;
//	}
	private static int isAdj(int n, boolean visited[]) {
		int cnt = 1;
		visited[n] = true;
		int now = check[n];
		for(int d = 0 ; d < 4; d++) {//상하좌우
			if((now%5==4&&d==3) || (now%5==0&&d==2))continue;
			int nn = now + dy[d]*5 + dx[d];
			for(int i = 0; i < 7; i++) {
				if(visited[i])continue;
				if(check[i] == nn) {
					cnt += isAdj(i, visited);
				}
			}
		}
		return cnt;
	}
}