package A202207;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ_12920_평범한배낭2 {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] max_val = new int[M+1];
		ArrayList<int[]> arr = new ArrayList<>();
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int dist = Integer.valueOf(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
		
			for(int j = 0; num > 0; j++) {
				int tmp = 1<<j;
				tmp = Math.min(tmp, num);
				arr.add(new int[] {dist*tmp, w*tmp});
				num-=tmp;
			}
		}
		
		for(int[] stuff : arr) {
			for(int j = M; j >= stuff[0]; j--) {
				max_val[j] = Math.max(max_val[j - stuff[0]] + stuff[1] , max_val[j]);
			}		
		}
		
		System.out.println(max_val[M]);
	}
	
}












