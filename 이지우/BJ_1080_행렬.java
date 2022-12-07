package A202211;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1080_행렬 {
	static int N, M;
	static int[][] EF, ET;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		EF = new int[N][M];
		ET = new int[N][M];
		char[] arr;
		for(int i = 0 ; i < N; i++) {
			arr = br.readLine().toCharArray();
			for(int j = 0 ; j < M; j++) {
				EF[i][j] = arr[j]-'0';
			}
		}
		for(int i = 0 ; i < N; i++) {
			arr = br.readLine().toCharArray();
			for(int j = 0 ; j < M; j++) {
				ET[i][j] = arr[j]-'0';
			}
		}
		int cnt = 0;
		for(int i = 0; i < N-2; i++) {
			for(int j = 0; j < M-2; j++) {
				if(EF[i][j] != ET[i][j]) {
					cnt++;
					swap(i,j);
				}
			}
		}
		if(!compare()) {
			System.out.println(-1);
			return;
		}
		System.out.println(cnt);
		
	}

	private static void swap(int i, int j) {
		for(int y = i; y < i+3; y++) {
			for(int x = j; x < j+3; x++) {
				EF[y][x] = (EF[y][x] + 1)%2;
			}
		}
		
	}

	private static boolean compare() {
		for(int i = 0 ; i < N; i++) {
			for(int j = 0 ; j < M; j++) {
				if(EF[i][j] != ET[i][j]) return false;
			}
		}
		return true;
	}

}
















